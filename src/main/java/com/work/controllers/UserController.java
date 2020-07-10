package com.work.controllers;

import com.work.dto.UserDTO;
import com.work.entities.User;
import com.work.mappers.UserMapper;
import com.work.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api")
public class UserController extends GenericController<UserService> {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO obj = getService().getById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Page<UserDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPorPage", defaultValue = "10") Integer linesPorPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<User> list = getService().findPage(page, linesPorPage, orderBy, direction);
        Page<UserDTO> listDto = list.map(obj -> userMapper.userToUserDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO objDto) {
        UserDTO userDTO = getService().insert(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO obj, @PathVariable Long id) {
        getService().update(obj, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }

}
