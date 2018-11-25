package br.com.fatura.dao.produto;

import br.com.fatura.dao.JdbcDaoFactory;
import br.com.fatura.model.Categoria;
import br.com.fatura.model.Produto;
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
public class ProdutoJdbc implements ProdutoDao {

    private Connection conexao;
    private PreparedStatement stmt;

    public List<Produto> buscarTodos() {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select \n"
                    + "  produto.id,\n"
                    + "  produto.descricao,\n"
                    + "  produto.valorcusto,\n"
                    + "  produto.estoque,\n"
                    + "  categoria.id catId,\n"
                    + "  categoria.descricao catDes\n"
                    + "from \n"
                    + "  produto, categoria\n"
                    + "where \n"
                    + "  produto.categoriaid = categoria.id");

            ResultSet rs = stmt.executeQuery();

            ArrayList<Produto> todos = new ArrayList<Produto>();

            while (rs.next()) {
                Produto oProduto = new Produto();
                Categoria oCategoria = new Categoria();

                oProduto.setId(rs.getInt("id"));
                oProduto.setDescricao(rs.getString("descricao"));
                oProduto.setValorCusto(rs.getDouble("valorCusto"));
                oProduto.setEstoque(rs.getInt("estoque"));

                oCategoria.setId(rs.getInt("catId"));
                oCategoria.setDescricao(rs.getString("catDes"));

                oProduto.setCategoria(oCategoria);
                todos.add(oProduto);
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

    @Override
    public Object getProduto(int id) {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select \n"
                    + "  produto.id,\n"
                    + "  produto.descricao,\n"
                    + "  produto.valorcusto,\n"
                    + "  produto.estoque,\n"
                    + "  categoria.id catId,\n"
                    + "  categoria.descricao catDes\n"
                    + "from \n"
                    + "  produto, categoria\n"
                    + "where \n"
                    + "  produto.categoriaid = categoria.id and produto.id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            Produto oProduto = new Produto();
            Categoria oCategoria = new Categoria();

            oProduto.setId(rs.getInt("id"));
            oProduto.setDescricao(rs.getString("descricao"));
            oProduto.setValorCusto(rs.getDouble("valorCusto"));
            oProduto.setEstoque(rs.getInt("estoque"));

            oCategoria.setId(rs.getInt("catId"));
            oCategoria.setDescricao(rs.getString("catDes"));

            oProduto.setCategoria(oCategoria);
            return oProduto;
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

    @Override
    public boolean insert(Object oObjeto) {
        Produto oProduto = (Produto) oObjeto;
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("insert into produto (id, descricao, valorcusto, estoque, categoriaid) values (?,?,?,?,?)");

            stmt.setInt(1, oProduto.getId());
            stmt.setString(2, oProduto.getDescricao());
            stmt.setDouble(3, oProduto.getValorCusto());
            stmt.setInt(4, oProduto.getEstoque());
            stmt.setInt(5, oProduto.getCategoria().getId());
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

    @Override
    public boolean delete(Object oObjeto) {
        Produto oPRoduto = (Produto) oObjeto;
        try{
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("delete from produto where id = ?");
            
            stmt.setInt(1, oPRoduto.getId());
            stmt.execute();
            
            return true;
        }catch(SQLException e){
            return false;
        }finally{
            try{
                stmt.close();
                conexao = JdbcDaoFactory.fechaConexao();
            }catch(SQLException ex){
                
            }
        }
       
    }

    @Override
    public boolean update(Object oObjeto) {
        Produto oProduto = (Produto) oObjeto;
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("update \n"
                    + "  produto\n"
                    + "set \n"
                    + "  descricao   = ?,\n"
                    + "  valorcusto  = ?,\n"
                    + "  estoque     = ?,\n"
                    + "  categoriaid = ?\n"
                    + "where \n"
                    + "  id = ?");

            stmt.setInt(5, oProduto.getId());
            stmt.setString(1, oProduto.getDescricao());
            stmt.setDouble(2, oProduto.getValorCusto());
            stmt.setInt(3, oProduto.getEstoque());
            stmt.setInt(4, oProduto.getCategoria().getId());
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

    @Override
    public int getNextId() {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select coalesce(max(id), 1) + 1 codigo from produto");
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
