package br.com.fatura.model;

import java.util.Objects;

/**
 *
 * @author guilherme
 */
public class Funcionario {
    
    private int funcionarioId;
    private String cpf;
    private String nome;
    private String endereco;
    private String email;
    private boolean usuario;
    private String login;
    private String senha;
    private String confirmaSenha;
    
    public Funcionario(){
        //Construtor vazio
    }

    public Funcionario(int funcionarioId, String cpf, String nome, String endereco, String email, boolean usuario) {
        this.funcionarioId = funcionarioId;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.usuario = usuario;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUsuario() {
        return usuario;
    }

    public void setUsuario(boolean usuario) {
        this.usuario = usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.funcionarioId;
        hash = 37 * hash + Objects.hashCode(this.cpf);
        hash = 37 * hash + Objects.hashCode(this.nome);
        hash = 37 * hash + Objects.hashCode(this.endereco);
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + (this.usuario ? 1 : 0);
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
        final Funcionario other = (Funcionario) obj;
        if (this.funcionarioId != other.funcionarioId) {
            return false;
        }
        if (this.usuario != other.usuario) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
   
}
