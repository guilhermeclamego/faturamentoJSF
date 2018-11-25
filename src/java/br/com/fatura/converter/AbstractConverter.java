/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatura.converter;

import br.com.fatura.dao.DaoFactory;

/**
 *
 * @author guilherme
 */
public class AbstractConverter {
    
    protected DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.JDBC);
    
}