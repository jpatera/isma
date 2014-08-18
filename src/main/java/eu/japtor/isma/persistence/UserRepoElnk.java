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
            String qStr = "SELECT u FROM User u";
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
            String qStr = "select u from User u where u.code = ?1";
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
    public User getUserByLoginName(String aLoginName) {
        try {
            EntityManager em = EMF.createEntityManager();
            String qStr = "select u from User u where u.loginName = ?1";
            TypedQuery<User> query = em.createQuery(qStr, User.class);
            query.setParameter(1, aLoginName);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new AplWebException(Response.Status.NOT_FOUND
                , "Nenalezen uživatel s přihlašovacím jménem=" + aLoginName
                , "Zřejmě neexistuje v DB...?");            
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri hledání uživatele s přihlašovacím jménem=" + aLoginName
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
                , "Uživatel s ID=" + aUser.getId() + " již existuje."
                , "Chyba DB...?");            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status. INTERNAL_SERVER_ERROR
                , "Chyba při vkládání nového uživatele s přihlašovacím jménem=" + aUser.getLoginName()
                , "Systémová zpráva:\n" + e.getMessage());            
        }
    }

    @Override
    public int deleteUserByCode(String aUserCode) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
                String qStr = "delete from usr u where u.code = :p_code";
                Query query = em.createQuery(qStr);
                int delCount = query.setParameter("p_code", aUserCode).executeUpdate();
            em.getTransaction().commit();
            return delCount;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status. INTERNAL_SERVER_ERROR
                , "Chyba při rušení uživatele s kódem=" + aUserCode
                , "Systémová zpráva:\n" + e.getMessage());            
        }
    }

    
    @Override
    public int deleteUserByLoginName(String aLoginName) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
                String qStr = "delete from usr u where u.loginname = :p_login";
                Query query = em.createQuery(qStr);
                int delCount = query.setParameter("p_login", aLoginName).executeUpdate();
            em.getTransaction().commit();
            return delCount;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba při rušení uživatele se jménem=" + aLoginName
                , "Systémová zpráva:\n" + e.getMessage());            
        }
    }

    @Override
    public void deleteAllUsers() {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("TRUNCATE TABLE usr");
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
    public boolean userWithUserCodeExist(String aUserCode) {
        EntityManager em = EMF.createEntityManager();
        try {
            Query query = em.createNativeQuery(
                "select count(*) cnt from usr where code ='" +  aUserCode + "'");        
            Integer cnt = (Integer)query.getSingleResult();
            return (cnt > 0);
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba při hledání uživatelem s kódem=" + aUserCode
                , "Systémová zpráva:\n" + e.getMessage());
        }
    }

    @Override
    public boolean userWithLoginNameExist(String aLoginName) {
        EntityManager em = EMF.createEntityManager();
        try {
            Query query = em.createNativeQuery(
                "select count(*) cnt from usr where loginname = '" + aLoginName + "'");
            Integer cnt = (Integer)query.getSingleResult();
            return (cnt > 0);
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba při hledání uživatele s přihlašovacím jménem=" + aLoginName
                , "Systémová zpráva:\n" + e.getMessage());
        }
    }    
}
