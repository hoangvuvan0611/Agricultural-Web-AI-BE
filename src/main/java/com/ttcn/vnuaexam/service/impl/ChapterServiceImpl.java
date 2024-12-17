package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.ChapterRequestDto;
import com.ttcn.vnuaexam.dto.response.ChapterResponseDto;
import com.ttcn.vnuaexam.dto.search.ChapterSearchDto;
import com.ttcn.vnuaexam.entity.Chapter;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.repository.ChapterRepository;
import com.ttcn.vnuaexam.repository.QuestionRepository;
import com.ttcn.vnuaexam.service.ChapterService;
import com.ttcn.vnuaexam.service.mapper.ChapterMapper;
import com.ttcn.vnuaexam.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.ttcn.vnuaexam.constant.enums.ErrorCodeEnum.*;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService {
    private final ChapterMapper chapterMapper;
    private final ChapterRepository chapterRepository;
    private final QuestionRepository questionRepository;

    @Override
    public ChapterResponseDto getById(Long id) throws EMException {
        // Check id null, trống
        if (ObjectUtils.isEmpty(id)) {
            throw new EMException(CHAPTER_ID_IS_NOT_EXIST);
        }

        var chapter = chapterRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_CHAPTER));
        return chapterMapper.entityToResponse(chapter);
    }

    @Override
    public ChapterResponseDto create(ChapterRequestDto requestDto) throws EMException {
        //validateDepartment
        validateChapter(requestDto, true);

        //Tạo entity
        var chapter = chapterMapper.requestToEntity(requestDto);
        chapterRepository.save(chapter);
        return chapterMapper.entityToResponse(chapter);
    }

    @Override
    public ChapterResponseDto update(ChapterRequestDto requestDto, Long id) throws EMException {
        //lấy ra entity theo id
        var chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new EMException(NOT_FOUND));

        //validate request
        requestDto.setId(id);
        validateChapter(requestDto, false);

        //update entity
        chapterMapper.setValue(requestDto, chapter);
        chapter = chapterRepository.save(chapter);
        return chapterMapper.entityToResponse(chapter);
    }

    private void validateChapter(ChapterRequestDto requestDto, boolean isCreate) throws EMException {
        // Kiểm tra name tồn tại trong môn chưa
        List<Chapter> chapters;
        if (isCreate)
            chapters = chapterRepository.findByNameAndSubjectId(requestDto.getName(), requestDto.getSubjectId());
        else
            chapters = chapterRepository.findByNameAndNotId(requestDto.getName(), requestDto.getId(), requestDto.getSubjectId());

        if (!CollectionUtils.isEmpty(chapters))
            throw new EMException(CHAPTER_NAME_IS_EXIST);
    }

    @Override
    public Boolean deleteById(Long id) throws EMException {
        var department = chapterRepository.findById(id).orElseThrow(() -> new EMException(NOT_FOUND_CHAPTER));
        questionRepository.deleteByChapterId(id);
        chapterRepository.delete(department);
        return true;
    }

    @Override
    public Page<ChapterResponseDto> search(ChapterSearchDto dto) {
        Pageable pageRequest = PageUtils.getPageable(dto.getPageIndex(), dto.getPageSize());
        var chapterEntities = chapterRepository.search(dto, pageRequest);
        return chapterEntities.map(chapterMapper::entityToResponse);
    }
}
