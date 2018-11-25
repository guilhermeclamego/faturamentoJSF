
package br.com.fatura.dao.cliente;

import br.com.fatura.dao.JdbcDaoFactory;
import br.com.fatura.model.Cliente;
import br.com.fatura.model.Municipio;
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
public class ClienteJdbc implements ClienteDao {

    private Connection conexao;
    private PreparedStatement stmt;

    @Override
    public boolean insert(Object oObjeto) {
        boolean bRetorno = false;
        Cliente oCliente = (Cliente) oObjeto;
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("insert into cliente (id,\n"
                    + "    nome,\n"
                    + "    cpf,\n"
                    + "    email,\n"
                    + "    municipioid,\n"
                    + "    bairro,\n"
                    + "    rua,\n"
                    + "    numero,\n"
                    + "    telefone\n"
                    + "  ) values (?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?,\n"
                    + "    ?)");

            stmt.setInt(1, oCliente.getId());
            stmt.setString(2, oCliente.getNome());
            stmt.setString(3, oCliente.getCpf());
            stmt.setString(4, oCliente.getEmail());
            stmt.setInt(5, oCliente.getMunicipio().getCodigo_ibge());
            stmt.setString(6, oCliente.getBairro());
            stmt.setString(7, oCliente.getRua());
            stmt.setString(8, oCliente.getNumero());
            stmt.setString(9, oCliente.getTelefone());
            stmt.execute();
            bRetorno = true;
        } catch (SQLException e) {
            System.out.println(e);
            bRetorno = false;
        } finally {
            try {
                stmt.close();
                conexao = JdbcDaoFactory.fechaConexao();
            } catch (SQLException ex) {

            }
            return bRetorno;
        }
    }

    @Override
    public int getNextId() {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select coalesce(max(id), 1) + 1 codigo from cliente");
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
        Cliente oCliente = (Cliente) oObjeto;
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("update \n"
                    + "  cliente\n"
                    + "set  \n"
                    + "  nome = ?,\n"
                    + "  cpf = ?,\n"
                    + "  email = ?,\n"
                    + "  municipioid = ?,\n"
                    + "  bairro = ?,\n"
                    + "  rua = ?,\n"
                    + "  numero = ?,\n"
                    + "  telefone = ?\n"
                    + "where \n"
                    + "  id = ?");

            stmt.setInt(9, oCliente.getId());
            stmt.setString(1, oCliente.getNome());
            stmt.setString(2, oCliente.getCpf());
            stmt.setString(3, oCliente.getEmail());
            stmt.setInt(4, oCliente.getMunicipio().getCodigo_ibge());
            stmt.setString(5, oCliente.getBairro());
            stmt.setString(6, oCliente.getRua());
            stmt.setString(7, oCliente.getNumero());
            stmt.setString(8, oCliente.getTelefone());
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
    public Object getCliente(int id) {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select \n"
                    + "  cliente.id , \n"
                    + "  cliente.nome,  \n"
                    + "  cliente.bairro,   \n"
                    + "  cliente.cpf ,   \n"
                    + "  cliente.email ,   \n"
                    + "  cliente.municipioid ,    \n"
                    + "  cliente.numero,   \n"
                    + "  cliente.rua,   \n"
                    + "  cliente.telefone,  \n"
                    + "  cliente.datanascimento,  \n"
                    + "  municipios.codigo_ibge,   \n"
                    + "  municipios.municipio,   \n"
                    + "  municipios.uf\n"
                    + "from\n"
                    + "  cliente, municipios\n"
                    + "where \n"
                    + "  cliente.municipioid = municipios.codigo_ibge and id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            Cliente oCliente = new Cliente();

            Municipio oMunicipio = new Municipio();

            oCliente.setId(rs.getInt("id"));
            oCliente.setNome(rs.getString("nome"));
            oCliente.setCpf(rs.getString("cpf"));
            oCliente.setEmail(rs.getString("email"));
            oCliente.setBairro(rs.getString("bairro"));
            oCliente.setNumero(rs.getString("numero"));
            oCliente.setRua(rs.getString("rua"));
            oCliente.setTelefone(rs.getString("telefone"));
            oCliente.setDataNascimento(rs.getString("datanascimento"));

            oMunicipio.setCodigo_ibge(rs.getInt("codigo_ibge"));
            oMunicipio.setDescricao(rs.getString("municipio"));
            oMunicipio.setUf(rs.getString("uf"));

            oCliente.setMunicipio(oMunicipio);
            return oCliente;
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
    public List<Cliente> buscarTodos() {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select \n"
                    + "  cliente.id , \n"
                    + "  cliente.nome,  \n"
                    + "  cliente.bairro,   \n"
                    + "  cliente.cpf ,   \n"
                    + "  cliente.email ,   \n"
                    + "  cliente.municipioid ,    \n"
                    + "  cliente.numero,   \n"
                    + "  cliente.rua,   \n"
                    + "  cliente.telefone,  \n"
                    + "  cliente.datanascimento,  \n"
                    + "  municipios.codigo_ibge,   \n"
                    + "  municipios.municipio,   \n"
                    + "  municipios.uf\n"
                    + "from\n"
                    + "  cliente, municipios\n"
                    + "where \n"
                    + "  cliente.municipioid = municipios.codigo_ibge \n"
                    + "order by \n"
                    + "  id");

            ResultSet rs = stmt.executeQuery();

            ArrayList<Cliente> todos = new ArrayList<Cliente>();

            while (rs.next()) {
                Cliente oCliente = new Cliente();
                Municipio oMunicipio = new Municipio();

                oCliente.setId(rs.getInt("id"));
                oCliente.setNome(rs.getString("nome"));
                oCliente.setCpf(rs.getString("cpf"));
                oCliente.setEmail(rs.getString("email"));
                oCliente.setBairro(rs.getString("bairro"));
                oCliente.setNumero(rs.getString("numero"));
                oCliente.setRua(rs.getString("rua"));
                oCliente.setTelefone(rs.getString("telefone"));
                oCliente.setDataNascimento(rs.getString("datanascimento"));

                oMunicipio.setCodigo_ibge(rs.getInt("codigo_ibge"));
                oMunicipio.setDescricao(rs.getString("municipio"));
                oMunicipio.setUf(rs.getString("uf"));

                oCliente.setMunicipio(oMunicipio);
                todos.add(oCliente);
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
        Cliente oCliente = (Cliente) oObjeto;
        try{
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("delete from cliente where id = ?");
            
            stmt.setInt(1, oCliente.getId());
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
            stmt = conexao.prepareStatement("Select nome from cliente where cpf = ?");
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
