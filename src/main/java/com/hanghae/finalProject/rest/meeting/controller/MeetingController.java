package com.hanghae.finalProject.rest.meeting.controller;

import com.hanghae.finalProject.config.controller.errorcode.Code;
import com.hanghae.finalProject.config.dto.DataResponseDto;
import com.hanghae.finalProject.config.dto.ResponseDto;
import com.hanghae.finalProject.rest.meeting.dto.MeetingLinkRequestDto;
import com.hanghae.finalProject.rest.meeting.dto.MeetingRequestDto;
import com.hanghae.finalProject.rest.meeting.dto.MeetingUpdateRequestDto;
import com.hanghae.finalProject.rest.meeting.service.MeetingService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MeetingController {

    private final MeetingService  meetingService;
    
    @ApiOperation (value = "모임 상세 조회")
    @GetMapping("/meetings/{id}")
    public ResponseDto getMeeting(@PathVariable Long id) {
        return DataResponseDto.of(meetingService.getMeeting(id));
    }
    
    @ApiOperation (value = "모임 전체 조회")
    @GetMapping("/meetings")
    public ResponseDto getMeetings(
         @RequestParam(value="sortby", defaultValue = "popular") int sortBy,
         @RequestParam(value="category", required = false) String category,
         @RequestParam(value="meetingId", required = false, defaultValue = "5") int mee
    ) {
        // 1. 인기순, 카테고리 없는버전 - 우선이거 (+ 무한스크롤)
            // 1.1 카테고리 있는버전
        // 2. 신규순, 카테고리 없는버전
            // 2.1 카테고리 있는버전
        // 무한스크롤 적용필요
        return DataResponseDto.of(meetingService.getMeetings(sortBy, category));
    }
    
    @ApiOperation (value = "모임 생성")
    @PostMapping("/meetings")
    public ResponseDto createMeeting(@RequestBody @Valid MeetingRequestDto requestDto) {
        return DataResponseDto.of(meetingService.createMeeting(requestDto), Code.CREATE_MEETING.getStatusMsg());
    }
    
    @ApiOperation (value = "모임 수정")
    @PatchMapping("/meetings/{id}")
    public ResponseDto updateAllMeeting(@PathVariable Long id, @RequestBody @Valid MeetingUpdateRequestDto requestDto) {
        meetingService.updateAllMeeting(id,requestDto);
        return ResponseDto.of(true, Code.UPDATE_MEETING);
    }
    
    @ApiOperation (value = "모임 링크 수정")
    @PatchMapping("/meetings/{id}/link")
    public ResponseDto updateLink(@PathVariable Long id, @RequestBody @Valid MeetingLinkRequestDto requestDto) {
        meetingService.updateLink(id, requestDto);
        return ResponseDto.of(true, Code.UPDATE_LINK);
    }
    
    @ApiOperation (value = "모임 삭제")
    @DeleteMapping("/meetings/{id}")
    public ResponseDto deleteMeeting(@PathVariable Long id) {
        meetingService.deleteMeeting(id);
        return ResponseDto.of(true, Code.DELETE_MEETING);
    }



    //     public ResponseDto signup(@RequestBody @Valid SignupRequestDto requestDto) {
//          userService.signUp(requestDto);
//          // 1. data o , msg o
////          return DataResponseDto.of("data test", "test 성공"); //data있고 별도 msg보낼 경우
//          // 2. data o msg 정상
////          return DataResponseDto.of("data test");
//          // 3. DATA X, MSG 따로
//          return ResponseDto.of(true, Code.USER_SIGNUP_SUCCESS);
//     }

}
