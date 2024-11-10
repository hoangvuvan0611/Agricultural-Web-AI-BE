package com.ttcn.vnuaexam.service;

import com.ttcn.vnuaexam.dto.request.ChapterRequestDto;
import com.ttcn.vnuaexam.dto.response.ChapterResponseDto;
import com.ttcn.vnuaexam.entity.Chapter;

public interface ChapterService {
    ChapterResponseDto chapterToResponseDto(Chapter chapter);

    Chapter requestDtoToChapter(ChapterRequestDto chapterRequestDto);
}
