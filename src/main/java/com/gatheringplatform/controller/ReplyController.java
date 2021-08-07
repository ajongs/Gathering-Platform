package com.gatheringplatform.controller;

import com.gatheringplatform.annotation.ReplyGroups;
import com.gatheringplatform.annotation.ValidationGroups;
import com.gatheringplatform.domain.Reply;
import com.gatheringplatform.domain.User;
import com.gatheringplatform.format.DefaultResponse;
import com.gatheringplatform.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController // JSON 형태로 객체 데이터를 반환하기 위한 컨트롤러
@RequestMapping(value = "/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    // 전체 댓글 보기
    @GetMapping
    public ResponseEntity view(@RequestParam long postNo, HttpServletResponse response) {
        return new ResponseEntity(replyService.view(postNo), HttpStatus.OK);
    }

    // 댓글 등록
    @PostMapping(value = "/post")
    public ResponseEntity post(@RequestBody Reply reply) {
        replyService.post(reply);
        return new ResponseEntity(new DefaultResponse("댓글 등록 완료!", HttpStatus.OK), HttpStatus.OK);
    }

    // 댓글 수정
    @PostMapping(value = "/update")
    public ResponseEntity update(@RequestBody Reply reply) {
        replyService.update(reply);
        return new ResponseEntity(new DefaultResponse("댓글 수정 완료!", HttpStatus.OK), HttpStatus.OK);
    }

    // 댓글 삭제
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody Reply reply) {
        replyService.delete(reply);
        return new ResponseEntity(new DefaultResponse("댓글이 삭제되었습니다.", HttpStatus.OK), HttpStatus.OK);
    }
}