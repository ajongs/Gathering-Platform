package com.gatheringplatform.mapper;

import com.gatheringplatform.domain.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Mapper
@Repository // 스프링 데이터 접근 계층
public interface ReplyMapper {
    Reply getRepliesByNumber(@Param("postNo") Long postNo); // 모든 댓글 가져오기
    Reply getReplyByNumber(@Param("postNo") Long postNo, @Param("replyNo") Long replyNo); // 댓글 가져오기
    Long getPostNoByNumber(@Param("postNo") Long postNo, @Param("replyNo") Long replyNo); // 게시물 번호 가져오기
    Long getReplyNoByNumber(@Param("postNo") Long postNo, @Param("replyNo") Long replyNo); // 댓글 번호 가져오기
    Long getParentReplyNoByNumber(@Param("postNo") Long postNo, @Param("replyNo") Long replyNo); // 부모 댓글 번호 가져오기
    String getContentByNumber(@Param("postNo") Long postNo, @Param("replyNo") Long replyNo); // 댓글 내용 가져오기
    String getWriterByNumber(@Param("postNo") Long postNo, @Param("replyNo") Long replyNo); // 댓글 작성자 가져오기
    Long getCreatedAtByNumber(@Param("postNo") Long postNo, @Param("replyNo") Long replyNo); // 댓글을 쓴 시간 가져오기

    void insert(Reply reply); // 댓글 쓰기
    void update(Reply reply); // 댓글 수정하기
    void delete(Reply reply); // 댓글 삭제하기
}