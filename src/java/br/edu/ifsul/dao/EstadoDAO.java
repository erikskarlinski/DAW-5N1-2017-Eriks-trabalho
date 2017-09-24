/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.eclipse.jdt.internal.compiler.lookup.TypeConstants;

/**
 *
 * @author eriks
 */
public class EstadoDAO implements Serializable{
    private String mensagem="";
    private EntityManager em;
    public EstadoDAO(){
        em= EntityManagerUtil.geEntityManager();
    }
    public List<Estado>getLista(){
        return em.createQuery("from Estado order by nome").getResultList();
    }
    public boolean salvar(Estado obj){
        try{
            em.getTransaction().begin();
            if(obj.getId()==null){
                em.persist(obj);
            }else{
                em.merge(obj);
            }
            em.getTransaction().commit();
            mensagem= "objeto persistido";
            return true;
        }catch(Exception e){
            if(em.getTransaction().isActive()==false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "erro ao persistir objeto " + Util.getMensagemErro(e);
            return false;
        }
    }
    public boolean remover(Estado obj){
        try{
            em.getTransaction().begin();
           em.remove(obj);
            em.getTransaction().commit();
            mensagem= "objeto removido";
            return true;
        }catch(Exception e){
            if(em.getTransaction().isActive()==false){
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "erro ao remover objeto " + Util.getMensagemErro(e);
            return false;
        }
    }
    public Estado localizar(Integer id){
        return em.find(Estado.class, id);
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
