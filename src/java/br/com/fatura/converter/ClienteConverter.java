
package br.com.fatura.converter;

import br.com.fatura.model.Cliente;
import br.com.fatura.converter.AbstractConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guilherme
 */
@FacesConverter(forClass = Cliente.class)
public class ClienteConverter extends AbstractConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.parseInt(string);
        return daoFactory.getClienteDao().getCliente(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Cliente cliente = (Cliente) o;
        String retorno = Integer.toString(cliente.getId());
        return retorno;
    }
    
    
}
