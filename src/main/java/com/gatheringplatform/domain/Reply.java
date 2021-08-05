package com.gatheringplatform.domain;

import com.gatheringplatform.annotation.ReplyGroups;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.LinkedList;

public class Reply {

    private class Node {
        Node child;
        Node next;
    }

    private long postNo;
    private long replyNo;
    LinkedList<Node> ll = new LinkedList<Node>();

    // 내용
    @NotBlank(message = "댓글 내용을 적어주세요.", groups = {ReplyGroups.class})
    private String content;

    // 댓글 쓴 사람
    private String writer;

    // 작성일
    private Timestamp created_at;

    // primary key(게시물 번호, 댓글 번호)



}
