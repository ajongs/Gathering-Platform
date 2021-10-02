package com.gatheringplatform.service;

import com.gatheringplatform.domain.Reply;
import com.gatheringplatform.format.DefaultResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReplyService {

    Reply view(long reply_id); // 해당 게시물의 특정 댓글 반환
    List<Reply> viewAll(long post_id, long page_num); // 해당 게시물의 해당 페이지의 전체 댓글 목록 반환
    DefaultResponse post(Reply reply);
    DefaultResponse update(long reply_id, Reply reply);
    DefaultResponse delete(long reply_id);
}