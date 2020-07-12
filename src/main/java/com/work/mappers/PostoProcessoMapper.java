package com.work.mappers;

import com.work.dto.PostoProcessoDTO;
import com.work.dto.UserDTO;
import com.work.entities.PostoProcesso;
import com.work.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostoProcessoMapper {

    PostoProcessoMapper INSTANCE = Mappers.getMapper(PostoProcessoMapper.class);

    PostoProcessoDTO postoProcessoToPostoProcessoDTO(PostoProcesso postoProcesso);

    PostoProcesso postoProcessoDtoToPostoProcesso(PostoProcessoDTO postoProcessoDTO);

    List<PostoProcessoDTO> listPostoProcessoTOListPostProcessoDTO(List<PostoProcesso> postosProcessos);
    
    List<PostoProcesso> listPostoProcessoDTOTOListPostProcesso(List<PostoProcessoDTO> postosProcessos);
    
}
