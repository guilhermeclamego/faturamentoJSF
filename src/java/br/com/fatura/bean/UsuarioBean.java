
package br.com.fatura.bean;

import br.com.fatura.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Carlos
 */
@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean extends AbstractBean implements Serializable {

    private Usuario usuario;
    private String senha;
    private String confirmaSenha;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public void salvar() {
        if (usuario.getId() > 0) {
            if (daoFactory.getUsuarioDao().update(usuario)) {
                addInfoMessage("Salvo com Sucesso");
                usuario = new Usuario();
            } else {
                addInfoMessage("Erro ao Salvar");
            }
        } else {
            usuario.setId(daoFactory.getFuncionarioDao().getNextId());
            if (daoFactory.getFuncionarioDao().insert(usuario)) {
                addInfoMessage("Salvo com Sucesso");
                usuario = new Usuario();
            } else {
                addErrorMessage("Erro ao Salvar");
            }
        }
    }
    
    public void excluir() {
        if(daoFactory.getUsuarioDao().delete(usuario)) {
            addInfoMessage("Excluído com Sucesso");
            usuario = new Usuario();
        } else {
            addInfoMessage("Erro ao Salvar");
        }
    }

    public void alterar(Usuario u) {
        this.usuario = u;
    }
    
    public void alterarSenha() {
        System.out.println("asdasdasd");
        if(daoFactory.getUsuarioDao().alteraSenha(senha)) {
            addInfoMessage("Senha alterada com Sucesso");
        } else {
            addErrorMessage("Erro ao alterar senha");
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
