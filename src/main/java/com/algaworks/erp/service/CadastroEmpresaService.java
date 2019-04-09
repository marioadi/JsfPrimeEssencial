/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.erp.service;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.repository.Empresas;
import com.algaworks.erp.util.Transacional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Junior Sy
 */
public class CadastroEmpresaService implements Serializable{
    	
	@Inject
	private Empresas empresas;
	
	@Transacional
	public void salvar(Empresa empresa) {
		empresas.guardar(empresa);
	}
	
	@Transacional
	public void excluir(Empresa empresa) {
		empresas.remover(empresa);
	}
}
