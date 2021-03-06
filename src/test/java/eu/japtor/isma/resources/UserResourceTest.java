/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.resources;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.japtor.isma.model.User;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Honza
 */
public class UserResourceTest {
    private static EntityManagerFactory EMF;
    
    public UserResourceTest() {
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

    
//    @Test
//    public void testGetUser() throws JsonGenerationException,
//            JsonMappingException, IOException {
//
//        ClientConfig clientConfig = new ClientConfig();
//        clientConfig.register(JacksonFeature.class);
//
//        Client client = ClientBuilder.newClient(clientConfig);
//
//        WebTarget webTarget = client
//                .target("http://localhost:8888/isma/users/byLogin/jarda");
//
//        Builder request = webTarget.request(MediaType.APPLICATION_JSON);
//
//        Response response = request.get();
//        Assert.assertTrue(response.getStatus() == 200);
//
//        User usr = response.readEntity(User.class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        System.out
//                .print("Received user from database *************************** "
//                        + mapper.writerWithDefaultPrettyPrinter()
//                        .writeValueAsString(usr));
//
//    }
    
    
//    /**
//     * Test of getPeople method, of class UserResource.
//     */
//    @Test
//    public void testGetPeople() {
//        System.out.println("getPeople");
//        Integer maxRec = 2;
////        UserResource instance = new UserResource(Persistence.createEntityManagerFactory("ismaDemo"));
//        UserResource instance = new UserResource(EMF);
//        List<User> expResult = null;
//        List<User> result = instance.getPeople(maxRec);
//        assertFalse(result.isEmpty());
//        assertTrue(result.size()==2);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserByName method, of class UserResource.
//     */
//    @Test
//    public void testGetPersonByName() {
//        System.out.println("getPersonByName");
//        String aPersonName = "Barbora";
////        UserResource instance = new UserResource(Persistence.createEntityManagerFactory("ismaDemo"));
//        UserResource instance = new UserResource(EMF);
//        Response.Status expResponse = Response.Status.OK;
//        Response response = instance.getPersonByName(aPersonName);
//        assertThat(response.getStatus(),is(expResponse.getStatusCode()));
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of getPersonByCode method, of class UserResource.
//     */
//    @Test
//    public void testGetPersonByCode() {
//        System.out.println("getPersonByCode");
//        String aPersonCode = "";
//        UserResource instance = null;
//        Response expResult = null;
//        Response result = instance.getPersonByCode(aPersonCode);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of createUser method, of class UserResource.
     */
    @Test
    public void testCreateAndDeleteUser() {
//        System.out.println("createPerson");
//        UserResource instance = new UserResource(EMF);
//        Response response;
//        User person;
//        String personName = "Ziggy";
//        UriInfo uriInfo = UriInfo();    // Lze vyresit bud mockitem nebo embedded serverem (Grizzly, asi taky Jetty ?)
//        
//        person = new User("code would be changed", personName);
//        response = instance.createPerson(person, uriInfo);
//        assertThat(response.getStatus(),is(Response.Status.CREATED.getStatusCode()));        
//
//        person = new User("code would be changed", personName);
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
//     * Test of deletePersonByName method, of class UserResource.
//     */
//    @Test
//    public void testDeletePersonByName() {
//        System.out.println("deletePersonByName");
//        String aPersonName = "";
//        UserResource instance = null;
//        Response expResult = null;
//        Response result = instance.deletePersonByName(aPersonName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    
//    /**
//     * Test of deletePersonByCode method, of class UserResource.
//     */
//    @Test
//    public void testDeletePersonByCode() {
//        System.out.println("deletePersonByCode");
//        String aPersonCode = "";
//        UserResource instance = null;
//        Response expResult = null;
//        Response result = instance.deletePersonByCode(aPersonCode);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }


//    /**
//     * Test of deleteAllPeople method, of class UserResource.
//     */
//    @Test
//    public void testDeleteAllPeople() {
//        System.out.println("deleteAllPeople");
//        UserResource instance = null;
//        Response expResult = null;
//        Response result = instance.deleteAllPeople();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    
}
