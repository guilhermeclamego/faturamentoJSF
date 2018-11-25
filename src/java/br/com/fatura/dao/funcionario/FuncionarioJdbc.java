package br.com.fatura.dao.funcionario;

import br.com.fatura.dao.JdbcDaoFactory;
import br.com.fatura.model.Funcionario;
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
public class FuncionarioJdbc implements FuncionarioDao {

    private Connection conexao;
    private PreparedStatement stmt;
    
    @Override
    public Object getFuncionario(int funcionarioId) {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select * from funcionario where funcionarioid = ?");
            stmt.setInt(1, funcionarioId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            Funcionario oFuncionario = new Funcionario();
            oFuncionario.setFuncionarioId(rs.getInt("funcionarioid"));
            oFuncionario.setNome(rs.getString("nome"));
            return oFuncionario;
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

    @Override
    public boolean insert(Object oObjeto) {
        Funcionario oFuncionario = (Funcionario) oObjeto;
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("insert into funcionario (funcionarioid,\n"
                    + "    cpf,\n"
                    + "    nome,\n"
                    + "    endereco,\n"
                    + "    email,\n"
                    + "    usuario\n"
                    + "  ) values (?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?)");

            stmt.setInt(1, oFuncionario.getFuncionarioId());
            stmt.setString(2, oFuncionario.getCpf());
            stmt.setString(3, oFuncionario.getNome());
            stmt.setString(4, oFuncionario.getEndereco());
            stmt.setString(5, oFuncionario.getEmail());
            if(oFuncionario.isUsuario()) {
                stmt.setInt(6, 1);
            } else {
                stmt.setInt(6, 2);
            }
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
            stmt = conexao.prepareStatement("Select coalesce(max(funcionarioid), 1) + 1 codigo from funcionario");
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

    @Override
    public boolean update(Object oObjeto) {
        Funcionario oFuncionario = (Funcionario) oObjeto;
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("update \n"
                    + "  Funcionario\n"
                    + "set  \n"
                    + "  cpf = ?,\n"
                    + "  nome = ?,\n"
                    + "  endereco = ?,\n"
                    + "  email = ?,\n"
                    + "  usuario = ?\n"
                    + "where \n"
                    + "  funcionarioid = ?");

            stmt.setInt(6, oFuncionario.getFuncionarioId());
            stmt.setString(1, oFuncionario.getCpf());
            stmt.setString(2, oFuncionario.getNome());
            stmt.setString(3, oFuncionario.getEndereco());
            stmt.setString(4, oFuncionario.getEmail());
            if(oFuncionario.isUsuario()) {
                stmt.setInt(5, 1);
            } else {
                stmt.setInt(5, 0);
            }
            
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
    public List<Funcionario> buscarTodos() {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select \n"
                    + "  funcionario.funcionarioid , \n"
                    + "  funcionario.cpf,  \n"
                    + "  funcionario.nome,   \n"
                    + "  funcionario.endereco ,   \n"
                    + "  funcionario.email ,   \n"
                    + "  funcionario.usuario\n"
                    + "from\n"
                    + "  funcionario\n"
                    + "order by \n"
                    + "  funcionarioid");

            ResultSet rs = stmt.executeQuery();

            ArrayList<Funcionario> todos = new ArrayList<Funcionario>();

            while (rs.next()) {
                Funcionario oFuncionario = new Funcionario();

                oFuncionario.setFuncionarioId(rs.getInt("funcionarioid"));
                oFuncionario.setCpf(rs.getString("cpf"));
                oFuncionario.setNome(rs.getString("nome"));
                oFuncionario.setEndereco(rs.getString("endereco"));
                oFuncionario.setEmail(rs.getString("email"));
                oFuncionario.setUsuario(rs.getBoolean("usuario"));              

                todos.add(oFuncionario);
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
    public boolean delete(Object oObjeto) {
        Funcionario oFuncionario = (Funcionario) oObjeto;
        try{
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("delete from Funcionario where funcionarioid = ?");
            
            stmt.setInt(1, oFuncionario.getFuncionarioId());
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
    public boolean cpfExistente(String cpf) {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select nome from funcionario where cpf = ?");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String nome = rs.getString("nome");
            return !nome.isEmpty();
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

}
