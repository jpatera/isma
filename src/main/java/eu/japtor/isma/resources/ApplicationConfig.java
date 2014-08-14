/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.japtor.isma.resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Honza
 *
 */
@javax.ws.rs.ApplicationPath("/")
public class ApplicationConfig extends ResourceConfig {
    static private final EntityManagerFactory EMF;

    static {
        EMF = Persistence.createEntityManagerFactory("ismaDemo");
    }

    public ApplicationConfig() {
        // Register resources and providers using package-scanning.
//        packages("eu.japtor.isma.resources");
//        List sqlCommands
//                = loadSQLCommands(ApplicationConfig.class
//                        .getResourceAsStream(
//                                "/META-INF/create-database.sql"));

        registerInstances(new PersonResource(EMF));
        
        System.out.println();
        System.out.println("*****   Application config OK   ******");
        System.out.println();
    }
//
//    @Override
//    public Set<Object> getSingletons() {
//        Set<Object> singletons = new HashSet<Object>();
//        PersonResource personResource = new PersonResource(EMF);
//        singletons.add(personResource);
//
//        System.out.println();
//        System.out.println("*****   Singletons defined   ******");
//        System.out.println();
//
//        return singletons;
//    }

//    @Override
//    public Set<Object> getSingletons() {
//        Set<Object> singletons = new HashSet<Object>();
//        PersonResource personResource = new PersonResource(EMF);
//        singletons.add(personResource);
//
//        System.out.println();
//        System.out.println("*****   Singletons defined   ******");
//        System.out.println();
//        
//        return singletons;
//    }

}
