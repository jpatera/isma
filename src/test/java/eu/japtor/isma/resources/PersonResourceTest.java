/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.resources;

import eu.japtor.isma.model.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Honza
 */
public class PersonResourceTest {
    private static EntityManagerFactory EMF;
    
    public PersonResourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        EMF = Persistence.createEntityManagerFactory("ismaDemo");        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {

    }
    
    @After
    public void tearDown() {
    }

    
    
    
    
    /**
     * Test of getPeople method, of class PersonResource.
     */
    @Test
    public void testGetPeople() {
        System.out.println("getPeople");
        Integer maxRec = 2;
//        PersonResource instance = new PersonResource(Persistence.createEntityManagerFactory("ismaDemo"));
        PersonResource instance = new PersonResource(EMF);
        List<Person> expResult = null;
        List<Person> result = instance.getPeople(maxRec);
        assertFalse(result.isEmpty());
        assertTrue(result.size()==2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonByName method, of class PersonResource.
     */
    @Test
    public void testGetPersonByName() {
        System.out.println("getPersonByName");
        String aPersonName = "Barbora";
//        PersonResource instance = new PersonResource(Persistence.createEntityManagerFactory("ismaDemo"));
        PersonResource instance = new PersonResource(EMF);
        Response.Status expResponse = Response.Status.OK;
        Response response = instance.getPersonByName(aPersonName);
        assertThat(response.getStatus(),is(expResponse.getStatusCode()));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

//    /**
//     * Test of getPersonByCode method, of class PersonResource.
//     */
//    @Test
//    public void testGetPersonByCode() {
//        System.out.println("getPersonByCode");
//        String aPersonCode = "";
//        PersonResource instance = null;
//        Response expResult = null;
//        Response result = instance.getPersonByCode(aPersonCode);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of createPerson method, of class PersonResource.
     */
    @Test
    public void testCreateAndDeletePerson() {
//        System.out.println("createPerson");
//        PersonResource instance = new PersonResource(EMF);
//        Response response;
//        Person person;
//        String personName = "Ziggy";
//        UriInfo uriInfo = UriInfo();    // Lze vyresit bud mockitem nebo embedded serverem (Grizzly, asi taky Jetty ?)
//        
//        person = new Person("code would be changed", personName);
//        response = instance.createPerson(person, uriInfo);
//        assertThat(response.getStatus(),is(Response.Status.CREATED.getStatusCode()));        
//
//        person = new Person("code would be changed", personName);
//        response = instance.createPerson(person, uriInfo);
//        assertThat(response.getStatus(),is(Response.Status.CONFLICT.getStatusCode()));        
//        
//        response = instance.deletePersonByName(personName);
//        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());   
//        
//        response = instance.deletePersonByName(personName);
//        assertTrue(response.getStatus() == Response.Status.NOT_FOUND.getStatusCode());   

    }
    
    
//    /**
//     * Test of deletePersonByName method, of class PersonResource.
//     */
//    @Test
//    public void testDeletePersonByName() {
//        System.out.println("deletePersonByName");
//        String aPersonName = "";
//        PersonResource instance = null;
//        Response expResult = null;
//        Response result = instance.deletePersonByName(aPersonName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    
//    /**
//     * Test of deletePersonByCode method, of class PersonResource.
//     */
//    @Test
//    public void testDeletePersonByCode() {
//        System.out.println("deletePersonByCode");
//        String aPersonCode = "";
//        PersonResource instance = null;
//        Response expResult = null;
//        Response result = instance.deletePersonByCode(aPersonCode);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }


//    /**
//     * Test of deleteAllPeople method, of class PersonResource.
//     */
//    @Test
//    public void testDeleteAllPeople() {
//        System.out.println("deleteAllPeople");
//        PersonResource instance = null;
//        Response expResult = null;
//        Response result = instance.deleteAllPeople();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    
}
