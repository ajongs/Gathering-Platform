package com.gatheringplatform.domain;

import java.sql.Timestamp;

public class ChatRoom {
    public long no;
    public String preview;

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

}
