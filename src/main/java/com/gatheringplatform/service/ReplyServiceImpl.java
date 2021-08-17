package com.gatheringplatform.service;

import com.gatheringplatform.domain.Reply;
import com.gatheringplatform.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Autowired
//    private BoardService boardService;

    @Override // 개별 댓글 반환
    public Reply view(Long replyNo) { // 해당 게시물의 특정 댓글 반환
        return replyMapper.getReplyByNumber(replyNo);
    }

    @Override
    public List<Reply> viewAll(Long postNo) { // 해당 게시물의 전체 댓글 목록 반환
        return replyMapper.getRepliesByNumber(postNo);
    }

    @Override
    public void post(Reply reply) {
//        reply.setPost_no(boardService.getId()); // merge 후 추가
//        reply.setWriter_nickname(userService.getLoginNickname()); // merge 후 추가
        replyMapper.insert(reply);
    }

    @Override
    public void update(Reply reply) {
        replyMapper.update(reply);
    }

    @Override
    public void delete(Reply reply) {  // 부모 댓글이 삭제되면 자식 댓글까지 모두 삭제
        replyMapper.delete(reply);
    }
}