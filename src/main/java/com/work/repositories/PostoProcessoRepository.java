package com.work.repositories;

import com.work.dto.PostoProcessoDTO;
import com.work.entities.MediaValorCompraEVenda;
import com.work.entities.PostoProcesso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostoProcessoRepository extends JpaRepository<PostoProcesso, Long> {

    //Implementar recurso que retorne a média de preço de combustível com base no nome do município
    //Implementar recurso que retorne o valor médio do valor da compra e do valor da venda por município
    @Transactional(readOnly = true)
    @Query(value = "SELECT ROUND(AVG(P.VALOR_VENDA),3) AS MEDIA_VALOR_COMBUSTIVEL FROM POSTO_PROCESSO AS P WHERE 1=1 AND P.MUNICIPIO = ?1", nativeQuery = true)
    Double mediaValorVendaByMunicipio(String MUNICIPIO);

    //Implementar recurso que retorne o valor médio do valor da compra e do valor da venda por município
    @Transactional(readOnly = true)
    @Query(value = "SELECT ROUND(AVG(P.VALOR_COMPRA),3) AS MEDIA_VALOR_COMBUSTIVEL FROM POSTO_PROCESSO AS P WHERE 1=1 AND P.MUNICIPIO = ?1", nativeQuery = true)
    Double mediaValorCompraByMunicipio(String MUNICIPIO);

    //Implementar recurso que retorne o valor médio do valor da compra e do valor da venda por bandeira
    @Transactional(readOnly = true)
    @Query(value = "SELECT ROUND(AVG(P.VALOR_VENDA),3) AS MEDIA_VALOR_COMBUSTIVEL FROM POSTO_PROCESSO AS P WHERE 1=1 AND P.BANDEIRA = ?1", nativeQuery = true)
    Double mediaValorVendaByBandeira(String BANDEIRA);

    //Implementar recurso que retorne o valor médio do valor da compra e do valor da venda por bandeira
    @Transactional(readOnly = true)
    @Query(value = "SELECT ROUND(AVG(P.VALOR_COMPRA),3) AS MEDIA_VALOR_COMBUSTIVEL FROM POSTO_PROCESSO AS P WHERE 1=1 AND P.BANDEIRA = ?1", nativeQuery = true)
    Double mediaValorCompraByBandeira(String BANDEIRA);

    //Implementar recurso que retorne todas as informações importadas por sigla da região
    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM POSTO_PROCESSO AS P WHERE 1=1 AND P.REGIAO_SIGLA = ?1", nativeQuery = true)
    Page<PostoProcesso> getInfoBySiglaRegiao(Pageable pageRequest, String REGIAO_SIGLA);

    //Implementar recurso que retorne os dados agrupados pela data da coleta
    @Transactional(readOnly = true)
    @Query(value = "SELECT P.* FROM POSTO_PROCESSO AS P WHERE 1=1 AND DATA_COLETA = ?1 GROUP BY P.DATA_COLETA, P.ID", nativeQuery = true)
    Page<PostoProcesso> getInfoByDataColeta(Pageable pageRequest, String DATA_COLETA);

    //Implementar recurso que retorne os dados agrupados por distribuidora
    @Transactional(readOnly = true)
    @Query(value = "SELECT P.* FROM POSTO_PROCESSO AS P WHERE 1=1 GROUP BY P.BANDEIRA, P.ID", nativeQuery = true)
    Page<PostoProcesso> getInfoByDistribuidora(Pageable pageRequest, String BANDEIRA);

//    @Transactional(readOnly = true)
//    @Query(value = "SELECT ROUND(AVG(P.VALOR_COMPRA),3) AS MEDIA_VALOR_COMPRA, ROUND(AVG(P.VALOR_VENDA),3) AS MEDIA_VALOR_VENDA FROM POSTO_PROCESSO AS P WHERE 1=1 AND P.MUNICIPIO = ?1", nativeQuery = true)
//    MediaValorCompraEVenda mediaValorCompraEVendaByMunicipio(String BANDEIRA);


}
