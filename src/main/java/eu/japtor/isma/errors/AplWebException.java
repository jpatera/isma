/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.japtor.isma.errors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Honza
 */
public class AplWebException extends WebApplicationException {
    private static final long serialVersionUID = -13L;

    /**
     *
     * @param aStatus   HTTP status
     * @param aMsg      Original mesage
     * @param aDesc     Detailed error description
     */
    public AplWebException(Status aStatus, String aMsg, String aDesc) {
        super(Response.status(aStatus)
                .entity(aMsg + "\n" + aDesc)
                .type(MediaType.TEXT_HTML)
                .build());
    }
         
    private AplWebException() {
    }

}
