/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.japtor.isma.persistence;

import eu.japtor.isma.errors.AplWebException;
import eu.japtor.isma.model.User;
import eu.japtor.isma.model.UserRepo;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

/**
 *
 * @author Honza

 User Repository interface implementation using EclipseLink
 *
 */
public class UserRepoElnk implements UserRepo {

    private static final int STANDARD_MAX_REC = 20;
    private static EntityManagerFactory EMF;

    public UserRepoElnk(EntityManagerFactory aEMF) {
        EMF = aEMF;
    }

    @Override
    public List<User> getUsers(Integer aMaxRec) {
        EntityManager em = EMF.createEntityManager();
        Integer maxRec = aMaxRec;
        if ((maxRec == null) || (maxRec < 1)) {
            maxRec = STANDARD_MAX_REC;
        }
        try {
            String qStr = "SELECT p FROM User p";
            TypedQuery<User> query = em.createQuery(qStr, User.class)
                .setMaxResults(maxRec);
            return query.getResultList();
        } catch (NoResultException e) {
            throw new AplWebException(Response.Status.NOT_FOUND
                , "Nenalezena žádný uživatel"
                , "Tabulka v DB je prazdna...?");            
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri hledani uživatelů"
                , "Systémová zpráva:\n" + e.getMessage());            
        }
    }

    @Override
    public User getUserByCode(String aUserCode) {
        try {
            EntityManager em = EMF.createEntityManager();
            String qStr = "SELECT p FROM User p WHERE p.code = ?1";
            TypedQuery<User> query = em.createQuery(qStr, User.class);
            query.setParameter(1, aUserCode);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new AplWebException(Response.Status.NOT_FOUND
                , "Nenalezen uživatel s kódem=" + aUserCode
                , "Takový uživatel v DB neexistuje...?");            
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba při hledání uživatele s kódem=" + aUserCode
                , "Systémová zpráva:\n" + e.getMessage());            
        }
    }

    @Override
    public User getUserByName(String aUserName) {
        try {
            EntityManager em = EMF.createEntityManager();
            String qStr = "SELECT p FROM User p WHERE p.name = ?1";
            TypedQuery<User> query = em.createQuery(qStr, User.class);
            query.setParameter(1, aUserName);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new AplWebException(Response.Status.NOT_FOUND
                , "Nenalezen uživatel se jmenem=" + aUserName
                , "Takova osoba v DB neexistuje...?");            
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri hledani osoby se jmenem=" + aUserName
                , "Systemová zprava:\n" + e.getMessage());            
        }
    }

    @Override
    public String createUser(User aUser) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
                em.persist(aUser);
        //        em.flush();  // Force insert and receive the id of the user
            em.getTransaction().commit();
            return aUser.getCode();
        } catch (EntityExistsException e) {
            throw new AplWebException(Response.Status.CONFLICT
                , "Osoba s ID=" + aUser.getId() + " jiz existuje"
                , "");            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status. INTERNAL_SERVER_ERROR
                , "Chyba pri vkladani nove osoby se jmenem=" + aUser.getName()
                , "Systemova zprava:\n" + e.getMessage());            
        }
    }

    @Override
    public int deleteUserByCode(String aUserCode) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
                String qStr = "DELETE FROM User p WHERE p.code = :code";
                Query query = em.createQuery(qStr);
                int delCount = query.setParameter("code", aUserCode).executeUpdate();
            em.getTransaction().commit();
            return delCount;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status. INTERNAL_SERVER_ERROR
                , "Chyba pri ruseni osoby s kodem=" + aUserCode
                , "Systemova zprava:\n" + e.getMessage());            
        }
    }

    
    @Override
    public int deleteUserByName(String aUserName) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
                String qStr = "DELETE FROM User p WHERE p.name = :name";
                Query query = em.createQuery(qStr);
                int delCount = query.setParameter("name", aUserName).executeUpdate();
            em.getTransaction().commit();
            return delCount;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri ruseni osoby se jmenem=" + aUserName
                , "Systemova zprava:\n" + e.getMessage());            
        }
    }

    @Override
    public void deleteAllUsers() {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("TRUNCATE TABLE user");
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba při ručení všech uživatelů"
                , "Systémová zpráva:\n" + e.getMessage());            
        }
    }

    
    @Override
    public boolean userWithCodeExist(String aUserCode) {
        EntityManager em = EMF.createEntityManager();
        try {
            Query query = em.createNativeQuery(
                "select count(*) cnt from User where code ='" +  aUserCode + "'");        
            Integer cnt = (Integer)query.getSingleResult();
            return (cnt > 0);
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri hledani osoby s kodem=" + aUserCode
                , "Systemova zprava:\n" + e.getMessage());
        }
    }

    @Override
    public boolean userWithNameExist(String aUserName) {
        EntityManager em = EMF.createEntityManager();
        try {
            Query query = em.createNativeQuery(
                "select count(*) cnt from User where name = '" + aUserName + "'");
            Integer cnt = (Integer)query.getSingleResult();
            return (cnt > 0);
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri hledani osoby se jmenem=" + aUserName
                , "Systemova zprava:\n" + e.getMessage());
        }
    }    
}
