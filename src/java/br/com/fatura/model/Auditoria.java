package br.com.fatura.model;

import java.util.Objects;

/**
 *
 * @author Guilherme
 */
public class Auditoria {
    
    private int auditoriaId;
    private String operacao;
    private String data;
    private String usuario;
    private String tabela;
    private String dados;
    
    public Auditoria(){
        //construtor vazio
    }

    public Auditoria(int auditoriaId, String operacao, String data, String usuario, String tabela, String dados) {
        this.auditoriaId = auditoriaId;
        this.operacao = operacao;
        this.data = data;
        this.usuario = usuario;
        this.tabela = tabela;
        this.dados = dados;
    }
    
    public int getAuditoriaId() {
        return auditoriaId;
    }

    public void setAuditoriaId(int auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.auditoriaId;
        hash = 97 * hash + Objects.hashCode(this.operacao);
        hash = 97 * hash + Objects.hashCode(this.data);
        hash = 97 * hash + Objects.hashCode(this.usuario);
        hash = 97 * hash + Objects.hashCode(this.tabela);
        hash = 97 * hash + Objects.hashCode(this.dados);
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
        final Auditoria other = (Auditoria) obj;
        if (this.auditoriaId != other.auditoriaId) {
            return false;
        }
        if (!Objects.equals(this.operacao, other.operacao)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.tabela, other.tabela)) {
            return false;
        }
        if (!Objects.equals(this.dados, other.dados)) {
            return false;
        }
        return true;
    }
    
    
    
}
