
package br.com.fatura.converter;

import br.com.fatura.model.Produto;
import br.com.fatura.converter.AbstractConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guilherme
 */
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter extends AbstractConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.parseInt(string);
        return daoFactory.getProdutoDao().getProduto(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Produto produto =  (Produto) o;
        String retorno = Integer.toString(produto.getId());
        return retorno;
    }

}