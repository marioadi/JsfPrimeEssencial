/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.erp.DAO;

import com.algaworks.erp.exception.ErroBancoDadosException;
import com.algaworks.erp.model.Empresa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author mariodev
 */
public abstract class DAO<T> implements Serializable {

    private static final Logger LOG = Logger.getLogger(DAO.class.getName());

    @Inject
    protected EntityManager manager;

    private final Class<T> classe;
    protected EntityTransaction trx;

    public DAO(Class<T> classe) {
        this.classe = classe;
    }

    public void salvar(T t) throws ErroBancoDadosException {
        try {
            trx = manager.getTransaction();
            trx.begin();
            manager.persist(t);
            trx.commit();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
            throw new ErroBancoDadosException(e.getMessage());
        }
    }

    public List<T> pesquisar(String nome) throws ErroBancoDadosException {
        try {
            String sql = "from Empresa where razaoSocial like :razaoSocial";
            Query query = manager.createQuery(sql, Empresa.class);
            query.setParameter("razaoSocial", "%"+nome+"%");
            return query.getResultList();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
            throw new ErroBancoDadosException(e.getMessage());
        }
    }

    public List<T> listarTodos() throws ErroBancoDadosException {
        try {
            List<T> lista = new ArrayList();
            CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
            query.select(query.from(classe));
            lista = manager.createQuery(query).getResultList();
            return lista;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
            throw new ErroBancoDadosException(e.getMessage());
        }
    }

}
