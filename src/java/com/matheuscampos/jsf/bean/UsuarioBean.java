package com.matheuscampos.jsf.bean;

import com.matheuscampos.jsf.dao.UsuarioDAO;
import com.matheuscampos.jsf.model.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UsuarioBean extends CrudBean<Usuario, UsuarioDAO> {

    private UsuarioDAO usuarioDAO;
    
    @Override
    public UsuarioDAO getDao() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        return usuarioDAO;
    }

    @Override
    public Usuario criarNovaEntidade() {
        return new Usuario();
    }
    
}
