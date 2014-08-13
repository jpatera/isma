/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.resources;

import eu.japtor.isma.model.Person;
import java.util.List;
import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
    
    public PersonResourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
    @org.junit.Test
    public void testGetPeople() {
        System.out.println("getPeople");
        Integer maxRec = null;
        PersonResource instance = null;
        List<Person> expResult = null;
        List<Person> result = instance.getPeople(maxRec);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonByName method, of class PersonResource.
     */
    @org.junit.Test
    public void testGetPersonByName() {
        System.out.println("getPersonByName");
        String aPersonName = "";
        PersonResource instance = null;
        Response expResult = null;
        Response result = instance.getPersonByName(aPersonName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

//    /**
//     * Test of getPersonByCode method, of class PersonResource.
//     */
//    @org.junit.Test
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
//
//    /**
//     * Test of createPerson method, of class PersonResource.
//     */
//    @org.junit.Test
//    public void testCreatePerson() {
//        System.out.println("createPerson");
//        Person aPerson = null;
//        UriInfo uriInfo = null;
//        PersonResource instance = null;
//        Response expResult = null;
//        Response result = instance.createPerson(aPerson, uriInfo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deletePersonByCode method, of class PersonResource.
//     */
//    @org.junit.Test
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
//
//    /**
//     * Test of deletePersonByName method, of class PersonResource.
//     */
//    @org.junit.Test
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
//
//    /**
//     * Test of deleteAllPeople method, of class PersonResource.
//     */
//    @org.junit.Test
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
