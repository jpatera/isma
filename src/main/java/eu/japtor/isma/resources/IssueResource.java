/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.resources;

import eu.japtor.isma.errors.AplWebException;
import eu.japtor.isma.model.Issue;
import eu.japtor.isma.model.IssueRepo;
import eu.japtor.isma.model.User;
import eu.japtor.isma.model.UserRepo;
import eu.japtor.isma.persistence.AtomIdGenerator;
import eu.japtor.isma.persistence.IssueRepoElnk;
import eu.japtor.isma.persistence.UserRepoElnk;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
@Path("/issues")
public class IssueResource {
    private final IssueRepo issueRepo;
    private final User fantomUser;

//    @Inject(Scope=Singleton.class, name="deploy")
    
    protected IssueResource(EntityManagerFactory aEMF) {
        // Create user repo with given Ent.Man.Factory:
        issueRepo = new IssueRepoElnk(aEMF);
        fantomUser = new User(AtomIdGenerator.nextId(), "fantom", "123456", "Fake", "User", null);
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
    public List<Issue> getIssues(@QueryParam("max") Integer maxRec) {
        List<Issue> issues = issueRepo.getIssues(maxRec);
        return issues;
    }

        
    
    
    /**
     * Adds a new user resource from the given json or XML format
     *
     * @param aIssue
     * @param   aUriInfo For link builder
     * @return  HTML with link to a new user
     * @throws  AplWebException
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.TEXT_HTML})
//    public Response createUser(@Valid User aUser, @Context UriInfo aUriInfo) {
    public Response createUser(Issue aIssue, @Context UriInfo aUriInfo) {
//        if (issueRepo.issueWithLoginNameExist(aUser.getLoginName())) {
//            throw new AplWebException(Response.Status.CONFLICT, "Přihlašovací jméno  " + aUser.getLoginName() + " je již obsazeno.", "Použij jiné.");
//        }

//        Issue newIssue = new Issue(
//                  fantomUser
//                , aIssue.getSummary()
//        );
        Long newIssueId = issueRepo.createIssue(aIssue);
        return Response.status(Response.Status.CREATED)
            .entity("Nové issue s ID=" + aIssue.getId() + "  je vytvořeno.")
            .header("Location",
                    aUriInfo.getAbsolutePath().toString()
                  + "byId/"
                  + newIssueId).build();
    }
    
    
}
