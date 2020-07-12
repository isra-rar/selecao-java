package com.work.dto;

import com.opencsv.bean.CsvBindByPosition;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PostoProcessoDTO implements Serializable {

    private Long id;

    @CsvBindByPosition(position = 0)
    @NotNull
    private String regiaoSigla;
    @CsvBindByPosition(position = 1)
    @NotNull
    private String estadoSigla;
    @CsvBindByPosition(position = 2)
    @NotNull
    private String municipio;
    @CsvBindByPosition(position = 3)
    @NotNull
    private String revenda;
    @CsvBindByPosition(position = 4)
    @NotNull
    private String cnpj;
    @CsvBindByPosition(position = 5)
    @NotNull
    private String produto;
    @CsvBindByPosition(position = 6)
    private String dataColeta;
    @CsvBindByPosition(position = 7)
    private String valorVenda;
    @CsvBindByPosition(position = 8)
    private String valorCompra;
    @CsvBindByPosition(position = 9)
    @NotNull
    private String unidadeMedida;
    @CsvBindByPosition(position = 10)
    @NotNull
    private String bandeira;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegiaoSigla() {
        return regiaoSigla;
    }

    public void setRegiaoSigla(String regiaoSigla) {
        this.regiaoSigla = regiaoSigla;
    }

    public String getEstadoSigla() {
        return estadoSigla;
    }

    public void setEstadoSigla(String estadoSigla) {
        this.estadoSigla = estadoSigla;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getRevenda() {
        return revenda;
    }

    public void setRevenda(String revenda) {
        this.revenda = revenda;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(String dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(String valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(String valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
}
