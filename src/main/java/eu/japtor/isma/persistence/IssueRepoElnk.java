/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.persistence;

import eu.japtor.isma.errors.AplWebException;
import eu.japtor.isma.model.Comment;
import eu.japtor.isma.model.Issue;
import eu.japtor.isma.model.IssueRepo;
import eu.japtor.isma.model.User;
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
public class IssueRepoElnk implements IssueRepo{

    private static final int STANDARD_MAX_REC = 20;
    private static EntityManagerFactory EMF;

    public IssueRepoElnk(EntityManagerFactory aEMF) {
        EMF = aEMF;
    }

    @Override
    public List<Issue> getIssues(Integer aMaxRec) {
        EntityManager em = EMF.createEntityManager();
        Integer maxRec = aMaxRec;
        if ((maxRec == null) || (maxRec < 1)) {
            maxRec = STANDARD_MAX_REC;
        }
        try {
            String qStr = "SELECT i FROM Issue i";
            TypedQuery<Issue> query = em.createQuery(qStr, Issue.class)
                .setMaxResults(maxRec);
            return query.getResultList();
        } catch (NoResultException e) {
            throw new AplWebException(Response.Status.NOT_FOUND
                , "Nenalezeno žádné issue"
                , "Tabulka v DB je prazdna...?");            
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba při hledání issues"
                , "Systémová zpráva:\n" + e.getMessage());            
        }
    }    
    
    @Override
    public List<Comment> getIssueComments(Issue issue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Issue getIssueById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long createIssue(Issue aIssue) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
                em.persist(aIssue);
                em.flush();  // Force insert and receive the user's id
            em.getTransaction().commit();
            return aIssue.getId();
        } catch (EntityExistsException e) {
            throw new AplWebException(Response.Status.CONFLICT
                , "Issue s ID=" + aIssue.getId() + " již existuje."
                , "Chyba DB...?");            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status. INTERNAL_SERVER_ERROR
                , "Chyba při vkládání nového issue s ID=" + aIssue.getId()
                , "Systémová zpráva:\n" + e.getMessage());            
        }
    }
    
}
