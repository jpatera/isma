/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.resources;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Honza
 */
//@Singleton
@Path("/inj")
public class InjectResource {
    private int counter=11;
    
    @Inject
    InjectTest injectTest;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String getInjection() {
        counter ++;
        return
               injectTest.getInjectedText()
             + "\nResource counter: " + Integer.toString(counter);
    }
}
