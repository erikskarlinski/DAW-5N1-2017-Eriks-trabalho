/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.edu.converters;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Estado;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;


/**
 *
 * @author eriks
 */
@FacesConverter(value = "converterestados")
public class ConverterEstado implements Serializable,Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
       if(string == null || string.equals("Selecione um registro")){
           return null;
       }
       try{
           return EntityManagerUtil.geEntityManager().find(Estado.class, Integer.parseInt(string));
       }catch(Exception e){
           return null;
       }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
       if(o==null){
           return null;
       }
       Estado obj =(Estado) o;
       return obj.getId().toString();
    }

    
    
}
