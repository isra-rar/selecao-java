package com.work.controllers;

import com.work.dto.PostoProcessoDTO;
import com.work.entities.MediaValorCompraEVenda;
import com.work.entities.PostoProcesso;
import com.work.services.PostoProcessoService;
import com.work.services.excepctions.FailedSaveProcesso;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(value = "/api")
public class PostoProcessoController extends GenericController<PostoProcessoService>{

    @GetMapping(value = "/sync")
    public void syncCSV() throws FailedSaveProcesso, IOException {
        getService().saveAll();
    }

    @GetMapping(value = "/postos/{id}")
    public ResponseEntity<PostoProcesso> findById(@PathVariable Long id) {
        PostoProcesso obj = getService().getById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/postos/media-municipio")
    public Double getMediaCombustivelMunicipio(@RequestParam String municipio) {
        return getService().getMediaCombustivelByMunicipio(municipio);
    }

    @GetMapping(value = "/postos/media-municipio/valor-compra-venda")
    public MediaValorCompraEVenda getMediaValorCompraEVendaMunicipio(@RequestParam String municipio) {
        return getService().getMediaValorCompraEVendaByMunicipio(municipio);
    }

    @GetMapping(value = "/postos/media-bandeira/valor-compra-venda")
    public MediaValorCompraEVenda getMediaValorCompraEVendaBandeira(@RequestParam String bandeira) {
        return getService().getMediaValorCompraEVendaByBandeira(bandeira);
    }

    @GetMapping(value = "/postos/info-data-coleta")
    public ResponseEntity<Page<PostoProcesso>> findInfoByDataColeta(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPorPage", defaultValue = "10") Integer linesPorPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "data-coleta") String dataColeta) {

        Page<PostoProcesso> list = getService().getInfoByDataColeta(page, linesPorPage, orderBy, direction, dataColeta);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/postos/info-distribuidora")
    public ResponseEntity<Page<PostoProcesso>> findInfoByDistribuidora(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPorPage", defaultValue = "10") Integer linesPorPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "distribuidora") String distribuidora) {

        Page<PostoProcesso> list = getService().getInfoByDistribuidora(page, linesPorPage, orderBy, direction, distribuidora);
        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "/postos/sigla-regiao")
    public ResponseEntity<Page<PostoProcesso>> findInfoBySiglaRegiao(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPorPage", defaultValue = "10") Integer linesPorPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "sigla-regiao") String sigla) {

        Page<PostoProcesso> list = getService().getInfoBySiglaRegiao(page, linesPorPage, orderBy, direction, sigla);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/postos")
    public ResponseEntity<PostoProcessoDTO> insert(@Valid @RequestBody PostoProcessoDTO objDto) {
        PostoProcessoDTO postoProcessoDTO = getService().save(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(postoProcessoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(postoProcessoDTO);
    }

    @PutMapping(value = "/postos/{id}")
    public ResponseEntity<Void> update(@RequestBody PostoProcesso obj, @PathVariable Long id) {
        getService().update(obj, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/postos/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}
