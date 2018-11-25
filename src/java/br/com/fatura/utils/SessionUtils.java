package br.com.fatura.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author carlos
 */
public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

    public static int getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return Integer.parseInt((String) session.getAttribute("usuarioid"));
        } else {
            return 0;
        }
    }
}
