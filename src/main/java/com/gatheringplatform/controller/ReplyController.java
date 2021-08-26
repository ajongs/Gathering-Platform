package com.gatheringplatform.controller;

import com.gatheringplatform.annotation.Auth;
import com.gatheringplatform.domain.Reply;
import com.gatheringplatform.domain.User;
import com.gatheringplatform.format.DefaultResponse;
import com.gatheringplatform.service.ReplyService;
import com.gatheringplatform.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController // JSON 형태로 객체 데이터를 반환하기 위한 컨트롤러
@RequestMapping(value = "/board/{board_id}/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    // 전체 댓글 보기
    @ApiOperation(value = "해당 게시물의 전체 댓글 조회", notes = "board_id 게시물의 전체 댓글을 조회하는 API 입니다.")
    @GetMapping
    public ResponseEntity viewAll(@PathVariable("board_id") long postNo, HttpServletResponse response) {
        return new ResponseEntity(replyService.viewAll(postNo), HttpStatus.OK);
    }

    // 개별 댓글 보기
    @ApiOperation(value = "개별 댓글 조회", notes = "댓글 번호가 replyNo인 댓글을 조회하는 API 입니다.")
    @GetMapping(value = "/{replyNo}")
    public ResponseEntity view(@PathVariable long replyNo, HttpServletResponse response) {
        return new ResponseEntity(replyService.view(replyNo), HttpStatus.OK);
    }

    // 댓글 등록
    @Auth
    @ApiOperation(value = "댓글 등록", notes = "댓글을 등록하는 API 입니다.",
                  authorizations = @Authorization(value = "JWT"))
    @PostMapping
    public ResponseEntity post(@ModelAttribute Reply reply) {
        return new ResponseEntity(replyService.post(reply), HttpStatus.OK);
    }

    // 댓글 수정
    @Auth
    @ApiOperation(value = "댓글 등록", notes = "댓글을 수정하는 API 입니다. 댓글의 content를 수정합니다.",
                  authorizations = @Authorization(value = "JWT"))
    @PostMapping(value = "/update")
    public ResponseEntity update(@ModelAttribute Reply reply) {
        return new ResponseEntity(replyService.update(reply), HttpStatus.OK);
    }

    // 댓글 삭제
    @Auth
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제하는 API 입니다. soft delete로 구현되어 있습니다.",
                  authorizations = @Authorization(value = "JWT"))
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@ModelAttribute Reply reply) {
        return new ResponseEntity(replyService.delete(reply), HttpStatus.OK);
    }
}