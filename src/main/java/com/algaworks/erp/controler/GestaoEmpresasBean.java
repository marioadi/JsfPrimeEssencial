package com.algaworks.erp.controler;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.repository.Empresas;
import java.util.ArrayList;
import javax.inject.Inject;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable{
    
    @Inject
    private Empresas empresas;
    
    private List<Empresa> listaEmpresas;

    public GestaoEmpresasBean() {
        listaEmpresas = new ArrayList<>();
    }

    public void todasEmpresas(){
        listaEmpresas = empresas.todas();
    }
    
    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }
}
