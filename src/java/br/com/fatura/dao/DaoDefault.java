package br.com.fatura.dao;

//Classe com metodos comun para todos os objetos
public interface DaoDefault {

    public abstract boolean insert(Object oObjeto);

    public abstract boolean delete(Object oObjeto);

    public abstract boolean update(Object oObjeto);

    public abstract int getNextId();
}