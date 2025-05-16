/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.mycompany.model.exceptions.IllegalOrphanException;
import com.mycompany.model.exceptions.NonexistentEntityException;
import com.mycompany.model.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author luana
 */
public class PlanEstudiosJpaController implements Serializable {

    public PlanEstudiosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("administratorUCR_persistence");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlanEstudios planEstudios) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(planEstudios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanEstudios(planEstudios.getId()) != null) {
                throw new PreexistingEntityException("Study plan " + planEstudios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlanEstudios planEstudios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            planEstudios = em.merge(planEstudios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findPlanEstudios(planEstudios.getId()) == null) {
                    throw new NonexistentEntityException("The study plan with id " + planEstudios.getId() + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanEstudios planEstudios;
            try {
                planEstudios = em.getReference(PlanEstudios.class, id);
                planEstudios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The study plan with id " + id + " no longer exists.", enfe);
            }
            em.remove(planEstudios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



    public List<PlanEstudios> findPlanEstudios() {
        EntityManager em = getEntityManager();
        
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(PlanEstudios.class));
        Query q = em.createQuery(cq);
            
        return q.getResultList();
    }

    public PlanEstudios findPlanEstudios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlanEstudios.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanEstudiosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlanEstudios> rt = cq.from(PlanEstudios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
