
package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.erp.model.Empresa;
import javax.inject.Inject;

public class Empresas implements Serializable{
    
    @Inject
    private EntityManager manager;
    
    public Empresas(){
    }
    
    public Empresas(EntityManager manager) {
        this.manager = manager;
    }
    
    public Empresa porId(Long id){
        return manager.find(Empresa.class, id);
    }
    
    public List<Empresa> pesquisar(String nome){
        String sql = "FROM Empresa WHERE nomeFantasia LIKE :nomeFantasia";
        TypedQuery<Empresa> query = manager.createQuery(sql, Empresa.class);
        query.setParameter("nomeFantasia", nome + "%");
        return query.getResultList();
    }
    
    public List<Empresa> todas(){
        return manager.createQuery("FROM Empresa", Empresa.class).getResultList();
    }
    
    public Empresa guardar(Empresa empresa){
        return manager.merge(empresa);
    }
    
    public void remover(Empresa empresa){
        empresa = porId(empresa.getId());
        manager.remove(empresa);
    }
}
