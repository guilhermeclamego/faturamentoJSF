package br.com.fatura.bean;

import br.com.fatura.model.Auditoria;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme
 */
@ManagedBean(name = "auditoriaBean")
@ViewScoped
public class AuditoriaBean extends AbstractBean implements Serializable {
    
    private Auditoria auditoria;
    private List<Auditoria> auditorias;

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }
    
   @PostConstruct
    public void init() {
        auditoria = new Auditoria();
        atualizaLista();
    }
    
    public List<Auditoria> getAuditorias() {
        try {
            return auditorias;
        } catch (Exception e) {
            return null;
        }
    }
    
   private void atualizaLista() {
        this.auditorias = daoFactory.getAuditoriaDao().buscarTodos();
    }
}
