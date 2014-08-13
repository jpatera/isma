/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.japtor.isma.persistence;

import eu.japtor.isma.errors.AplWebException;
import eu.japtor.isma.model.Person;
import eu.japtor.isma.model.PersonRepo;
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
 *
 * Person Repository interface implementation using EclipseLink
 *
 */
public class PersonRepoElnk implements PersonRepo {

    private static final int STANDARD_MAX_REC = 10;
    private static EntityManagerFactory EMF;

    public PersonRepoElnk(EntityManagerFactory aEMF) {
        EMF = aEMF;
    }

    @Override
    public List<Person> getPeople(Integer aMaxRec) {
        EntityManager em = EMF.createEntityManager();
        Integer maxRec = aMaxRec;
        if ((maxRec == null) || (maxRec < 1)) {
            maxRec = STANDARD_MAX_REC;
        }
        try {
            String qStr = "SELECT p FROM Person p";
            TypedQuery<Person> query = em.createQuery(qStr, Person.class)
                .setMaxResults(maxRec);
            return query.getResultList();
        } catch (NoResultException e) {
            throw new AplWebException(Response.Status.NOT_FOUND
                , "Nenalezena zadna osoba"
                , "Tabulka v DB je prazdna...?");            
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri hledani osob"
                , "Systemova zprava:\n" + e.getMessage());            
        }
    }

    @Override
    public Person getPersonByCode(String aPersonCode) {
        try {
            EntityManager em = EMF.createEntityManager();
            String qStr = "SELECT p FROM Person p WHERE p.code = ?1";
            TypedQuery<Person> query = em.createQuery(qStr, Person.class);
            query.setParameter(1, aPersonCode);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new AplWebException(Response.Status.NOT_FOUND
                , "Nenalezena osoba s kodem=" + aPersonCode
                , "Takova osoba v DB neexistuje...?");            
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri hledani osoby s kodem=" + aPersonCode
                , "Systemova zprava:\n" + e.getMessage());            
        }
    }

    @Override
    public Person getPersonByName(String aPersonName) {
        try {
            EntityManager em = EMF.createEntityManager();
            String qStr = "SELECT p FROM Person p WHERE p.name = ?1";
            TypedQuery<Person> query = em.createQuery(qStr, Person.class);
            query.setParameter(1, aPersonName);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new AplWebException(Response.Status.NOT_FOUND
                , "Nenalezena osoba se jmenem=" + aPersonName
                , "Takova osoba v DB neexistuje...?");            
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri hledani osoby se jmenem=" + aPersonName
                , "Systemová zprava:\n" + e.getMessage());            
        }
    }

    @Override
    public String createPerson(Person aPerson) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
                em.persist(aPerson);
        //        em.flush();  // Force insert and receive the id of the person
            em.getTransaction().commit();
            return aPerson.getCode();
        } catch (EntityExistsException e) {
            throw new AplWebException(Response.Status.CONFLICT
                , "Osoba s ID=" + aPerson.getId() + " jiz existuje"
                , "");            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status. INTERNAL_SERVER_ERROR
                , "Chyba pri vkladani nove osoby se jmenem=" + aPerson.getName()
                , "Systemova zprava:\n" + e.getMessage());            
        }
    }

    @Override
    public int deletePersonByCode(String aPersonCode) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
                String qStr = "DELETE FROM Person p WHERE p.code = :code";
                Query query = em.createQuery(qStr);
                int delCount = query.setParameter("code", aPersonCode).executeUpdate();
            em.getTransaction().commit();
            return delCount;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status. INTERNAL_SERVER_ERROR
                , "Chyba pri ruseni osoby s kodem=" + aPersonCode
                , "Systemova zprava:\n" + e.getMessage());            
        }
    }

    
    @Override
    public int deletePersonByName(String aPersonName) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
                String qStr = "DELETE FROM Person p WHERE p.name = :name";
                Query query = em.createQuery(qStr);
                int delCount = query.setParameter("name", aPersonName).executeUpdate();
            em.getTransaction().commit();
            return delCount;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri ruseni osoby se jmenem=" + aPersonName
                , "Systemova zprava:\n" + e.getMessage());            
        }
    }

    @Override
    public void deleteAllPeople() {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("TRUNCATE TABLE person");
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri ruseni vsech osob"
                , "Systemová zprava:\n" + e.getMessage());            
        }
    }

    
    @Override
    public boolean personWithCodeExist(String aPersonCode) {
        EntityManager em = EMF.createEntityManager();
        try {
            Query query = em.createNativeQuery(
                "select count(*) cnt from person where code ='" +  aPersonCode + "'");        
            Integer cnt = (Integer)query.getSingleResult();
            return (cnt > 0);
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri hledani osoby s kodem=" + aPersonCode
                , "Systemova zprava:\n" + e.getMessage());
        }
    }

    @Override
    public boolean personWithNameExist(String aPersonName) {
        EntityManager em = EMF.createEntityManager();
        try {
            Query query = em.createNativeQuery(
                "select count(*) cnt from person where name = '" + aPersonName + "'");
            Integer cnt = (Integer)query.getSingleResult();
            return (cnt > 0);
        } catch (Exception e) {
            throw new AplWebException(Response.Status.INTERNAL_SERVER_ERROR
                , "Chyba pri hledani osoby se jmenem=" + aPersonName
                , "Systemova zprava:\n" + e.getMessage());
        }
    }    
}
