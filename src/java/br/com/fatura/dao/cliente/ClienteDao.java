
package br.com.fatura.dao.cliente;

import br.com.fatura.dao.DaoDefault;
import br.com.fatura.model.Cliente;
import java.util.List;

/**
 *
 * @author guilherme
 */
public interface ClienteDao extends DaoDefault{
    
    public abstract Object getCliente(int id);
    
    public abstract List<Cliente> buscarTodos();
    
    public abstract boolean cpfExistente(String cpf);
    
}

