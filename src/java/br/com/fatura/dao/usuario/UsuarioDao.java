
package br.com.fatura.dao.usuario;

import br.com.fatura.dao.DaoDefault;

/**
 *
 * @author guilherme
 */
public interface UsuarioDao extends DaoDefault{
    
    public abstract Object getUsuario(int id);
    
     public abstract boolean alteraSenha(String senha);
    
}
