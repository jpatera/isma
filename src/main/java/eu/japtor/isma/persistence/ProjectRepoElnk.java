/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.persistence;

import eu.japtor.isma.errors.AplWebException;
import eu.japtor.isma.model.Project;
import eu.japtor.isma.model.ProjectRepo;
import eu.japtor.isma.model.User;
import eu.japtor.isma.model.UserRepo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

/**
 *
 * @author Honza
 */
public class ProjectRepoElnk  implements ProjectRepo {

    private static final int STANDARD_MAX_REC = 20;
    private static EntityManagerFactory EMF;

    public ProjectRepoElnk(EntityManagerFactory aEMF) {
        EMF = aEMF;
    }
    
    @Override
    public List<Project> getProjectsByOwnerCode(String aOwnerCode) {
        try {
            EntityManager em = EMF.createEntityManager();
            String qStr = "select p from Project p where p.ownerCode = ?1";
            TypedQuery<Project> query = em.createQuery(qStr, Project.class);
            query.setParameter(1, aOwnerCode);
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<Project>();
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba při hledání projektů vlastníka s kódem=" + aOwnerCode
                , "Systémová zpráva:\n" + e.getMessage());            
        }    }

    @Override
    public Project getProjectById(Long aProjectId) {
        try {
            EntityManager em = EMF.createEntityManager();
            String qStr = "select p from Project p where p.id = ?1";
            TypedQuery<Project> query = em.createQuery(qStr, Project.class);
            query.setParameter(1, aProjectId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
//            throw new AplWebException(Response.Status.NOT_FOUND
//                , "Nenalezen uživatel s kódem=" + aUserCode
//                , "Takový uživatel v DB neexistuje...?");            
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba při hledání projektu s ID=" + aProjectId
                , "Systémová zpráva:\n" + e.getMessage());            
        }    }

    @Override
    public Long createProject(Project aProject) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
                em.persist(aProject);
            em.getTransaction().commit();
            return aProject.getId();
        } catch (EntityExistsException e) {
            throw new AplWebException(Response.Status.CONFLICT
                , "Projekt s ID=" + aProject.getId() + " již existuje."
                , "Chyba DB...?");            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status. INTERNAL_SERVER_ERROR
                , "Chyba při vkládání nového projektu"
                , "Systémová zpráva:\n" + e.getMessage());            
        }    }

    @Override
    public void deleteProject(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
