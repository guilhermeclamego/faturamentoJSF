package br.com.fatura.dao.usuario;

import br.com.fatura.dao.JdbcDaoFactory;
import br.com.fatura.model.Usuario;
import br.com.fatura.utils.Criptografia;
import br.com.fatura.utils.SessionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author guilherme
 */
public class UsuarioJdbc implements UsuarioDao {

    private Connection conexao;
    private PreparedStatement stmt;

    @Override
    public Object getUsuario(int id) {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select * from usuario where id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            Usuario oUsuario = new Usuario();
            oUsuario.setId(rs.getInt("id"));
            oUsuario.setNome(rs.getString("nome"));
            oUsuario.setLogin(rs.getString("login"));
            oUsuario.setSenha(rs.getString("senha"));

            return oUsuario;
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
    
    public boolean insert(Object oObject){
        Usuario oUsuario = (Usuario) oObject;
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("insert into usuario ("
                    + " usuarioid, "
                    + " nome, "
                    + " login, "
                    + " senha)"
                    + "values("
                    + " ?, "
                    + " ?, "
                    + " ?, "
                    + " ?)");

            stmt.setInt(1, oUsuario.getId());
            stmt.setString(2, oUsuario.getNome());
            stmt.setString(3, oUsuario.getLogin());
            stmt.setString(4, oUsuario.getSenha());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("erro sql: "+e);
            return false;
        } finally {
            try {
                stmt.close();
                conexao = JdbcDaoFactory.fechaConexao();
            } catch (SQLException ex) {

            }
        }
    }
    
    public boolean alteraSenha(String senha) {
        boolean bRetorno = false;
        
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("update usuario set senha = ? where usuarioid = ?");
            stmt.setString(1, Criptografia.criptografar(senha));
            stmt.setInt(1, SessionUtils.getUserId());
            stmt.execute();
            bRetorno = true;
        } catch(SQLException e) {
            bRetorno= false;
        } finally {
            return bRetorno;
        }
    }
    
    @Override
    public boolean update(Object oObjeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        @Override
    public boolean delete(Object oObjeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getNextId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
