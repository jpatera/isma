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
public class Issue {
    private Long id;
    private Date created;
    private Person createdBy;
    private Person assignedTo;
    private String summary;
    private String description;
    private Integer priority;
    private IssStatus status;
    private List<Comment> comments;

    public Issue() {
    }

    public Issue(Person createdBy, String summary) {
        this.createdBy = createdBy; // Mandatory
        this.summary = summary;     // Mandatory
    }

    public boolean changeStatus(IssStatus aNewStatus) {
        boolean canChange = false;
        switch (aNewStatus) {
            case PROGRESS:
                canChange = (status == IssStatus.OPENED);
                break;
            case CLOSED:
                canChange = (status == IssStatus.OPENED || status == IssStatus.PROGRESS);
                break;
        }
        if (canChange) {
            setStatus(aNewStatus);
        }
        return canChange;
    }
    
    public Long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public Person getCreatedBy() {
        return createdBy;
    }

    public Person getAssignedTo() {
        return assignedTo;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriority() {
        return priority;
    }

    public IssStatus getStatus() {
        return status;
    }

    private void setStatus(IssStatus status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }
    
}
