
package br.com.fatura.dao.municipio;

import br.com.fatura.dao.JdbcDaoFactory;
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
public class MunicipioJdbc implements MunicipioDao {

    private Connection conexao;
    private PreparedStatement stmt;

    @Override
    public Object getMunicipio(int id) {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select * from MUNICIPIOS where CODIGO_IBGE = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            Municipio oMunicipio = new Municipio();
            oMunicipio.setCodigo_ibge(rs.getInt("CODIGO_IBGE"));
            oMunicipio.setDescricao(rs.getString("MUNICIPIO"));
            oMunicipio.setUf(rs.getString("UF"));

            return oMunicipio;
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
    public List<Municipio> buscarTodosSC() {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("select * from municipios where uf = 'SC' order by municipio");

            ResultSet rs = stmt.executeQuery();

            ArrayList<Municipio> todos = new ArrayList<Municipio>();

            while (rs.next()) {
                Municipio oMunicipio = new Municipio();
                oMunicipio.setCodigo_ibge(rs.getInt("CODIGO_IBGE"));
                oMunicipio.setDescricao(rs.getString("MUNICIPIO"));
                oMunicipio.setUf(rs.getString("UF"));
                todos.add(oMunicipio);
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
    public List<Municipio> buscarTodos() {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("select * from municipios order by municipio");

            ResultSet rs = stmt.executeQuery();

            ArrayList<Municipio> todos = new ArrayList<Municipio>();

            while (rs.next()) {
                Municipio oMunicipio = new Municipio();
                oMunicipio.setCodigo_ibge(rs.getInt("CODIGO_IBGE"));
                oMunicipio.setDescricao(rs.getString("MUNICIPIO"));
                oMunicipio.setUf(rs.getString("UF"));
                todos.add(oMunicipio);
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
    public boolean insert(Object oObjeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object oObjeto)  {
        Municipio oMunicipio = (Municipio) oObjeto;
        try{
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("delete from municipio where codigo_ibge = ?");
            
            stmt.setInt(1, oMunicipio.getCodigo_ibge());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
