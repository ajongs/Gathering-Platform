package com.gatheringplatform.controller;

import com.gatheringplatform.annotation.Auth;
import com.gatheringplatform.annotation.ValidationGroups;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController // JSON 형태로 객체 데이터를 반환하기 위한 컨트롤러
@RequestMapping(value = "/board/{board_id}/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    // 전체 댓글 보기
    @ApiOperation(value = "해당 게시물의 해당되는 페이지 전체 댓글 조회", notes = "board_id 게시물의 댓글 리스트를 조회하는 API 입니다. 페이지당 5개의 댓글을 보여줍니다.")
    @GetMapping(value = "/{page_num}")
    public ResponseEntity viewAll(@PathVariable long board_id, @PathVariable long page_num) {
        return new ResponseEntity(replyService.viewAll(board_id, page_num), HttpStatus.OK);
    }

//    // 개별 댓글 보기 --> 추후 필요시 기능 추가
//    @ApiOperation(value = "개별 댓글 조회", notes = "댓글 번호가 replyNo인 댓글을 조회하는 API 입니다.")
//    @GetMapping(value = "/{reply_id}")
//    public ResponseEntity view(@PathVariable long reply_id) {
//        return new ResponseEntity(replyService.view(reply_id), HttpStatus.OK);
//    }

    // 댓글 등록
    @Auth
    @ApiOperation(value = "댓글 등록", notes = "댓글을 등록하는 API 입니다.",
                  authorizations = @Authorization(value = "JWT"))
    @PostMapping
    public ResponseEntity post(@RequestBody @Validated(ValidationGroups.uploadReply.class) Reply reply) throws IOException {
        return new ResponseEntity(replyService.post(reply), HttpStatus.OK);
    }

    // 댓글 수정
    @Auth
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정하는 API 입니다. 댓글의 content를 수정합니다.",
                  authorizations = @Authorization(value = "JWT"))
    @PutMapping(value = "/{reply_id}")
    public ResponseEntity update(@PathVariable long reply_id, @RequestBody Reply reply) {
        return new ResponseEntity(replyService.update(reply_id, reply), HttpStatus.OK);
    }

    // 댓글 삭제
    @Auth
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제하는 API 입니다. soft delete로 구현되어 있습니다.",
                  authorizations = @Authorization(value = "JWT"))
    @PatchMapping(value = "/{reply_id}")
    public ResponseEntity delete(@PathVariable long reply_id) {
        return new ResponseEntity(replyService.delete(reply_id), HttpStatus.OK);
    }
}