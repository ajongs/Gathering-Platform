package com.gatheringplatform.service;

import com.gatheringplatform.domain.Reply;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface ReplyService {

    Reply view(Long postNo, Long replyNo); // 해당 게시물의 특정 댓글 반환

    List<Reply> viewAll(Long postNo); // 해당 게시물의 전체 댓글 목록 반환

    void post(Reply reply);

    void update(Reply reply);

    void delete(Reply reply);
}