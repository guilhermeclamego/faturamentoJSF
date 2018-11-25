
package br.com.fatura.dao.auditoria;

import br.com.fatura.dao.DaoDefault;
import br.com.fatura.model.Auditoria;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public interface AuditoriaDao extends DaoDefault {
    
    public abstract Object getAuditoria(int auditoriaid);
    
    public abstract List<Auditoria> buscarTodos();
}
