
package br.com.fatura.dao.funcionario;

import br.com.fatura.dao.DaoDefault;
import br.com.fatura.model.Funcionario;
import java.util.List;

/**
 *
 * @author guilherme
 */
public interface FuncionarioDao extends DaoDefault {
    
    public abstract Object getFuncionario(int funcionarioid);
    
    public abstract List<Funcionario> buscarTodos();
    
    public abstract boolean cpfExistente(String cpf);
    
}
