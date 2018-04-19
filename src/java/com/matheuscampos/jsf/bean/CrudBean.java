package com.matheuscampos.jsf.bean;

public class CrudBean {
    
    private String estadoTela = "busca"; // Insere, Edita, Busca
    
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