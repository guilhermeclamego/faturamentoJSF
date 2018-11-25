package br.com.fatura.dao.pedidoCliente;

import br.com.fatura.dao.JdbcDaoFactory;
import br.com.fatura.model.PedidoCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author guilherme
 */
public class PedidoClienteJdbc implements PedidoClienteDao {

    private Connection conexao;
    private PreparedStatement stmt;

    @Override
    public boolean insert(Object oObjeto) {
        PedidoCliente oPedidoCliente = (PedidoCliente) oObjeto;
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("insert into pedidocliente (clienteid, produtoid, status, quantidade, precovenda) values (?,?,?,?,?)");

            stmt.setInt(1, oPedidoCliente.getCliente().getId());
            stmt.setInt(2, oPedidoCliente.getProduto().getId());
            stmt.setInt(3, 0);
            stmt.setDouble(4, oPedidoCliente.getQuantidade());
            stmt.setDouble(5, oPedidoCliente.getPrecoVenda());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                stmt.close();
                conexao = JdbcDaoFactory.fechaConexao();
            } catch (SQLException ex) {

            }
        }
    }

    @Override
    public boolean delete(Object oObjeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object oObjeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
