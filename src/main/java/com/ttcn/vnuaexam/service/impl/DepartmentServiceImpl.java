package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.DepartmentRequestDto;
import com.ttcn.vnuaexam.dto.response.DepartmentResponseDto;
import com.ttcn.vnuaexam.dto.search.DepartmentSearchDto;
import com.ttcn.vnuaexam.entity.Department;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.DepartmentRepository;
import com.ttcn.vnuaexam.service.DepartmentService;
import com.ttcn.vnuaexam.service.mapper.DepartmentMapper;
import com.ttcn.vnuaexam.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum.*;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponseDto getById(Long id) throws EMException {
        // Check id null, trống
        if (ObjectUtils.isEmpty(id)) {
            throw new EMException(DEPARTMENT_ID_IS_NOT_EXIST);
        }

        var department = departmentRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_DEPARTMENT));
        return departmentMapper.entityToResponse(department);
    }

    @Override
    public DepartmentResponseDto create(DepartmentRequestDto requestDto) throws EMException {
        //validateDepartment
        validateDepartment(requestDto, true);

        //Tạo entity
        var department = departmentMapper.requestToEntity(requestDto);
        departmentRepository.save(department);
        return departmentMapper.entityToResponse(department);
    }

    private void validateDepartment(DepartmentRequestDto requestDto, boolean isCreate) throws EMException {
        // Kiểm tra code, name trống
        if(!StringUtils.hasText(requestDto.getCode()) || !StringUtils.hasText(requestDto.getName())){
            throw new EMException(DEPARTMENT_CODE_OR_NAME_IS_EMPTY);
        }

        // Kiểm tra code tồn tại chưa
        List<Department> departments;
        if (isCreate)
            departments = departmentRepository.findByCode(requestDto.getCode());
        else
            departments = departmentRepository.findByCodeAndNotId(requestDto.getCode(), requestDto.getId());

        if (!CollectionUtils.isEmpty(departments))
            throw new EMException(DEPARTMENT_CODE_IS_EXIST);
    }

    @Override
    public DepartmentResponseDto update(DepartmentRequestDto requestDto, Long id) throws EMException {
        //lấy ra entity theo id
        var department = departmentRepository.findById(id)
                .orElseThrow(() -> new EMException(NOT_FOUND));

        //validate request
        validateDepartment(requestDto, false);

        //update entity
        departmentMapper.setValue(requestDto, department);
        department = departmentRepository.save(department);
        return departmentMapper.entityToResponse(department);
    }

    @Override
    public Boolean deleteById(Long id) throws EMException {
        var department = departmentRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_DEPARTMENT));
        departmentRepository.delete(department);
        return true;
    }

    @Override
    public Page<DepartmentResponseDto> searchDepartment(DepartmentSearchDto searchDto) {
        Pageable pageRequest = PageUtils.getPageable(searchDto.getPageIndex(), searchDto.getPageSize());
        var pageResults = departmentRepository.searchDepartment(searchDto, pageRequest);
        return pageResults.map(departmentMapper::entityToResponse);
    }
}
