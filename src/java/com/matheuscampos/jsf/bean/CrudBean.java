package com.matheuscampos.jsf.bean;

import com.matheuscampos.jsf.dao.CrudDAO;
import com.matheuscampos.jsf.util.exception.ErroSistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class CrudBean<E, D extends CrudDAO> {
    
    private String estadoTela = "busca"; // insere, edita, busca
    private E entidade;
    private List<E> entidades;
    
    
    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }
    
    public void novo() {
        entidade = criarNovaEntidade();
        mudarParaInsere();
    }
    
    public void salvar() {
        try {
            getDao().salvar(entidade);
            entidade = criarNovaEntidade();
            adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            mudarParaBusca();
            getDao().buscar();
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void editar(E entidade) {
        this.entidade = entidade;
        mudarParaEdita();
    }
    
    public void deletar(E entidade) {
        try {
            getDao().deletar(entidade);
            entidades.remove(entidade);
            adicionarMensagem("Deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void buscar() {
        if (!isBusca()) {
            mudarParaBusca();
            return;
        }
        try {
            entidades = getDao().buscar();
            if (entidades == null || entidades.size() < 1) {
                adicionarMensagem("Não temos nada cadastrado!", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    // Responsável por criar os métodos nas classes bean
    public abstract D getDao();
    public abstract E criarNovaEntidade();
    
    // Métodos para controle de tela
    public boolean isInsere() {
        return "insere".equals(estadoTela);
    }
    public boolean isEdita() {
        return "edita".equals(estadoTela);
    }
    public boolean isBusca() {
        return "busca".equals(estadoTela);
    }
    public void mudarParaInsere() {
        estadoTela = "insere";
    }
    public void mudarParaEdita() {
        estadoTela = "edita";
    }
    public void mudarParaBusca() {
        estadoTela = "busca";
    }
    
}