package com.ttcn.vnuaexam.rest;

import com.ttcn.vnuaexam.constant.enums.StatusExamRoomEnum;
import com.ttcn.vnuaexam.dto.request.ExamRoomRequestDto;
import com.ttcn.vnuaexam.dto.response.ExamResponseDto;
import com.ttcn.vnuaexam.dto.response.ExamRoomResponseDto;
import com.ttcn.vnuaexam.dto.search.ExamRoomSearchDto;
import com.ttcn.vnuaexam.dto.search.ExamSearchDto;
import com.ttcn.vnuaexam.dto.search.SearchDto;
import com.ttcn.vnuaexam.exception.EMException;
import com.ttcn.vnuaexam.response.EMResponse;
import com.ttcn.vnuaexam.service.ExamRoomService;
import com.ttcn.vnuaexam.service.mapper.ExamRoomMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exam-rooms")
@AllArgsConstructor
public class RestExamRoomController {
    private final ExamRoomService examRoomService;
    private final ExamRoomMapper examRoomMapper;

    @GetMapping()
    public EMResponse<List<ExamRoomResponseDto>> getAll() {
        return new EMResponse<>(examRoomService.findAll());
    }

    @PostMapping()
    public EMResponse<ExamRoomResponseDto> create(@RequestBody ExamRoomRequestDto requestDto) throws EMException {
        return new EMResponse<>(examRoomService.add(requestDto));
    }

    @PutMapping("/{id}")
    public EMResponse<ExamRoomResponseDto> update(@PathVariable Long id, @RequestBody ExamRoomRequestDto requestDto) throws EMException {
        return new EMResponse<>(examRoomService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public EMResponse<Boolean> delete(@PathVariable Long id) throws EMException {
        return new EMResponse<>(examRoomService.delete(id));
    }

    @GetMapping("/{id}")
    public EMResponse<ExamRoomResponseDto> getById(@PathVariable Long id) throws EMException {
        return new EMResponse<>(examRoomService.findById(id));
    }

    @GetMapping("/search")
    public EMResponse<Page<ExamRoomResponseDto>> search(ExamRoomSearchDto dto) throws EMException {
        return new EMResponse<>(examRoomService.search(dto));
    }

    @PutMapping("/start/{id}")
    public EMResponse<Boolean> start(@PathVariable Long id, @RequestParam Integer status) throws EMException {
        return new EMResponse<>(examRoomService.updateStatus(id, status));
    }

    @GetMapping("/get-status-map")
    public EMResponse<Map<Integer, String>> getStatusMap() {
        return new EMResponse<>(StatusExamRoomEnum.getStatusRoom());
    }
}
