package com.algaworks.erp.controler;

import com.algaworks.erp.DAO.EmpresaDAO;
import com.algaworks.erp.exception.ErroBancoDadosException;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.TipoEmpresa;
import com.algaworks.erp.util.FacesMessages;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable{
    
    @Inject
    private EmpresaDAO empresaDAO;
    
    @Inject
    private FacesMessages facesMessages;
    
    private List<Empresa> listaEmpresas;
    
    private String termoPesquisa;
    
    public GestaoEmpresasBean() {
        listaEmpresas = new ArrayList<>();
    }
    
    @PostConstruct
    public void inicializar(){
        todasEmpresas();
    }
    
    public void pesquisar() throws ErroBancoDadosException{
        listaEmpresas = empresaDAO.pesquisar(termoPesquisa);
        if(listaEmpresas.isEmpty()){
            facesMessages.info("Sua consulta não retornou resgistros.");
        }
    }
    
    public void todasEmpresas(){
        try {
            listaEmpresas = empresaDAO.listarTodos();
        } catch (ErroBancoDadosException ex) {
            Logger.getLogger(GestaoEmpresasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TipoEmpresa[] getTipoEmpresas(){
        return TipoEmpresa.values();
    }
    
    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }
}
