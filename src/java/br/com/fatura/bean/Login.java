package br.com.fatura.bean;

import br.com.fatura.dao.login.LoginDao;
import br.com.fatura.utils.SessionUtils;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author lamego
 */
@ManagedBean
@SessionScoped
public class Login extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String validateUsernamePassword() {
        if("".equals(user)) {
            addErrorMessage("O campo Usuário é obrigatório");
            return "login";
        } else if("".equals(pwd)) {
            addErrorMessage("O campo Senha é obrigatório");
            return "login";
        } else {
            boolean valid = LoginDao.validate(user, pwd);
            if (valid) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("username", user);
                return "/home.xhtml?faces-redirect=true";
            } else {
                addErrorMessage("Usuário e/ou senha incorretos");
                return "login";
            }
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }
}
