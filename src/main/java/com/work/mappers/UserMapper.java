package com.work.mappers;

import com.work.dto.UserDTO;
import com.work.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDtoToUser(UserDTO userDTO);

    List<UserDTO> listUserTOListUserDTO(List<User> users);


}
