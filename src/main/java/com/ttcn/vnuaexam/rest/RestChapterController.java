package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.ChapterRequestDto;
import com.ttcn.vnuaexam.dto.response.ChapterResponseDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.ChapterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chapter")
@RequiredArgsConstructor
public class RestChapterController {
    private final ChapterService chapterService;

    @GetMapping("/{id}")
    public EMResponse<ChapterResponseDto> getById(@PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(chapterService.getById(id));
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
}
