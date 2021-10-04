package com.gatheringplatform.domain;

import java.sql.Timestamp;

public class Message {
    public long no;
    public long chatRoomId;
    public String sender;
    public String receiver;
    public String content;
    public Timestamp time;


    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
