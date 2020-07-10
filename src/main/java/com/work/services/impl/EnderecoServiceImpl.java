package com.work.services.impl;

import com.work.dto.EnderecoDTO;
import com.work.entities.Endereco;
import com.work.mappers.EnderecoMapper;
import com.work.repositories.EnderecoRepository;
import com.work.services.EnderecoService;
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
public class EnderecoServiceImpl extends GenericServiceImpl<EnderecoRepository, EnderecoMapper> implements EnderecoService {


    @Override
    public EnderecoDTO getById(Long id) {
        Optional<Endereco> obj = getRepository().findById(id);
        obj.orElseThrow(() -> new ObjectNotFoundException(
                "Endereco não encontrado"));
        return getModelMapper().enderecotoEnderecoDTO(obj.get());
    }

    @Override
    public List<EnderecoDTO> getAll() {
        List<Endereco> obj = getRepository().findAll();
        return getModelMapper().listEnderecoTOListEnderecoDTO(obj);
    }

    @Override
    public EnderecoDTO insert(EnderecoDTO objDto) {
        Endereco obj = getModelMapper().enderecoDTOtoEndereco(objDto);
        obj.setId(null);
        Endereco endereco = getRepository().save(obj);
        return getModelMapper().enderecotoEnderecoDTO(endereco);
    }

    @Override
    public void update(EnderecoDTO objDto, Long id) {
        EnderecoDTO enderecoDTO = getById(id);
        updateData(enderecoDTO, objDto);
        Endereco endereco = getModelMapper().enderecoDTOtoEndereco(enderecoDTO);
        getRepository().save(endereco);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        try {
            getRepository().deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um ENDERECO que possua associações");
        }
    }

    @Override
    public Page<Endereco> findPage(Integer page, Integer linesPorPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPorPage, Sort.Direction.valueOf(direction), orderBy);
        return getRepository().findByEndereco(pageRequest);
    }

    private void updateData(EnderecoDTO newObj, EnderecoDTO obj) {
        newObj.setLogradouro(obj.getLogradouro());
        newObj.setNumero(obj.getNumero());
        newObj.setNumero(obj.getNumero());
        newObj.setBairro(obj.getBairro());
        newObj.setCep(obj.getCep());
    }
}
