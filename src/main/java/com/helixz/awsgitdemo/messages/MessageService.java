package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    public MessageDTO createMessage(MessageDTO messageDTO) {
        log.info("Creating message with content: {}", messageDTO.getMessage());
        Message message = messageMapper.toMsg(messageDTO);
        return messageMapper.toDTO(messageRepository.save(message));
    }

    public List<MessageDTO> getMessagesSorted(String sortBy, String sortDirection, int page, int size) {
        List<MessageDTO> messageDTOList = new ArrayList<>();
        Sort sort = Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(sortDirection)));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Message> all = messageRepository.findAll(pageable);
        all.forEach(message -> {
            messageDTOList.add(messageMapper.toDTO(message));
        });
        return messageDTOList;
    }
}