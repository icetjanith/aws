package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message createMessage(String content){
        log.info("Creating message with content: {}", content);
        Message message=new Message();
        message.setMessage(content);
        return messageRepository.save(message);
    }

    public List<Message> getMessages(){
        return new ArrayList<>(messageRepository.findAllByOrderByCreatedDateDesc());
    }
}