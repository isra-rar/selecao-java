package com.work.services.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import com.work.dto.PostoProcessoDTO;
import com.work.entities.MediaValorCompraEVenda;
import com.work.entities.PostoProcesso;
import com.work.mappers.PostoProcessoMapper;
import com.work.repositories.PostoProcessoRepository;
import com.work.services.PostoProcessoService;
import com.work.services.excepctions.DataIntegrityException;
import com.work.services.excepctions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class PostoProcessoImpl extends GenericServiceImpl<PostoProcessoRepository, PostoProcessoMapper> implements PostoProcessoService {

    @Override
    public PostoProcesso getById(Long id) {
        Optional<PostoProcesso> obj = getRepository().findById(id);
        obj.orElseThrow(() -> new ObjectNotFoundException(
                "POSTO não encontrado"));
        return obj.get();
    }

    @Override
    public Double getMediaCombustivelByMunicipio(String municipio) {
        Double mediaMunicipio = getRepository().mediaValorVendaByMunicipio(municipio);
        if (mediaMunicipio == null) {
            throw new ObjectNotFoundException("Não existem dados para esse municipio");
        }
        return mediaMunicipio;
    }

    @Override
    public MediaValorCompraEVenda getMediaValorCompraEVendaByMunicipio(String municipio) {
        Double mediaValorVendaMunicipio = getRepository().mediaValorVendaByMunicipio(municipio);
        Double mediaValorCompraMunicipio = getRepository().mediaValorCompraByMunicipio(municipio);
        if (mediaValorCompraMunicipio == null || mediaValorVendaMunicipio == null){
            throw new ObjectNotFoundException("Dados inconsistentes");
        }

        MediaValorCompraEVenda mediaValorCompraEVenda = new MediaValorCompraEVenda(mediaValorVendaMunicipio, mediaValorCompraMunicipio);
        return mediaValorCompraEVenda;
    }

    @Override
    public MediaValorCompraEVenda getMediaValorCompraEVendaByBandeira(String bandeira) {
        Double mediaValorVendaBandeira = getRepository().mediaValorVendaByBandeira(bandeira);
        Double mediaValorCompraBandeira = getRepository().mediaValorCompraByBandeira(bandeira);
        MediaValorCompraEVenda mediaValorCompraEVenda = new MediaValorCompraEVenda(mediaValorVendaBandeira, mediaValorCompraBandeira);
        if (mediaValorVendaBandeira == null || mediaValorCompraBandeira == null){
            throw new ObjectNotFoundException("Dados inconsistentes");
        }
        return mediaValorCompraEVenda;
    }

    @Override
    public Page<PostoProcesso> getInfoByDataColeta(Integer page, Integer linesPorPage, String orderBy, String direction, String dataColeta) {
        PageRequest pageRequest = PageRequest.of(page, linesPorPage, Sort.Direction.valueOf(direction), orderBy);
        Page<PostoProcesso> postoProcessos = getRepository().getInfoByDataColeta(pageRequest, dataColeta);
        if (postoProcessos.getContent().isEmpty()) {
            throw new ObjectNotFoundException("Não existem dados para essa Data de Coleta");
        }
        return getRepository().getInfoByDataColeta(pageRequest, dataColeta);
    }


    @Override
    public Page<PostoProcesso> getInfoBySiglaRegiao(Integer page, Integer linesPorPage, String orderBy, String direction, String sigla) {
        PageRequest pageRequest = PageRequest.of(page, linesPorPage, Sort.Direction.valueOf(direction), orderBy);
        Page<PostoProcesso> postoProcessos = getRepository().getInfoBySiglaRegiao(pageRequest, sigla);
        if (postoProcessos.getContent().isEmpty()) {
            throw new ObjectNotFoundException("Não existem dados para essa Sigla");
        }

        return getRepository().getInfoBySiglaRegiao(pageRequest, sigla);
    }

    @Override
    public Page<PostoProcesso> getInfoByDistribuidora(Integer page, Integer linesPorPage, String orderBy, String direction, String bandeira) {
        PageRequest pageRequest = PageRequest.of(page, linesPorPage, Sort.Direction.valueOf(direction), orderBy);
        Page<PostoProcesso> postoProcessos = getRepository().getInfoByDistribuidora(pageRequest, bandeira);
        if (postoProcessos.getContent().isEmpty()) {
            throw new ObjectNotFoundException("Não existem dados para essa Distribuidora");
        }
        return getRepository().getInfoByDistribuidora(pageRequest, bandeira);
    }


    @Override
    public PostoProcessoDTO save(PostoProcessoDTO objDto) {
        PostoProcesso obj = getModelMapper().postoProcessoDtoToPostoProcesso(objDto);
        obj.setId(null);
        PostoProcesso cliente = getRepository().save(obj);
        return getModelMapper().postoProcessoToPostoProcessoDTO(cliente);
    }

    @Override
    public void saveAll() throws FileNotFoundException {

        String file = System.getProperty("user.dir") + "/src/main/resources/processosData.csv";

        List<PostoProcessoDTO> postoProcessoDTOList = new CsvToBeanBuilder(new FileReader(file))
                .withType(PostoProcessoDTO.class).withSkipLines(1).withSeparator('\t').build().parse();


        for (int i = 0; i < postoProcessoDTOList.size(); i++) {
            if (postoProcessoDTOList.get(i).getBandeira() == null && postoProcessoDTOList.get(i).getRevenda() == null && postoProcessoDTOList.get(i).getCnpj() == null) {
                postoProcessoDTOList.remove(i);
            }
        }


        for (PostoProcessoDTO postoProcessoDTO : postoProcessoDTOList) {
            if (postoProcessoDTO.getValorCompra() == null || (postoProcessoDTO.getValorCompra() != null && postoProcessoDTO.getValorCompra().isEmpty())) {
                postoProcessoDTO.setValorCompra("3,254");
            }

            try {
                postoProcessoDTO.setValorCompra(postoProcessoDTO.getValorCompra()
                        .replace(",", "."));
                postoProcessoDTO.setValorVenda(postoProcessoDTO.getValorVenda()
                        .replace(",", "."));
                postoProcessoDTO.setRegiaoSigla(postoProcessoDTO.getRegiaoSigla().trim());
            } catch (NullPointerException e) {
                throw new DataIntegrityException("Erro ao tratar os dados do CSV");
            }

        }

        getRepository().saveAll(getModelMapper().listPostoProcessoDTOTOListPostProcesso(postoProcessoDTOList));
    }

    @Override
    public void update(PostoProcesso objDto, Long id) {
        PostoProcesso postoProcesso = getById(id);
        updateData(postoProcesso, objDto);
        getRepository().save(postoProcesso);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        try {
            getRepository().deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um POSTO que possua associações");
        }
    }

    private void updateData(PostoProcesso newObj, PostoProcesso obj) {
        newObj.setRegiaoSigla(obj.getRegiaoSigla());
        newObj.setEstadoSigla(obj.getEstadoSigla());
        newObj.setMunicipio(obj.getMunicipio());
        newObj.setRevenda(obj.getRevenda());
        newObj.setCnpj(obj.getCnpj());
        newObj.setProduto(obj.getProduto());
        newObj.setDataColeta(obj.getDataColeta());
        newObj.setValorVenda(obj.getValorVenda());
        newObj.setValorCompra(obj.getValorCompra());
        newObj.setUnidadeMedida(obj.getUnidadeMedida());
        newObj.setBandeira(obj.getBandeira());
    }
}
