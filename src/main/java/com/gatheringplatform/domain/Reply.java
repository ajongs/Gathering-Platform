package com.gatheringplatform.domain;

import com.gatheringplatform.annotation.ReplyGroups;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class Reply {

    private long post_no; // 게시물 번호
    private long reply_no; // 댓글 번호
    private long parent_reply_no; // 부모 댓글의 번호(대댓글일 경우에, 자신의 부모 댓글 번호를 말한다. 만약, root 댓글일 경우에는 0으로 한다.)

    // 내용
    @NotBlank(message = "댓글 내용을 적어주세요.", groups = {ReplyGroups.class})
    private String content;

    // 댓글 쓴 사람
    private String writer;

    // 작성 시간
    private Timestamp created_at;

}