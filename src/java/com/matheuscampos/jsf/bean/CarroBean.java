package com.matheuscampos.jsf.bean;

import com.matheuscampos.jsf.dao.CarroDAO;
import com.matheuscampos.jsf.model.Carro;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CarroBean extends CrudBean<Carro, CarroDAO> {
    
    private CarroDAO carroDAO;

    @Override
    public CarroDAO getDao() {
        if (carroDAO == null) {
            carroDAO = new CarroDAO();
        }
        return carroDAO;
    }

    @Override
    public Carro criarNovaEntidade() {
        return new Carro();
    }
    
}
