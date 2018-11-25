
package br.com.fatura.dao.categoria;

import br.com.fatura.dao.JdbcDaoFactory;
import br.com.fatura.model.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilherme
 */
//Conexão com o banco para crud
public class CategoriaJdbc implements CategoriaDao {

    private Connection conexao;
    private PreparedStatement stmt;
    //Carregar objeto categoria
    @Override
    public Object getCategoria(int id) {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select * from categoria where id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            Categoria oCategoria = new Categoria();
            oCategoria.setId(rs.getInt("id"));
            oCategoria.setDescricao(rs.getString("descricao"));
            return oCategoria;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                stmt.close();
                conexao = JdbcDaoFactory.fechaConexao(); //JdbcDaoFactory está com o método para fechar conexao...
            } catch (SQLException ex) {

            }
        }
    }
    //Carregar lista de categoria
    @Override
    public List<Categoria> buscarTodos() {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("SELECT ID, "
                    + "  descricao "
                    + "FROM categoria\n"
                    + "ORDER BY id");

            ResultSet rs = stmt.executeQuery();

            ArrayList<Categoria> todos = new ArrayList<Categoria>();

            while (rs.next()) {
                Categoria oCategoria = new Categoria();
                oCategoria.setId(rs.getInt("ID"));
                oCategoria.setDescricao(rs.getString("descricao"));
                todos.add(oCategoria);
            }
            return todos;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                stmt.close();
                conexao = JdbcDaoFactory.fechaConexao();
            } catch (SQLException ex) {

            }
        }
    }
    //inserir categoria nova
    @Override
    public boolean insert(Object oObjeto) {
        Categoria oCategoria = (Categoria) oObjeto;
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("insert into categoria ("
                    + " id, "
                    + " descricao)"
                    + "values("
                    + " ?, "
                    + " ?)");

            stmt.setInt(1, oCategoria.getId());
            stmt.setString(2, oCategoria.getDescricao());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                stmt.close();
                conexao = JdbcDaoFactory.fechaConexao();
            } catch (SQLException ex) {

            }
        }
    }
    //deletar categoria passando objeto como parametro
    @Override
    public boolean delete(Object oObjeto) {
        Categoria oCategoria = (Categoria) oObjeto;
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("delete from categoria where id = ?");

            stmt.setInt(1, oCategoria.getId());
            stmt.execute(); 
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                stmt.close();
                conexao = JdbcDaoFactory.fechaConexao();
            } catch (SQLException ex) {

            }
        }
    }
    //alteração de categoria
    @Override
    public boolean update(Object oObjeto) {
        Categoria oCategoria = (Categoria) oObjeto;
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("update categoria\n"
                    + "set descricao = ?\n"
                    + "where (id = ?)");

            stmt.setString(1, oCategoria.getDescricao());
            stmt.setInt(2, oCategoria.getId());
            stmt.execute();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                stmt.close();
                conexao = JdbcDaoFactory.fechaConexao();
            } catch (SQLException ex) {

            }
        }
    }
    //'generator' controlado pela classe 
    @Override
    public int getNextId() {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select coalesce(max(id), 0) + 1 codigo from categoria");
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("codigo");
        } catch (Exception e) {
            return 0;
        } finally {
            try {
                stmt.close();
                conexao = JdbcDaoFactory.fechaConexao();
            } catch (SQLException ex) {

            }
        }
    }
}
