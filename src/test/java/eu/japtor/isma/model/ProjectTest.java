/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
public class ProjectTest {
    private static EntityManagerFactory EMF;
    
    public ProjectTest() {
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
     * Test of fields immediately after new Project creation.
     */
    @Test
    public void testNewProjectFields() {
        System.out.println("New project's fields");
        Project proj = Project.buildNewProject(new User(), "Fake header", null);
        assertNull(proj.getId());
        assertNotNull(proj.getCreated());
        assertNotNull(proj.getOwner());
        assertNotNull(proj.getIssueIds());
        assertEquals(0, proj.getIssueIds().size());
    }


    /**
     * Test of fields immediately after new Project creation.
     */
    @Test
    public void testProjectPersistence() {
        System.out.println("Project persistence");
        Project proj = Project.buildNewProject(new User(), "Fake header", null);
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
            em.persist(proj);
            assertTrue(em.contains(proj));
        em.getTransaction().commit();
        assertTrue(em.contains(proj));
        em.close();
    }
    

//    /**
//     * Test of getIssueIds method, of class Project.
//     */
//    @Test
//    public void testGetIssueIds() {
//        System.out.println("getIssueIds");
//        Project instance = null;
//        List<Long> expResult = null;
//        List<Long> result = instance.getIssueIds();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addIssue method, of class Project.
//     */
//    @Test
//    public void testAddIssue() {
//        System.out.println("addIssue");
//        Long aIssueId = null;
//        Project instance = null;
//        boolean expResult = false;
//        boolean result = instance.addIssue(aIssueId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
