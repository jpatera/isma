/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Honza
 */
public class Project {
    private static int MAX_ISSUES = 5;
    private Long id;
    private Date created;
    private Person owner;        
    private String header;
    private String desription;
    private List<Issue> issues;

    public Project() {
    }

    public Project(Person owner, String header) {
        this.owner = owner;     // Mandatory
        this.header = header;   // Mandatory
    }


    // Business logic:
    //   One project cannot have more than MAX_ISSUES issues
    boolean addIssue(Issue aIssue) {
        if (issues.size() >= MAX_ISSUES) {
            return false;
        }
        issues.add(aIssue);
        return true;
    }
    
    
    public Long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public Person getOwner() {
        return owner;
    }

    public String getHeader() {
        return header;
    }

    public String getDesription() {
        return desription;
    }

    public List<Issue> getIssues() {
        return issues;
    }
    
    
}
