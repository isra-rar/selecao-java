package com.work.services;

import com.work.dto.EnderecoDTO;
import com.work.entities.Endereco;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EnderecoService extends GenericService {


    EnderecoDTO getById(Long id);

    List<EnderecoDTO> getAll();

    EnderecoDTO insert(EnderecoDTO objDto);

    void update(EnderecoDTO objDto, Long id);

    void delete(Long id);

    Page<Endereco> findPage(Integer page, Integer linesPorPage, String orderBy, String direction);

}
