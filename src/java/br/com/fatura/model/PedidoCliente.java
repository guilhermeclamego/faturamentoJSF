/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatura.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author guilherme
 */
public class PedidoCliente implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Cliente cliente;
    private Produto produto;
    private int status;
    private double quantidade;
    private double precoVenda;
    
    public PedidoCliente(){
        //construtor vazio
    }

    public PedidoCliente(Cliente cliente, Produto produto, int status, double quantidade, double precoVenda) {
        this.cliente = cliente;
        this.produto = produto;
        this.status = status;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.cliente);
        hash = 41 * hash + Objects.hashCode(this.produto);
        hash = 41 * hash + this.status;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.quantidade) ^ (Double.doubleToLongBits(this.quantidade) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.precoVenda) ^ (Double.doubleToLongBits(this.precoVenda) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PedidoCliente other = (PedidoCliente) obj;
        if (this.status != other.status) {
            return false;
        }
        if (Double.doubleToLongBits(this.quantidade) != Double.doubleToLongBits(other.quantidade)) {
            return false;
        }
        if (Double.doubleToLongBits(this.precoVenda) != Double.doubleToLongBits(other.precoVenda)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }

}