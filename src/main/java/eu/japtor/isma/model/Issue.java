/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
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
    @ManyToOne (cascade = {CascadeType.REFRESH, CascadeType.MERGE})
        private User assignedTo;
        private String summary;
        private String description;
        private Integer priority;
    @Enumerated(EnumType.STRING)        
        private IssueStatus status;
//    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
        private Collection<Comment> comments;

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

    public void addComment(Comment aComment) {
      this.comments.add(aComment);
      if(aComment.getIssue() != this)
        aComment.setIssue(this);
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


    // Returns defensive copy
    public Collection<Comment> getComments() {
        return new ArrayList<Comment>(comments);
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Issue)) {
            return false;
        }
        Issue other = (Issue)o;
        // if the id is missing, return false
        if (id == null) return false;
        // equivalence by id
        return id.equals(other.getId());
    }

    
    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }    
}
