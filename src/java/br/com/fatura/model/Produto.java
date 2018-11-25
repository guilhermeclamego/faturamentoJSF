package br.com.fatura.model;

import java.util.Objects;

/**
 *
 * @author guilherme
 */
public class Produto {
    private static final long serialVersionUID = 1L;
       
    private int id;
    private String descricao;
    private double valorCusto;
    private int estoque;
    private Categoria categoria;
    
    public Produto(){
        //construtor vazio
    }

    public Produto(int id, String descricao, double valorCusto, int estoque, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valorCusto = valorCusto;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.descricao);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.valorCusto) ^ (Double.doubleToLongBits(this.valorCusto) >>> 32));
        hash = 23 * hash + this.estoque;
        hash = 23 * hash + Objects.hashCode(this.categoria);
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
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorCusto) != Double.doubleToLongBits(other.valorCusto)) {
            return false;
        }
        if (this.estoque != other.estoque) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return true;
    }
    
    
    
}
