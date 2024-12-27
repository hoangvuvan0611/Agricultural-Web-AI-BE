package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.ChapterRequestDto;
import com.ttcn.vnuaexam.dto.response.ChapterResponseDto;
import com.ttcn.vnuaexam.dto.search.ChapterSearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.ChapterService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapter")
@RequiredArgsConstructor
public class RestChapterController {
    private final ChapterService chapterService;

    @GetMapping("/{id}")
    public EMResponse<ChapterResponseDto> getById(@PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(chapterService.getById(id));
    }

    @GetMapping("/by-subject/{subjectId}")
    public EMResponse<List<ChapterResponseDto>> getBySubjectId(@PathVariable("subjectId") Long subjectId) {
        return new EMResponse<>(chapterService.getBySubjectId(subjectId));
    }

    @PostMapping()
    public EMResponse<ChapterResponseDto> create(@Valid @RequestBody ChapterRequestDto requestDto) throws EMException {
        return new EMResponse<>(chapterService.create(requestDto));
    }

    @PutMapping("/{id}")
    public EMResponse<ChapterResponseDto> update(@Valid @RequestBody ChapterRequestDto requestDto, @PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(chapterService.update(requestDto, id));
    }

    @DeleteMapping("/{id}")
    public EMResponse<Boolean> delete(@PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(chapterService.deleteById(id));
    }

    @GetMapping("/search")
    public EMResponse<Page<ChapterResponseDto>> search(ChapterSearchDto dto) throws EMException {
        return new EMResponse<>(chapterService.search(dto));
    }
}
