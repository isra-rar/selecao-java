package com.work.entities;


import java.io.Serializable;

public class MediaValorCompraEVenda implements Serializable {

    private Double mediaValorVenda;

    private Double mediaValorCompra;

    public MediaValorCompraEVenda() {
    }

    public MediaValorCompraEVenda(Double mediaValorVenda, Double mediaValorCompra) {
        this.mediaValorVenda = mediaValorVenda;
        this.mediaValorCompra = mediaValorCompra;
    }

    public Double getMediaValorVenda() {
        return mediaValorVenda;
    }

    public void setMediaValorVenda(Double mediaValorVenda) {
        this.mediaValorVenda = mediaValorVenda;
    }

    public Double getMediaValorCompra() {
        return mediaValorCompra;
    }

    public void setMediaValorCompra(Double mediaValorCompra) {
        this.mediaValorCompra = mediaValorCompra;
    }
}