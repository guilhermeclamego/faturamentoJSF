package br.com.fatura.dao;

import br.com.fatura.dao.auditoria.AuditoriaDao;
import br.com.fatura.dao.auditoria.AuditoriaJdbc;
import br.com.fatura.dao.categoria.CategoriaJpa;
import br.com.fatura.dao.categoria.CategoriaDao;
import br.com.fatura.dao.cliente.ClienteDao;
import br.com.fatura.dao.cliente.ClienteJpa;
import br.com.fatura.dao.funcionario.FuncionarioDao;
import br.com.fatura.dao.funcionario.FuncionarioJdbc;
import br.com.fatura.dao.municipio.MunicipioDao;
import br.com.fatura.dao.municipio.MunicipioJpa;
import br.com.fatura.dao.pedidoCliente.PedidoClienteDao;
import br.com.fatura.dao.pedidoCliente.PedidoClienteJpa;
import br.com.fatura.dao.produto.ProdutoDao;
import br.com.fatura.dao.produto.ProdutoJpa;
import br.com.fatura.dao.usuario.UsuarioDao;
import br.com.fatura.dao.usuario.UsuarioJpa;

/**
 *
 * @author guilherme
 */
public class JpaDaoFactory extends DaoFactory {

    @Override
    public CategoriaDao getCategoriaDao() {
        return new CategoriaJpa();
    }

    @Override
    public ProdutoDao getProdutoDao() {
        return new ProdutoJpa();
    }

    @Override
    public ClienteDao getClienteDao() {
        return new ClienteJpa();
    }

    @Override
    public MunicipioDao getMunicipioDao() {
        return new MunicipioJpa();
    }
    
    @Override
    public PedidoClienteDao getPedidoClienteDao() {
        return new PedidoClienteJpa();
    }
    
    @Override 
    public UsuarioDao getUsuarioDao(){
        return new UsuarioJpa();
    }
    
   @Override
    public FuncionarioDao getFuncionarioDao(){
        return new FuncionarioJdbc();
    }
    
    @Override
    public AuditoriaDao getAuditoriaDao(){
        return new AuditoriaJdbc();
    }
}
