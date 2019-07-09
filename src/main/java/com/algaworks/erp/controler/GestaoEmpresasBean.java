package com.algaworks.erp.controler;

import com.algaworks.erp.DAO.EmpresaDAO;
import com.algaworks.erp.exception.ErroBancoDadosException;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.RamoAtividade;
import com.algaworks.erp.model.TipoEmpresa;
import com.algaworks.erp.repository.RamoAtividades;
import com.algaworks.erp.util.FacesMessages;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(GestaoEmpresasBean.class.getName());

    @Inject
    private EmpresaDAO empresaDAO;

    @Inject
    private FacesMessages facesMessages;

    @Inject
    private RamoAtividades ramoAtividades;

    private RamoAtividadeConverter atividadeConverter;
    private Empresa empresa;

    private List<Empresa> listaEmpresas;

    private String termoPesquisa;

    public GestaoEmpresasBean() {
        listaEmpresas = new ArrayList<>();
    }

    @PostConstruct
    public void inicializar() {
        todasEmpresas();
    }

    public void prepararNovaEmpresa() {
        empresa = new Empresa();
    }

    public void salvar() {
        try {
            if (this.empresa != null) {
                if (this.empresa.getId() == null) {
                    empresaDAO.salvar(empresa);
//                    if (jaHouvePesquisa()) {
//                        pesquisar();
//                    }
                    facesMessages.info("Empresa salva com sucesso!");
                    todasEmpresas();
                    //Só vai ser chamado quando a empresa for salva
                    RequestContext.getCurrentInstance().update(Arrays.asList("frm:empresasDataTable", "frm:messages"));
                } else {
                    //atualizar
                }
            }
        } catch (ErroBancoDadosException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void pesquisar() throws ErroBancoDadosException {
        listaEmpresas = empresaDAO.pesquisar(termoPesquisa);
        if (listaEmpresas.isEmpty()) {
            facesMessages.info("Sua consulta não retornou resgistros.");
        }
    }

    public List<RamoAtividade> completarRamoAtividade(String termo) {
        List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);
        atividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);
        return listaRamoAtividades;
    }

    public void todasEmpresas() {
        try {
            listaEmpresas = empresaDAO.listarTodos();
        } catch (ErroBancoDadosException ex) {
            Logger.getLogger(GestaoEmpresasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean jaHouvePesquisa() {
        return termoPesquisa != null && !"".equals(termoPesquisa);
    }

    public TipoEmpresa[] getTipoEmpresas() {
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

    public RamoAtividadeConverter getAtividadeConverter() {
        return atividadeConverter;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

}
