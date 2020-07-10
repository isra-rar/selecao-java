package com.work.services;

import com.work.dto.UserDTO;
import com.work.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService extends GenericService {


    UserDTO getById(Long id);

    List<UserDTO> getAll();

    UserDTO insert(UserDTO objDto);

    void update(UserDTO objDto, Long id);

    void delete(Long id);

    Page<User> findPage(Integer page, Integer linesPorPage, String orderBy, String direction);

}
