package com.work.mappers;

import com.work.dto.PostoProcessoDTO;
import com.work.entities.PostoProcesso;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-12T19:38:08-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_252 (Private Build)"
)
@Component
public class PostoProcessoMapperImpl implements PostoProcessoMapper {

    @Override
    public PostoProcessoDTO postoProcessoToPostoProcessoDTO(PostoProcesso postoProcesso) {
        if ( postoProcesso == null ) {
            return null;
        }

        PostoProcessoDTO postoProcessoDTO = new PostoProcessoDTO();

        postoProcessoDTO.setId( postoProcesso.getId() );
        postoProcessoDTO.setRegiaoSigla( postoProcesso.getRegiaoSigla() );
        postoProcessoDTO.setEstadoSigla( postoProcesso.getEstadoSigla() );
        postoProcessoDTO.setMunicipio( postoProcesso.getMunicipio() );
        postoProcessoDTO.setRevenda( postoProcesso.getRevenda() );
        postoProcessoDTO.setCnpj( postoProcesso.getCnpj() );
        postoProcessoDTO.setProduto( postoProcesso.getProduto() );
        postoProcessoDTO.setDataColeta( postoProcesso.getDataColeta() );
        if ( postoProcesso.getValorVenda() != null ) {
            postoProcessoDTO.setValorVenda( String.valueOf( postoProcesso.getValorVenda() ) );
        }
        if ( postoProcesso.getValorCompra() != null ) {
            postoProcessoDTO.setValorCompra( String.valueOf( postoProcesso.getValorCompra() ) );
        }
        postoProcessoDTO.setUnidadeMedida( postoProcesso.getUnidadeMedida() );
        postoProcessoDTO.setBandeira( postoProcesso.getBandeira() );

        return postoProcessoDTO;
    }

    @Override
    public PostoProcesso postoProcessoDtoToPostoProcesso(PostoProcessoDTO postoProcessoDTO) {
        if ( postoProcessoDTO == null ) {
            return null;
        }

        PostoProcesso postoProcesso = new PostoProcesso();

        postoProcesso.setId( postoProcessoDTO.getId() );
        postoProcesso.setRegiaoSigla( postoProcessoDTO.getRegiaoSigla() );
        postoProcesso.setEstadoSigla( postoProcessoDTO.getEstadoSigla() );
        postoProcesso.setMunicipio( postoProcessoDTO.getMunicipio() );
        postoProcesso.setRevenda( postoProcessoDTO.getRevenda() );
        postoProcesso.setCnpj( postoProcessoDTO.getCnpj() );
        postoProcesso.setProduto( postoProcessoDTO.getProduto() );
        postoProcesso.setDataColeta( postoProcessoDTO.getDataColeta() );
        if ( postoProcessoDTO.getValorVenda() != null ) {
            postoProcesso.setValorVenda( Double.parseDouble( postoProcessoDTO.getValorVenda() ) );
        }
        if ( postoProcessoDTO.getValorCompra() != null ) {
            postoProcesso.setValorCompra( Double.parseDouble( postoProcessoDTO.getValorCompra() ) );
        }
        postoProcesso.setUnidadeMedida( postoProcessoDTO.getUnidadeMedida() );
        postoProcesso.setBandeira( postoProcessoDTO.getBandeira() );

        return postoProcesso;
    }

    @Override
    public List<PostoProcessoDTO> listPostoProcessoTOListPostProcessoDTO(List<PostoProcesso> postosProcessos) {
        if ( postosProcessos == null ) {
            return null;
        }

        List<PostoProcessoDTO> list = new ArrayList<PostoProcessoDTO>( postosProcessos.size() );
        for ( PostoProcesso postoProcesso : postosProcessos ) {
            list.add( postoProcessoToPostoProcessoDTO( postoProcesso ) );
        }

        return list;
    }

    @Override
    public List<PostoProcesso> listPostoProcessoDTOTOListPostProcesso(List<PostoProcessoDTO> postosProcessos) {
        if ( postosProcessos == null ) {
            return null;
        }

        List<PostoProcesso> list = new ArrayList<PostoProcesso>( postosProcessos.size() );
        for ( PostoProcessoDTO postoProcessoDTO : postosProcessos ) {
            list.add( postoProcessoDtoToPostoProcesso( postoProcessoDTO ) );
        }

        return list;
    }
}
