package br.com.fatura.bean;

import br.com.fatura.dao.DaoFactory;
import br.com.fatura.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author carlos
 */
@ManagedBean(name = "templateBean")
@ViewScoped
public class TemplateBean extends AbstractBean implements Serializable {
    
    private String senha;
    private String confirmaSenha;
    private UsuarioBean usuario;
    
    @PostConstruct
    public void init() {
        usuario = new UsuarioBean();
    }
    
    public void alterarSenha() {
        System.out.println("aqui");
        if(!(senha.equals(confirmaSenha))) {
            addErrorMessage("Senha são diferentes");
        } else {
            usuario.alterarSenha();
        }
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
    
}
