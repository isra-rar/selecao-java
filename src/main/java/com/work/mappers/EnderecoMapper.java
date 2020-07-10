package com.work.mappers;

import com.work.dto.EnderecoDTO;
import com.work.entities.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    Endereco enderecoDTOtoEndereco (EnderecoDTO enderecoDTO);

    EnderecoDTO enderecotoEnderecoDTO (Endereco enderecoDTO);

    List<EnderecoDTO> listEnderecoTOListEnderecoDTO(List<Endereco> livros);

    List<Endereco> listEnderecoDTOTOListEndereco(List<EnderecoDTO> livros);
}
