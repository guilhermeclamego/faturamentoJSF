/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatura.converter;

import br.com.fatura.model.Categoria;
import br.com.fatura.converter.AbstractConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guilherme
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter extends AbstractConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.parseInt(string);
        return daoFactory.getCategoriaDao().getCategoria(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Categoria categoria = (Categoria) o;
        String retorno = Integer.toString(categoria.getId());
        return retorno;
    }
}