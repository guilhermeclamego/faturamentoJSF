
package br.com.fatura.dao.municipio;

import br.com.fatura.dao.DaoDefault;
import br.com.fatura.model.Municipio;
import java.util.List;

/**
 *
 * @author guilherme
 */
public interface MunicipioDao extends DaoDefault{
    
    public abstract Object getMunicipio(int id);
    
    public abstract List<Municipio> buscarTodos();
    public abstract List<Municipio> buscarTodosSC();
    
}