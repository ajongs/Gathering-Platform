package com.gatheringplatform.service;

import com.gatheringplatform.domain.Message;

public interface MessageService {
    void sendMessage(Message message, String token);
}
