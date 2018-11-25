
package br.com.fatura.bean;

import br.com.fatura.model.Funcionario;
import br.com.fatura.model.Usuario;
import br.com.fatura.utils.Criptografia;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author guilherme
 */
@ManagedBean(name = "funcionarioBean")
@ViewScoped
public class FuncionarioBean extends AbstractBean implements Serializable {

    private Funcionario funcionario;
    private List<Funcionario> funcionarios;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @PostConstruct
    public void init() {
        funcionario = new Funcionario();
        atualizaLista();
    }

    public void salvar() throws NoSuchAlgorithmException {
        if (funcionario.getFuncionarioId() > 0) {
            if (daoFactory.getFuncionarioDao().update(funcionario)) {
                addInfoMessage("Salvo com Sucesso");
                funcionario = new Funcionario();
                atualizaLista();
            } else {
                addInfoMessage("Erro ao Salvar");
            }
        } else {
            funcionario.setFuncionarioId(daoFactory.getFuncionarioDao().getNextId());
            
            if(funcionario.isUsuario()) {
                Usuario usuario = new Usuario();
                usuario.setId(funcionario.getFuncionarioId());
                usuario.setLogin(funcionario.getLogin());
                usuario.setSenha(Criptografia.criptografar(funcionario.getSenha()));
                usuario.setNome(funcionario.getNome());

                if(!daoFactory.getUsuarioDao().insert(usuario)) {
                    addErrorMessage("Erro ao Salvar Usuário");
                }
            }            
            
            if (daoFactory.getFuncionarioDao().insert(funcionario)) {
                addInfoMessage("Salvo com Sucesso");
                funcionario = new Funcionario();
                atualizaLista();
            } else {
                addErrorMessage("Erro ao Salvar");
            }            
        }
    }
    
    public void excluir() {
        if(daoFactory.getFuncionarioDao().delete(funcionario)) {
            addInfoMessage("Excluído com Sucesso");
            funcionario = new Funcionario();
            atualizaLista();
        } else {
            addInfoMessage("Erro ao Salvar");
        }
    }
    
    public List<Funcionario> getFuncionarios() {
        try {
            return funcionarios;
        } catch (Exception e) {
            return null;
        }
    }

    private void atualizaLista() {
        this.funcionarios = daoFactory.getFuncionarioDao().buscarTodos();
    }
    
    public void alterar(Funcionario f) {
        this.funcionario = f;
    }

}
