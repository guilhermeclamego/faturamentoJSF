/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatura.converter;

import br.com.fatura.model.Funcionario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author guilherme
 */
@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter extends AbstractConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int funcionarioid = Integer.parseInt(string);
        return daoFactory.getFuncionarioDao().getFuncionario(funcionarioid);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Funcionario funcionario = (Funcionario) o;
        String retorno = Integer.toString(funcionario.getFuncionarioId());
        return retorno;
    }
    
    
}