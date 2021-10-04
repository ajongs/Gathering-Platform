package com.gatheringplatform.controller;

import com.gatheringplatform.domain.Message;
import com.gatheringplatform.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    @MessageMapping("/message")
    public void sendMessage(Message message, @Header(value = "Authorization") String token){
        messageService.sendMessage(message, token);
    }

}
