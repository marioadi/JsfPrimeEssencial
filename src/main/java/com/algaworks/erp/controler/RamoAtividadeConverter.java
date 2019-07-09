/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.erp.controler;

import com.algaworks.erp.model.RamoAtividade;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


/**
 *
 * @author mariodev
 */

public class RamoAtividadeConverter implements Converter{

    List<RamoAtividade> ramoAtividades;

    public RamoAtividadeConverter(List<RamoAtividade> ramoAtividades) {
        this.ramoAtividades = ramoAtividades;
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value == null){
            return null;
        }
        
        Long id  = Long.valueOf(value);
        for (RamoAtividade ramoAtividade : ramoAtividades) {
            if(id.equals(ramoAtividade.getId())){
                return ramoAtividade;
            }
        }
        return null;    
    }
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if(value == null){
            return null;
        }
        
        return ((RamoAtividade) value).getId().toString();
        
        //RamoAtividade ramoAtividades = (RamoAtividade) value;
        
        //return ramoAtividades.getId().toString();
    }

    
}
