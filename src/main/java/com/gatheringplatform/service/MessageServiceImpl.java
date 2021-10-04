package com.gatheringplatform.service;

import com.gatheringplatform.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService{
    private final SimpMessagingTemplate template;

    @Autowired
    UserService userService;

    public MessageServiceImpl(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public void sendMessage(Message message, String token) {
        String sender = userService.getLoginNickname(token);

        message.setSender(sender);
        String receiver = message.getReceiver();

        Map<String, Object> header = new HashMap<>();
        header.put("sender", sender);
        header.put("receiver", receiver);

        System.out.println("sender = " + sender);
        System.out.println("receiver = " + receiver);

        template.convertAndSend("/sub/messages/"+sender, message.getContent(), header);
        template.convertAndSend("/sub/messages/"+receiver, message.getContent(), header);
    }
}
