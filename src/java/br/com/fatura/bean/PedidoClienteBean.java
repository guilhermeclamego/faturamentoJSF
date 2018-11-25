package br.com.fatura.bean;

import br.com.fatura.model.PedidoCliente;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author guilherme
 */
@ManagedBean(name = "pedidoClienteBean")
@ViewScoped
public class PedidoClienteBean extends AbstractBean implements Serializable {

    private PedidoCliente pedidoCliente;

    @PostConstruct
    public void init() {
        pedidoCliente = new PedidoCliente();
        //atualizaLista();
    }

    public void salvar() {
        if (daoFactory.getPedidoClienteDao().insert(pedidoCliente)) {
            addInfoMessage("Salvo com Sucesso");
            pedidoCliente = new PedidoCliente();
            //atualizaLista();
        } else {
            addErrorMessage("Erro ao Salvar");
        }
    }
    public PedidoCliente getPedidoCliente() {
        try {
            return pedidoCliente;
        } catch (Exception e) {
            return null;
        }
    }

    public void setPedidoCliente(PedidoCliente pedidoCliente) {
        this.pedidoCliente = pedidoCliente;
    }

}
