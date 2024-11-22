package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.dto.request.SubjectRequestDto;
import com.ttcn.vnuaexam.dto.response.SubjectResponseDto;
import com.ttcn.vnuaexam.dto.search.SubjectSearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/subject")
@RequiredArgsConstructor
public class RestSubjectController {
    private final SubjectService subjectService;

    @GetMapping("{id}")
    public EMResponse<SubjectResponseDto> getById(@PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(subjectService.getById(id));
    }

    @PostMapping()
    public EMResponse<SubjectResponseDto> create(@RequestBody SubjectRequestDto requestDto) throws EMException {
        return new EMResponse<>(subjectService.create(requestDto));
    }

    @PutMapping("/{id}")
    public EMResponse<SubjectResponseDto> update(@RequestBody SubjectRequestDto requestDto, @PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(subjectService.update(requestDto, id));
    }

    @DeleteMapping("{id}")
    public EMResponse<Boolean> delete(@PathVariable("id") Long id) throws EMException {
        return new EMResponse<>(subjectService.deleteById(id));
    }

    @GetMapping("/search")
    public EMResponse<Page<SubjectResponseDto>> search(SubjectSearchDto searchDto) {
        return new EMResponse<>(subjectService.searchSubject(searchDto));
    }
}
