/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.erp.exception;

/**
 *
 * @author mariodev
 */
public class ErroBancoDadosException extends Exception{
    
    public ErroBancoDadosException(){
        super("Ocorreu um erro na operação do banco de dados");
    }
    
    public ErroBancoDadosException(String msg){
        super(msg);
    }
    
}
