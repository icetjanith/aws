package com.helixz.awsgitdemo;

import com.helixz.awsgitdemo.messages.Message;
import com.helixz.awsgitdemo.messages.MessageController;
import com.helixz.awsgitdemo.messages.MessageRepository;
import com.helixz.awsgitdemo.messages.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AwsGitDemoApplicationTests {
    @Autowired
    MessageService messageService;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageController messageController;
    
    ArrayList<Message> messageArrayList=new ArrayList<>();
    Message message=new Message(
            1L,
            "abc",
            LocalDateTime.parse("2024-12-04T00:11:43.972889"),
            LocalDateTime.parse("2024-12-04T00:11:43.972889")
    );

    @Test
    void contextLoads() {
    }

    @Test
    void test_saveMessage(){
        Message serviceMessage = messageService.createMessage("New Message");
        Message savedMsg = messageRepository.save(serviceMessage);
        Assertions.assertNotNull(serviceMessage);
        Assertions.assertFalse(serviceMessage.getMessage().isEmpty());
        Assertions.assertEquals("New Message",serviceMessage.getMessage());
        Assertions.assertEquals(savedMsg.getMessage(),serviceMessage.getMessage());
    }

    @Test
    void test_getMessages(){
        messageArrayList.add(message);
        Assertions.assertTrue(messageArrayList.contains(message));
        Assertions.assertEquals("abc",messageArrayList.getFirst().getMessage());
    }

    @Test
    void test_postRequest(){
        Message message1=new Message();
        message1.setMessage("");
        ResponseEntity<Message> responseEntity = messageController.createMessage(message);
        ResponseEntity<Message> responseEntity1 = messageController.createMessage(message1);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(responseEntity1.getStatusCode(),HttpStatus.BAD_REQUEST);
    }

    @Test
    void test_getRequest(){
        ResponseEntity<List<Message>> messagesList = messageController.getMessages();
        Assertions.assertEquals(messagesList.getStatusCode(),HttpStatus.FOUND);
    }
}
