
package br.com.fatura.dao;

/**
 *
 * @author guilherme
 */
import br.com.fatura.dao.auditoria.AuditoriaDao;
import br.com.fatura.dao.categoria.CategoriaDao;
import br.com.fatura.dao.cliente.ClienteDao;
import br.com.fatura.dao.funcionario.FuncionarioDao;
import br.com.fatura.dao.municipio.MunicipioDao;
import br.com.fatura.dao.pedidoCliente.PedidoClienteDao;
import br.com.fatura.dao.produto.ProdutoDao;
import br.com.fatura.dao.usuario.UsuarioDao;

public abstract class DaoFactory {

    public static final int JPA = 1;
    public static final int JDBC = 2;

    public abstract CategoriaDao getCategoriaDao();
    
    public abstract ProdutoDao getProdutoDao();
    
    public abstract ClienteDao getClienteDao();
    
    public abstract MunicipioDao getMunicipioDao();
    
    public abstract PedidoClienteDao getPedidoClienteDao();
    
    public abstract UsuarioDao getUsuarioDao();
    
    public abstract FuncionarioDao getFuncionarioDao();    
    
    public abstract AuditoriaDao getAuditoriaDao();


    public static DaoFactory getDaoFactory(int tipo) {
        switch (tipo) {
            case JPA: {
                return new JpaDaoFactory();
            }
            case JDBC: {
                return new JdbcDaoFactory();
            }
            default: {
                return null;
            }
        }
    }
}
