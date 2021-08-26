package com.gatheringplatform.service;

import com.gatheringplatform.domain.Reply;
import com.gatheringplatform.format.DefaultResponse;
import com.gatheringplatform.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service // 핵심 비즈니스 로직
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private UserService userService;

    @Override // 개별 댓글 반환
    public Reply view(Long replyNo) { // 해당 게시물의 특정 댓글 반환
        return replyMapper.getReplyByNumber(replyNo);
    }

    @Override
    public List<Reply> viewAll(Long postNo) { // 해당 게시물의 전체 댓글 목록 반환
        return replyMapper.getRepliesByNumber(postNo);
    }

    @Override
    public ResponseEntity post(Reply reply) {
        reply.setWriter_nickname(userService.getLoginNickname()); // merge 후 추가
        System.out.println("Writer_nickname: " + reply.getWriter_nickname());
        replyMapper.insert(reply);
        return new ResponseEntity(new DefaultResponse("댓글 등록 완료!", HttpStatus.OK), HttpStatus.OK);
    }

    @Override
    public ResponseEntity update(Reply reply) {
        replyMapper.update(reply);
        return new ResponseEntity(new DefaultResponse("댓글 수정 완료!", HttpStatus.OK), HttpStatus.OK);
    }

    @Override
    public ResponseEntity delete(Reply reply) {
        replyMapper.delete(reply);
        return new ResponseEntity(new DefaultResponse("댓글 삭제 완료!", HttpStatus.OK), HttpStatus.OK);
    }
}