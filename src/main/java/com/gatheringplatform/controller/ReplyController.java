package com.gatheringplatform.controller;

import com.gatheringplatform.domain.Reply;
import com.gatheringplatform.domain.User;
import com.gatheringplatform.format.DefaultResponse;
import com.gatheringplatform.service.ReplyService;
import com.gatheringplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController // JSON 형태로 객체 데이터를 반환하기 위한 컨트롤러
@RequestMapping(value = "/board/{board_seq}/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;
    private UserService userService;

    // 전체 댓글 보기
    @GetMapping
    public ResponseEntity viewAll(@PathVariable("board_seq") Long postNo, HttpServletResponse response) {
        return new ResponseEntity(replyService.viewAll(postNo), HttpStatus.OK);
    }

    // 개별 댓글 보기
    @GetMapping(value = "/{replyNo}")
    public ResponseEntity view(@PathVariable("replyNo") long replyNo, HttpServletResponse response) {
        return new ResponseEntity(replyService.view(replyNo), HttpStatus.OK);
    }

    // 댓글 등록
    @PostMapping
    public ResponseEntity post(@ModelAttribute Reply reply) {
        replyService.post(reply);
        return new ResponseEntity(new DefaultResponse("댓글 등록 완료!", HttpStatus.OK), HttpStatus.OK);
    }

    // 댓글 수정
    @PostMapping(value = "/update")
    public ResponseEntity update(@ModelAttribute Reply reply) {
        replyService.update(reply);
        return new ResponseEntity(new DefaultResponse("댓글 수정 완료!", HttpStatus.OK), HttpStatus.OK);
    }

    // 댓글 삭제
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@ModelAttribute Reply reply) {
        replyService.delete(reply);
        return new ResponseEntity(new DefaultResponse("댓글 삭제 완료!", HttpStatus.OK), HttpStatus.OK);
    }
}