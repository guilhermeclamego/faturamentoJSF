
package br.com.fatura.dao.categoria;

/**
 *
 * @author guilherme
 */
import br.com.fatura.dao.DaoDefault;
import br.com.fatura.model.Categoria;
import java.util.List;

public interface CategoriaDao extends DaoDefault{
    
    public abstract Object getCategoria(int id);
    
    public abstract List<Categoria> buscarTodos();
    
}
