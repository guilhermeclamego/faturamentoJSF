package br.com.fatura.bean;

import br.com.fatura.model.Cliente;
import br.com.fatura.model.Municipio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author guilherme
 */
@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean extends AbstractBean implements Serializable {

    private Cliente cliente;
    private List<Municipio> municipios;
    private List<Cliente> clientes;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @PostConstruct
    public void init() {
        cliente = new Cliente();
        atualizaLista();
    }

    public void salvar() {
        if (cliente.getId() > 0) {
            if (daoFactory.getClienteDao().update(cliente)) {
                addInfoMessage("Salvo com Sucesso");
                cliente = new Cliente();
                atualizaLista();
            } else {
                addErrorMessage("Erro ao Salvar");
            }
        } else {
            cliente.setId(daoFactory.getClienteDao().getNextId());
            if (daoFactory.getClienteDao().insert(cliente)) {
                addInfoMessage("Salvo com Sucesso");
                cliente = new Cliente();
                atualizaLista();
            } else {
                addErrorMessage("Erro ao Salvar");
            }
        }
    }

    public void excluir(Cliente cliente) {
        this.cliente = cliente;
        if (daoFactory.getClienteDao().delete(cliente)) {
            addInfoMessage("Excluído com Sucesso");
            init();
        } else {
            addInfoMessage("Erro ao Excluir");
        }
    }

    public List<Municipio> getMunicipios() {
        try {
            return municipios;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Cliente> getClientes() {
        try {
            return clientes;
        } catch (Exception e) {
            return null;
        }
    }

    private void atualizaLista() {
        this.municipios = daoFactory.getMunicipioDao().buscarTodosSC();
        this.clientes = daoFactory.getClienteDao().buscarTodos();
    }

    public void alterar(Cliente c) {
        this.cliente = c;
    }
}
