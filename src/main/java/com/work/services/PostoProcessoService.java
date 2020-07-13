package com.work.services;

import com.work.dto.PostoProcessoDTO;
import com.work.entities.MediaValorCompraEVenda;
import com.work.entities.PostoProcesso;
import com.work.services.excepctions.FailedSaveProcesso;
import org.springframework.data.domain.Page;

import java.io.FileNotFoundException;

public interface PostoProcessoService extends GenericService{

    PostoProcesso getById(Long id);

    Double getMediaCombustivelByMunicipio(String municipio);

    MediaValorCompraEVenda getMediaValorCompraEVendaByMunicipio(String municipio);

    MediaValorCompraEVenda getMediaValorCompraEVendaByBandeira(String bandeira);

    Page<PostoProcesso> getInfoByDataColeta(Integer page, Integer linesPorPage, String orderBy, String direction,String dataColeta);

    Page<PostoProcesso> getInfoBySiglaRegiao(Integer page, Integer linesPorPage, String orderBy, String direction, String sigla);

    Page<PostoProcesso> getInfoByDistribuidora(Integer page, Integer linesPorPage, String orderBy, String direction, String distribuidora);

    PostoProcesso save(PostoProcesso postoProcessoDTO);

    void saveAll() throws FailedSaveProcesso, FileNotFoundException;

    void update(PostoProcesso objDto, Long id);

    void delete(Long id);

}
