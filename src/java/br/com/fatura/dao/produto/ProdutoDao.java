package br.com.fatura.dao.produto;

import br.com.fatura.dao.DaoDefault;
import br.com.fatura.model.Produto;
import java.util.List;

/**
 *
 * @author guilherme
 */
public interface ProdutoDao extends DaoDefault{
    
    public abstract List<Produto> buscarTodos();
    
    public abstract Object getProduto(int id);
    
}

