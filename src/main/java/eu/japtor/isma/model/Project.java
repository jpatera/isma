/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Honza
 */
@Entity
public class Project {
    private static int MAX_ISSUES = 5;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)      
        private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date created;
    @Transient
        private User owner;        
        private String header;
        private String description;
        private List<Long> issueIds;


    protected Project() {
    }

    private Project(User aOwner, String aHeader, String aDescription) {
        this.owner = aOwner;     // Mandatory
        this.header = aHeader;   // Mandatory
        this.description = aDescription;
        this.created = new Date(System.currentTimeMillis());
        this.issueIds = new ArrayList();
    }

    // Static factory - we need checking of not nullable fields
    public static Project buildNewProject (User aOwner, String aHeader, String aDescription) {
       if ( (aOwner == null) || (aHeader.isEmpty()) ) {
           return null;
       }
       return new Project(aOwner, aHeader, aDescription);
    }
    
    
    public Long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public User getOwner() {
        return owner;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Long> getIssueIds() {
        return issueIds;
    }

    // Business logic:
    //   One project cannot have more than MAX_ISSUES issues
    boolean addIssue(Long aIssueId) {
        if (issueIds.size() >= MAX_ISSUES) {
            return false;
        }
        issueIds.add(aIssueId);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Issue)) {
            return false;
        }
        Project other = (Project)o;
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
