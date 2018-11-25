package br.com.fatura.dao.auditoria;

import br.com.fatura.dao.JdbcDaoFactory;
import br.com.fatura.model.Auditoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public class AuditoriaJdbc implements AuditoriaDao {

    private Connection conexao;
    private PreparedStatement stmt;

    @Override
    public Object getAuditoria(int auditoriaId) {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select * from auditoria where id = ?");
            stmt.setInt(1, auditoriaId);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            Auditoria oAuditoria = new Auditoria();
            oAuditoria.setAuditoriaId(rs.getInt("auditoriaid"));
            oAuditoria.setOperacao(rs.getString("operacao"));
            oAuditoria.setData(rs.getString("data"));
            oAuditoria.setUsuario(rs.getString("usuario"));
            oAuditoria.setTabela(rs.getString("tabela"));
            oAuditoria.setDados(rs.getString(("dados")));           
            return oAuditoria;
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
    public List<Auditoria> buscarTodos() {
        try {
            conexao = JdbcDaoFactory.abreConexao();
            stmt = conexao.prepareStatement("Select \n"
                    + "  auditoria.id , \n"
                    + "  auditoria.operacao,  \n"
                    + "  auditoria.data,   \n"
                    + "  auditoria.usuario,   \n"
                    + "  auditoria.tabela,   \n"
                    + "  auditoria.dados\n"
                    + "from\n"
                    + "  auditoria\n"
                    + "order by \n"
                    + "  id");

            ResultSet rs = stmt.executeQuery();

            ArrayList<Auditoria> todos = new ArrayList<Auditoria>();

            while (rs.next()) {
                Auditoria oAuditoria = new Auditoria();

                oAuditoria.setAuditoriaId(rs.getInt("auditoriaid"));
                oAuditoria.setOperacao(rs.getString("operacao"));
                oAuditoria.setData(rs.getString("data"));
                oAuditoria.setUsuario(rs.getString("usuario"));
                oAuditoria.setTabela(rs.getString("tabela"));
                oAuditoria.setDados(rs.getString(("dados")));             

                todos.add(oAuditoria);
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
