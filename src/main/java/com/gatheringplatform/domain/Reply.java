package com.gatheringplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gatheringplatform.annotation.ReplyGroups;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class Reply {

//    @ApiModelProperty(hidden = true) // merge 후 추가
    private long post_no; // 게시물 번호

    @ApiModelProperty(hidden = true)
    private long reply_no; // post_reply 전체를 대상으로 하는 댓글 번호

//    @ApiModelProperty(hidden = true) // merge 후 추가
    private long parent_reply_no; // 부모 댓글의 번호

    // 내용
    @NotBlank(message = "댓글 내용을 적어주세요.", groups = {ReplyGroups.class})
    private String content;

    // 댓글 쓴 사람의 닉네임
//    @ApiModelProperty(hidden = true) // merge 후 추가
    private String writer_nickname;

    // 작성 시간
//    @DateTimeFormat(pattern = "yyyy-MM-DD'T'HH:mm:ss")
    @ApiModelProperty(hidden = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp created_at;

    // 게시물 삭제 여부
    @ApiModelProperty(hidden = true)
    private Boolean is_deleted;

    public long getPost_no() {
        return post_no;
    }

    public void setPost_no(long post_no) {
        this.post_no = post_no;
    }

    public long getReply_no() {
        return reply_no;
    }

    public void setReply_no(long reply_no) {
        this.reply_no = reply_no;
    }

    public long getParent_reply_no() {
        return parent_reply_no;
    }

    public void setParent_reply_no(long parent_reply_no) {
        this.parent_reply_no = parent_reply_no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter_nickname() {
        return writer_nickname;
    }

    public void setWriter_nickname(String writer_nickname) {
        this.writer_nickname = writer_nickname;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}