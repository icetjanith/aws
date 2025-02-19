package com.helixz.awsgitdemo.messages;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDTO toDTO(Message message);

    Message toMsg(MessageDTO messageDTO);

}
