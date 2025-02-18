package com.helixz.awsgitdemo.messages;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequestMapping("api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    @PostMapping
    public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message){
        if(message.getMessage()==null||message.getMessage().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(messageService.createMessage(message.getMessage()),HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Message>> getMessages(){
        return new ResponseEntity<>(messageService.getMessages(),HttpStatus.FOUND);
    }
}