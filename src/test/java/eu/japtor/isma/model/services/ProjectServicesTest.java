/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model.services;

import eu.japtor.isma.model.Project;
import eu.japtor.isma.model.User;
import eu.japtor.isma.model.UserDesc;
import eu.japtor.isma.model.UserRepo;
import eu.japtor.isma.persistence.AtomIdGenerator;
import eu.japtor.isma.persistence.UserRepoElnk;
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
public class ProjectServicesTest {
    private static EntityManagerFactory EMF;
    private static User fakeUser;
    private static UserDesc fakeUserDesc;
    
    public ProjectServicesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        EMF = Persistence.createEntityManagerFactory("ismaDemo");  
        UserRepo userRepo = new UserRepoElnk(EMF);
        fakeUser = new User(AtomIdGenerator.nextId()
                , "fake_login"
                , "fake_pwd"
                , "fake_first"
                , "fake_last"
                , "fake_login@email.com"); 
        userRepo.createUser(fakeUser);
        fakeUserDesc = new UserDesc(fakeUser); 
        assertNotNull(fakeUserDesc);
        assertNotNull(fakeUserDesc.getCode());        
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
     * Test of getProjectOwner method, of class ProjectServices.
     */
    @Test
    public void testGetProjectOwner() {
        System.out.println("getProjectOwner");
        Project project = Project.buildNewProject(fakeUserDesc.getCode(), "Fake header", null);
        assertNotNull(project);
//        ProjectServices projectServices = new ProjectServices(EMF);
        ProjectServices projectServices = new ProjectServices();
        UserDesc projectOwnerDesc = projectServices.getProjectOwner(project);
        assertNotNull(projectOwnerDesc);
        assertEquals("fake_login", projectOwnerDesc.getLoginName());
    }
    
}
