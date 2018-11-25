package br.com.fatura.dao;

import br.com.fatura.dao.auditoria.AuditoriaDao;
import br.com.fatura.dao.auditoria.AuditoriaJdbc;
import br.com.fatura.dao.categoria.CategoriaDao;
import br.com.fatura.dao.categoria.CategoriaJdbc;
import br.com.fatura.dao.cliente.ClienteDao;
import br.com.fatura.dao.cliente.ClienteJdbc;
import br.com.fatura.dao.funcionario.FuncionarioDao;
import br.com.fatura.dao.funcionario.FuncionarioJdbc;
import br.com.fatura.dao.municipio.MunicipioDao;
import br.com.fatura.dao.municipio.MunicipioJdbc;
import br.com.fatura.dao.pedidoCliente.PedidoClienteDao;
import br.com.fatura.dao.pedidoCliente.PedidoClienteJdbc;
import br.com.fatura.dao.produto.ProdutoDao;
import br.com.fatura.dao.produto.ProdutoJdbc;
import br.com.fatura.dao.usuario.UsuarioDao;
import br.com.fatura.dao.usuario.UsuarioJdbc;
import br.com.fatura.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author guilherme
 */
public class JdbcDaoFactory extends DaoFactory {

    private static Connection conexao;

    //criar a conexão
    private static Connection createConexao() {
        try {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.54.50:1521/XE", "faturamento", "faturamento");
            Class.forName("org.postgresql.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/faturamento", "postgres", "postgres");
            return conexao;
        } catch (Exception e) {
            return null;
        }
    }

    //Fecha conexão
    public static Connection fechaConexao() throws SQLException {
        if (conexao != null) {
            conexao.close();
        }
        return conexao;
    }

    //abre a conexão
    public static Connection abreConexao() throws SQLException {
        return conexao = createConexao();
    }

    @Override
    public CategoriaDao getCategoriaDao() {
        return new CategoriaJdbc();
    }

    @Override
    public ProdutoDao getProdutoDao() {
        return new ProdutoJdbc();
    }

    @Override
    public ClienteDao getClienteDao() {
        return new ClienteJdbc();
    }

    @Override
    public MunicipioDao getMunicipioDao() {
        return new MunicipioJdbc();
    }

    @Override
    public PedidoClienteDao getPedidoClienteDao() {
        return new PedidoClienteJdbc();
    }

    @Override
    public UsuarioDao getUsuarioDao() {
        return new UsuarioJdbc();
    }

    @Override
    public FuncionarioDao getFuncionarioDao() {
        return new FuncionarioJdbc();
    }
    
   @Override
    public AuditoriaDao getAuditoriaDao() {
        return new AuditoriaJdbc();
    }
}
