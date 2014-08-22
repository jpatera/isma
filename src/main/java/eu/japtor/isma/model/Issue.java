/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Honza
 */
@Entity
@Access(AccessType.FIELD)
public class Issue implements Serializable {
    private static final long serialVersionUID = 13L;       
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
        private Long id;
    @Temporal(TemporalType.DATE)
        private Date created;
    @ManyToOne
        private User createdBy;
    @ManyToOne
        private User assignedTo;
        private String summary;
        private String description;
        private Integer priority;
    @Enumerated(EnumType.STRING)        
        private IssueStatus status;
//    @OneToMany(mappedBy = "issue")
//        private List<Comment> comments;

    public Issue() {
    }

    public Issue(User createdBy, String summary) {
        this.createdBy = createdBy; // Mandatory
        this.summary = summary;     // Mandatory
    }

    public boolean changeStatus(IssueStatus aNewStatus) {
        boolean canChange = false;
        switch (aNewStatus) {
            case PROGRESS:
                canChange = (status == IssueStatus.OPENED);
                break;
            case CLOSED:
                canChange = (status == IssueStatus.OPENED || status == IssueStatus.PROGRESS);
                break;
        }
        if (canChange) {
            status = aNewStatus;
        }
        return canChange;
    }
    
    public Long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public User getAssignedTo() {
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

    public IssueStatus getStatus() {
        return status;
    }

//    public List<Comment> getComments() {
//        return comments;
//    }
    
}
