package br.com.fatura.bean;

/**
 *
 * @author guilherme
 */
import br.com.fatura.dao.DaoFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AbstractBean {
    
    protected DaoFactory daoFactory;

    public AbstractBean() {
        daoFactory = DaoFactory.getDaoFactory(DaoFactory.JDBC);
    }
    
 
    protected void addInfoMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }
    
    protected void addInfoMessage(String message) {
        addInfoMessage("", message);
    }
    
    protected void addErrorMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }
    
    protected void addErrorMessage(String message) {
        addErrorMessage("", message);
    }
    
}

