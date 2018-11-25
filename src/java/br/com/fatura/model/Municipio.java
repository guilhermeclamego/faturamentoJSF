
package br.com.fatura.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author guilherme
 */
public class Municipio implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private int codigo_ibge;
    private String descricao;
    private String uf;
    
    public Municipio(){
        //Construtor Vazio
    }

    public Municipio(int codigo_ibge, String descricao, String uf) {
        this.codigo_ibge = codigo_ibge;
        this.descricao = descricao;
        this.uf = uf;
    }

    public int getCodigo_ibge() {
        return codigo_ibge;
    }

    public void setCodigo_ibge(int codigo_ibge) {
        this.codigo_ibge = codigo_ibge;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.codigo_ibge;
        hash = 37 * hash + Objects.hashCode(this.descricao);
        hash = 37 * hash + Objects.hashCode(this.uf);
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
        final Municipio other = (Municipio) obj;
        if (this.codigo_ibge != other.codigo_ibge) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.uf, other.uf)) {
            return false;
        }
        return true;
    }
    
}
