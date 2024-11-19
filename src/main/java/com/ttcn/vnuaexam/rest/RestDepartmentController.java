package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.DepartmentRequestDto;
import com.ttcn.vnuaexam.dto.response.DepartmentResponseDto;
import com.ttcn.vnuaexam.dto.search.DepartmentSearchDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/department")
@RequiredArgsConstructor
public class RestDepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("{id}")
    public EMResponse<DepartmentResponseDto> getById(@PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(departmentService.getById(id));
    }

    @PostMapping()
    public EMResponse<DepartmentResponseDto> create(@Valid @RequestBody DepartmentRequestDto requestDto) throws EMException {
        return new EMResponse<>(departmentService.create(requestDto));
    }

    @PutMapping("/{id}")
    public EMResponse<DepartmentResponseDto> update(@Valid @RequestBody DepartmentRequestDto requestDto, @PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(departmentService.update(requestDto, id));
    }

    @DeleteMapping("{id}")
    public EMResponse<Boolean> delete(@PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(departmentService.deleteById(id));
    }

    @GetMapping("/search")
    public EMResponse<Page<DepartmentResponseDto>> search(DepartmentSearchDto searchDto){
        return new EMResponse<>(departmentService.searchDepartment(searchDto));
    }
}
