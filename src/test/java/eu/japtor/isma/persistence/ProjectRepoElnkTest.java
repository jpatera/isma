/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.persistence;

import eu.japtor.isma.model.Project;
import eu.japtor.isma.model.ProjectRepo;
import eu.japtor.isma.model.User;
import eu.japtor.isma.model.UserVo;
import java.sql.SQLException;
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
public class ProjectRepoElnkTest {
    private static EntityManagerFactory EMF;
    private static EntityManager em;
    private static User fakeUser;
    private static UserVo fakeUserVo;
    private ProjectRepo projectRepo;
    
    
    public ProjectRepoElnkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        EMF = Persistence.createEntityManagerFactory("ismaDemo");  
        fakeUser = new User(AtomIdGenerator.nextId()
                , "fake_login"
                , "fake_pwd"
                , "fake_first"
                , "fake_last"
                , "fake_login@email.com");  
        fakeUserVo = new UserVo(fakeUser);        
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        EMF.close();
    }
    
    @Before
    public void setUp() {
        projectRepo = new ProjectRepoElnk(EMF);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getProjectsByOwnerCode method, of class ProjectRepoElnk.
     */
//    @Test
//    public void testGetProjectsByOwnerCode() {
//        System.out.println("getProjectsByOwnerCode");
//        String aOwnerCode = "";
//        ProjectRepoElnk instance = null;
//        List<Project> expResult = null;
//        List<Project> result = instance.getProjectsByOwnerCode(aOwnerCode);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getProjectById method, of class ProjectRepoElnk.
     */
//    @Test
//    public void testGetProjectById() {
//        System.out.println("getProjectById");
//        Long projectId = null;
//        ProjectRepoElnk instance = null;
//        Project expResult = null;
//        Project result = instance.getProjectById(projectId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of createProject method, of class ProjectRepoElnk.
     */
    @Test
    public void testCreateProject() {
        System.out.println("Project persistence: createProject");
        Project proj = Project.buildNewProject(fakeUserVo, "Fake header", null);
        assertNull(proj.getId());
        Long newId = projectRepo.createProject(proj);
        assertNotNull(proj.getId());
        Project proj2 = projectRepo.getProjectById(newId);
        assertNotNull(proj2);
        
    }

    /**
     * Test of deleteProject method, of class ProjectRepoElnk.
     */
//    @Test
//    public void testDeleteProject() {
//        System.out.println("deleteProject");
//        Project project = null;
//        ProjectRepoElnk instance = null;
//        instance.deleteProject(project);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
