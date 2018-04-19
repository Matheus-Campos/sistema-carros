package com.matheuscampos.jsf.dao;

import com.matheuscampos.jsf.util.exception.ErroSistema;
import java.util.List;

public interface CrudDAO<E> { // E representa a entidade
    
    public void salvar(E entidade) throws ErroSistema;
    
    public void deletar(E entidade) throws ErroSistema;
    
    public List<E> buscar() throws ErroSistema;
}
