package com.gatheringplatform.mapper;

import com.gatheringplatform.domain.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
@Repository // 스프링 데이터 접근 계층
public interface ReplyMapper {
    List<Reply> getReplies(Map parameter); // 모든 댓글 가져오기
    Reply getReply(long id); // 댓글 가져오기
    String getAuthor(long id); // 댓글 작성자 닉네임 가져오기
    void insert(Reply reply); // 댓글 쓰기
    void update(Reply reply); // 댓글 수정하기
    void delete(long id); // 댓글 삭제하기
    long countReplyByPost(long post_id); // 포스트의 댓글 개수
}