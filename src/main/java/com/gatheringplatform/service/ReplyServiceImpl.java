package com.gatheringplatform.service;

import com.gatheringplatform.domain.Reply;
import com.gatheringplatform.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service // 핵심 비즈니스 로직
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public Map<Long, Reply> view(Long replyNo) {
        return null;
    }

    @Override
    public void post(Reply reply) {

    }

    @Override
    public void update(Reply reply) {

    }

    @Override
    public void delete(Reply reply) {  // 부모 댓글이 삭제되면 자식 댓글까지 모두 삭제

    }
}
