
package br.com.fatura.converter;

import br.com.fatura.model.Municipio;
import br.com.fatura.converter.AbstractConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guilherme
 */
@FacesConverter(forClass = Municipio.class)
public class MunicipioConverter extends AbstractConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.parseInt(string);
        return daoFactory.getMunicipioDao().getMunicipio(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Municipio municipio = (Municipio) o;
        String retorno = Integer.toString(municipio.getCodigo_ibge());
        return retorno;
    }
    
}
