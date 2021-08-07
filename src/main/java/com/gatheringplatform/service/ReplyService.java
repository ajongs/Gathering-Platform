package com.gatheringplatform.service;

import com.gatheringplatform.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public interface ReplyService {

    Map<Long, Reply> view(Long replyNo);
    void post(Reply reply);
    void update(Reply reply);
    void delete(Reply reply);
}