package com.ttcn.vnuaexam.service.impl;

import com.ttcn.vnuaexam.dto.request.ChapterRequestDto;
import com.ttcn.vnuaexam.dto.response.ChapterResponseDto;
import com.ttcn.vnuaexam.entity.Chapter;
import com.ttcn.vnuaexam.repository.ChapterRepository;
import com.ttcn.vnuaexam.service.ChapterService;
import com.ttcn.vnuaexam.service.mapper.ChapterMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final ChapterMapper chapterMapper;

    @Override
    public ChapterResponseDto chapterToResponseDto(Chapter chapter) {
        return chapterMapper.entityToResponseDto(chapter);
    }

    @Override
    public Chapter requestDtoToChapter(ChapterRequestDto requestDto) {
        Chapter chapter = chapterMapper.requestDtoToEntity(requestDto);
        chapterRepository.save(chapter);
        return chapter;
    }
}
