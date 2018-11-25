package br.com.fatura.dao.login;

import br.com.fatura.dao.JdbcDaoFactory;
import br.com.fatura.utils.Criptografia;
import br.com.fatura.utils.SessionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

/**
 * @author carlos
 */
public class LoginDao {

    public static boolean validate(String user, String password) {
        boolean bRetorno = false;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JdbcDaoFactory.abreConexao();
            ps = con.prepareStatement("select usuarioid, login, senha from usuario where login = ? and senha = ?");
            ps.setString(1, user);
            ps.setString(2, Criptografia.criptografar(password));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("usuarioid", rs.getInt("usuarioid"));
                bRetorno = true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            bRetorno = false;
        } finally {
            try {
                ps.close();
                con = JdbcDaoFactory.fechaConexao();
            } catch (SQLException ex) {

            }
            //return true;
            return bRetorno;
        }
    }
}
