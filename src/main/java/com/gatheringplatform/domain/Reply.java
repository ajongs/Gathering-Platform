package com.gatheringplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gatheringplatform.annotation.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class Reply {

    @ApiModelProperty(hidden = true)
    private long id; // post_reply 전체를 대상으로 하는 댓글 번호

    //    @ApiModelProperty(hidden = true) // merge 후 추가
    private long post_id; // 게시물 번호

    //    @ApiModelProperty(hidden = true) // merge 후 추가
    private long parent_id; // 부모 댓글의 번호

    // 내용
    @NotBlank(message = "댓글 내용을 적어주세요.", groups = {ValidationGroups.uploadReply.class})
    private String content;

    // 댓글 쓴 사람의 닉네임
    @ApiModelProperty(hidden = true)
    private String author;

    // 작성 시간
    @ApiModelProperty(hidden = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp created_at;

    // 게시물 삭제 여부
    @ApiModelProperty(hidden = true)
    private Boolean is_deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPost_id() {
        return post_id;
    }

    public long getParent_id() {
        return parent_id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }
}