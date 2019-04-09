package com.algaworks.erp.repository;

import com.algaworks.erp.model.RamoAtividade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Junior Sy
 */
public class RamoAtividades implements Serializable{
    
    @Inject
    EntityManager manager;
    
    public RamoAtividades(){
    }
    
    public RamoAtividades(EntityManager manager){
        this.manager = manager;
    }
    
    public List<RamoAtividade> pesquisar(String descricao){
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        
        CriteriaQuery<RamoAtividade> criteriaQuery = criteriaBuilder.createQuery(RamoAtividade.class);
        Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.<String>get("descricao"), descricao + "%"));
        
        TypedQuery<RamoAtividade> query = manager.createQuery(criteriaQuery);
        
        return query.getResultList();
    }
}
