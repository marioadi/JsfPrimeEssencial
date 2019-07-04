/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.erp.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author mariodev
 */
public class FacesMessages implements Serializable{
    
    public void add(String msg, FacesMessage.Severity severity){
        FacesMessage facesMessage = new FacesMessage(msg);
        facesMessage.setSeverity(severity);
        
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    
    public void info(String msg){
        add(msg, FacesMessage.SEVERITY_INFO);
    }
    
}
