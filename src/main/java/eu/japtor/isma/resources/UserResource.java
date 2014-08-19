/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.japtor.isma.resources;

import eu.japtor.isma.errors.AplWebException;
import eu.japtor.isma.model.User;
import eu.japtor.isma.model.UserRepo;
import eu.japtor.isma.persistence.AtomIdGenerator;
import eu.japtor.isma.persistence.UserRepoElnk;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.validation.Valid;
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
@Path("users")
public class UserResource {
    private final UserRepo userRepo;

    protected UserResource(EntityManagerFactory aEMF) {
        // Create user repo with given Ent.Man.Factory:
        userRepo = new UserRepoElnk(aEMF);
    }

    
    /**
     * Returns list of users with max records defined by parameter
     * If parametr is not defined or less than 1,
     *   standard numbre of records is returned.
     *
     * @param maxRec    maximum number of records to be returned
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> getUsers(@QueryParam("max") Integer maxRec) {
        List<User> users = userRepo.getUsers(maxRec);
        return users;
    }

    
    @GET
    @Path("/byLogin/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUserByName(@PathParam("login") String aLoginName) {
        User user = userRepo.getUserByLoginName(aLoginName);
        if (user != null) {
            return Response.ok().entity(user)
                .entity(user)
                .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Uživatel s přihlašovacím jménem=" + aLoginName + " nebyl nalezen.")
                .build();
        }
    }

    @GET
    @Path("/byCode/{code}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUserByCode(@PathParam("code") String aUserCode) {
        User user = userRepo.getUserByCode(aUserCode);
        if (user != null) {
            return Response.ok()
                .entity(user)
                .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Uživatel s kódem=" + aUserCode + " nebyl nalezen.")
                .build();
        }
    }

    
    /**
     * Adds a new user resource from the given json or XML format
     *
     * @param   aUser
     * @param   aUriInfo For link builder
     * @return  HTML with link to a new user
     * @throws  AplWebException
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.TEXT_HTML})
//    public Response createUser(@Valid User aUser, @Context UriInfo aUriInfo) {
    public Response createUser(@Valid User aUser, @Context UriInfo aUriInfo) {
        if (userRepo.userWithLoginNameExist(aUser.getLoginName())) {
            throw new AplWebException(Response.Status.CONFLICT, "Přihlašovací jméno  " + aUser.getLoginName() + " je již obsazeno.", "Použij jiné.");
        }

        User newUser = new User(AtomIdGenerator.nextId()
                , aUser.getLoginName()
                , aUser.getLoginPwd()
                , aUser.getFirstName()
                , aUser.getLastName()
                , aUser.getEmail());
        String newUserCode = userRepo.createUser(newUser);
        return Response.status(Response.Status.CREATED)
            .entity("Nový uživatel s přihlašovacím jménem=" + aUser.getLoginName() + "  je vytvořen.")
            .header("Location",
                    aUriInfo.getAbsolutePath().toString()
                  + "byLogin/"
                  + newUserCode).build();
    }

//    
//    /**
//     * Fully updates a user resource from the given json format
//     * If a user resource given by URI does not exist, error is returned 
//     *
//     * @param user
//     * @return // * @throws AppException
//     */    
//    @PUT
//    @Path("{code}")
//    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//    public Response fullyUpdateUser(@PathParam("code") String aUserCode, User aUser)
//            throws AplWebException {
//
//        boolean userWithUserCodeExist = userRepo.userWithUserCodeExist(aUserCode);
//
//        if (userWithUserCodeExist) {
//            // Resource exist under the specified URI, can be updated:
//            userRepo.updateUser(aUser);
//            return Response
//                    .status(Response.Status.OK)
//                    .entity("User has been updated")
//                    .header("Location",
//                          uriInfo.getAbsolutePath().toString()
//                        + "byCode/"
//                        + newUserCode).build();
//        } else {
//            // Resource does not exist - update error:
//            throw new AplWebException(Response.Status.CONFLICT
//                    , "User does not exist - cannot update"
//                    , "Create user resource first");
//        }
//    }
    
    
    /**
     * Permanently removes a user with given code from DB
     *
     * @param   aUserCode
     * @return  Response object
     * @throws  AplWebException
     */
    @DELETE
    @Path("/byCode/{code}")
    @Produces({MediaType.TEXT_HTML})
    public Response deleteUserByCode(@PathParam("code") String aUserCode) {
        int delCount = userRepo.deleteUserByCode(aUserCode);
        if (delCount == 1) {
            return Response.ok()
                .entity("Uživatel s kódem=" + aUserCode + " byl zrušen v DB")
                .build();
        } else if (delCount < 1) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Uživatel s kódem=" + aUserCode + " nebyl nalezen")
                .build();
        } else {
            // Should not happen, user.name should be unique
            return Response.status(Response.Status.NOT_FOUND)
                .entity("VAROVÁNÍ !!! Bylo zrušeno " + delCount + " uživatelů s kódem=" + aUserCode)
                .build();
        }
    }

    
    /**
     * Permanently removes a user with given name from DB
     *
     * @param   aLoginName
     * @return  Response object
     * @throws  AplWebException
     */
    @DELETE
    @Path("/byLogin/{login}")
    @Produces({MediaType.TEXT_HTML})
    public Response deleteUserByLoginName(@PathParam("login") String aLoginName) {
        int delCount = userRepo.deleteUserByLoginName(aLoginName);
        if (delCount == 1) {
            return Response.ok()
                .entity("Uživatel s přihlašovacím jménem=" + aLoginName + " byl zrušen v DB.")
                .build();
        } else if (delCount < 1) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Uživatel s přihlašovacím jménem=" + aLoginName + " nebyl nalezen.")
                .build();
        } else {
            // Should not happen, user.name should be unique
            return Response.status(Response.Status.NOT_FOUND)
                .entity("VAROVÁNÍ !!! Bylo zrušeno " + delCount + " uživatelů s přihlašovacím jménem=" + aLoginName)
                .build();
        }
    }

    
    /**
     * Permanently removes all users from DB
     *
     * @return
     * @throws AplWebException
     */
    @DELETE
    @Produces({MediaType.TEXT_HTML})
    public Response deleteAllUsers() {
        userRepo.deleteAllUsers();
        return Response.status(Response.Status.NO_CONTENT)
            .entity("Všichni uživatelé byli zrušeni z DB")
            .build();
    }
}
