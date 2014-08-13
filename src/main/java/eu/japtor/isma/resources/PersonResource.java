/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.japtor.isma.resources;

import eu.japtor.isma.errors.AplWebException;
import eu.japtor.isma.model.Person;
import eu.japtor.isma.model.PersonRepo;
import eu.japtor.isma.persistence.AtomIdGenerator;
import eu.japtor.isma.persistence.PersonRepoElnk;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Honza
 */
@Path("people")
public class PersonResource {
    private final PersonRepo personRepo;

    protected PersonResource(EntityManagerFactory aEMF) {
        // Create person repo with given Ent.Man.Factory:
        personRepo = new PersonRepoElnk(aEMF);
    }

    
    /**
     * Returns list of people with max records defined by parameter
     * If parametr is not defined or less than 1,
     *   standard numbre of records is returned.
     *
     * @param maxRec    maximum number of records to be returned
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> getPeople(@QueryParam("max") Integer maxRec) {
        List<Person> people = personRepo.getPeople(maxRec);
        return people;
    }

    
    @GET
    @Path("/byName/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getPersonByName(@PathParam("name") String aPersonName) {
        Person person = personRepo.getPersonByName(aPersonName);
        if (person != null) {
            return Response.ok().entity(person)
                .entity(person)
                .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Osoba se jmenem=" + aPersonName + " nebyla nalezena.")
                .build();
        }
    }

    @GET
    @Path("/byCode/{code}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getPersonByCode(@PathParam("code") String aPersonCode) {
        Person person = personRepo.getPersonByCode(aPersonCode);
        if (person != null) {
            return Response.ok()
                .entity(person)
                .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Osoba s kodem=" + aPersonCode + " nebyla nalezena.")
                .build();
        }
    }

    
    /**
     * Adds a new person resource from the given json or XML format
     *
     * @param   aPerson
     * @param   uriInfo For link builder
     * @return  HTML with link to a new person
     * @throws  AplWebException
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.TEXT_HTML})
    public Response createPerson(Person aPerson, @Context UriInfo uriInfo) {
        if (personRepo.personWithNameExist(aPerson.getName())) {
            throw new AplWebException(Response.Status.CONFLICT, "Jmeno  " + aPerson.getName() + " je jiz obsazeno", "Pouzij jine jmeno");
        }

        Person newPerson = new Person(AtomIdGenerator.nextId(), aPerson.getName());
        String newPersonCode = personRepo.createPerson(newPerson);
        return Response.status(Response.Status.CREATED)
            .entity("Nova osoba byla vytvorena s kodem=" + newPersonCode)
            .header("Location",
                    uriInfo.getAbsolutePath().toString()
                  + "byCode/"
                  + newPersonCode).build();
    }

//    
//    /**
//     * Fully updates a person resource from the given json format
//     * If a person resource given by URI does not exist, error is returned 
//     *
//     * @param person
//     * @return // * @throws AppException
//     */    
//    @PUT
//    @Path("{code}")
//    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//    public Response fullyUpdatePerson(@PathParam("code") String aPersonCode, Person aPerson)
//            throws AplWebException {
//
//        boolean personWithCodeExist = personRepo.personWithCodeExist(aPersonCode);
//
//        if (personWithCodeExist) {
//            // Resource exist under the specified URI, can be updated:
//            personRepo.updatePerson(aPerson);
//            return Response
//                    .status(Response.Status.OK)
//                    .entity("Person has been updated")
//                    .header("Location",
//                          uriInfo.getAbsolutePath().toString()
//                        + "byCode/"
//                        + newPersonCode).build();
//        } else {
//            // Resource does not exist - update error:
//            throw new AplWebException(Response.Status.CONFLICT
//                    , "Person does not exist - cannot update"
//                    , "Create person resource first");
//        }
//    }
    
    
    /**
     * Permanently removes a person with given code from DB
     *
     * @param   aPersonCode
     * @return  Response object
     * @throws  AplWebException
     */
    @DELETE
    @Path("/byCode/{code}")
    @Produces({MediaType.TEXT_HTML})
    public Response deletePersonByCode(@PathParam("code") String aPersonCode) {
        int delCount = personRepo.deletePersonByCode(aPersonCode);
        if (delCount == 1) {
            return Response.ok()
                .entity("Osoba s kodem=" + aPersonCode + " byla zrusena v DB")
                .build();
        } else if (delCount < 1) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Osoba s kodem=" + aPersonCode + " nebyla nalezena")
                .build();
        } else {
            // Should not happen, person.name should be unique
            return Response.status(Response.Status.NOT_FOUND)
                .entity("POZOR !!! Bylo zruseno " + delCount + " osob se kodem=" + aPersonCode)
                .build();
        }
    }

    
    /**
     * Permanently removes a person with given name from DB
     *
     * @param   aPersonName
     * @return  Response object
     * @throws  AplWebException
     */
    @DELETE
    @Path("/byName/{name}")
    @Produces({MediaType.TEXT_HTML})
    public Response deletePersonByName(@PathParam("name") String aPersonName) {
        int delCount = personRepo.deletePersonByName(aPersonName);
        if (delCount == 1) {
            return Response.ok()
                .entity("Osoba se jmenem=" + aPersonName + " byla zrusena v DB")
                .build();
        } else if (delCount < 1) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Osoba se jmenem=" + aPersonName + " nebyla nalezena")
                .build();
        } else {
            // Should not happen, person.name should be unique
            return Response.status(Response.Status.NOT_FOUND)
                .entity("POZOR !!! Bylo zruseno " + delCount + " osob se jmenem=" + aPersonName)
                .build();
        }
    }

    
    /**
     * Permanently removes all people from DB
     *
     * @return
     * @throws AplWebException
     */
    @DELETE
    @Produces({MediaType.TEXT_HTML})
    public Response deleteAllPeople() {
        personRepo.deleteAllPeople();
        return Response.status(Response.Status.NO_CONTENT)
            .entity("Vsechny osoby byly zruseny v DB")
            .build();
    }
}
