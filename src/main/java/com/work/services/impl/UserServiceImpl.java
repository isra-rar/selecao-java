package com.work.services.impl;

import com.work.dto.UserDTO;
import com.work.entities.User;
import com.work.mappers.UserMapper;
import com.work.repositories.UserRepository;
import com.work.services.UserService;
import com.work.services.excepctions.DataIntegrityException;
import com.work.services.excepctions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends GenericServiceImpl<UserRepository, UserMapper> implements UserService {


    @Override
    public UserDTO getById(Long id) {
        Optional<User> obj = getRepository().findById(id);
        obj.orElseThrow(() -> new ObjectNotFoundException(
                "User não encontrado"));
        return getModelMapper().userToUserDTO(obj.get());
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> obj = getRepository().findAll();
        return getModelMapper().listUserTOListUserDTO(obj);
    }

    @Override
    public UserDTO insert(UserDTO objDto) {
        User obj = getModelMapper().userDtoToUser(objDto);
        obj.setId(null);
        obj.getEnderecos().forEach(e -> e.setUser(obj));
        User user = getRepository().save(obj);
        return getModelMapper().userToUserDTO(user);
    }

    @Override
    public void update(UserDTO objDto, Long id) {
        UserDTO userDTO = getById(id);
        updateData(userDTO, objDto);
        User user = getModelMapper().userDtoToUser(userDTO);
        user.getEnderecos().forEach(e -> e.setUser(user));
        getRepository().save(user);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        try {
            getRepository().deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um CLIENTE que possua associações");
        }
    }

    @Override
    public Page<User> findPage(Integer page, Integer linesPorPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPorPage, Sort.Direction.valueOf(direction), orderBy);
        return getRepository().findByCliente(pageRequest);
    }

    private void updateData(UserDTO newObj, UserDTO obj) {
        newObj.setNome(obj.getNome());
        newObj.setCpf(obj.getCpf());
        newObj.setEmail(obj.getEmail());
        newObj.setEnderecos(obj.getEnderecos());
    }
}
