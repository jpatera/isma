/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Honza eeeee
 */
@Entity
@Access(AccessType.FIELD)
public class Comment implements Serializable {
    private static final long serialVersionUID = 13L;   
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
        private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
        private Date created;
        private String header;
        private String text;
    @Column(updatable=false)
        private String author_code;
    @Column(updatable=false)
        private String author_name;
//    @Embedded
//    @AttributeOverrides({
//      @AttributeOverride(name   =  "code",
//          column = @Column(name = "author_code")),
//      @AttributeOverride(name   =  "fullName",
//            column = @Column(name = "author_name")),
//    })
    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

        
        
        
    public Comment() {
        // ..for JAXB & JPA
    }

    public Comment(Date created, String header, String text, User author) {
        this.created = created;
        this.header = header;
        this.text = text;
        this.author_code = author.getCode();
        this.author_name = author.getFullName();
    }

//    public Comment(Actor actor, String header) {
////        this.author = author;   // Mandatory
//        this.header = header;   // Mandatory
//    }

    public Long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

//    public Long getAuthorId() {
//        return authorId;
//    }

    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }

    public String getAuthor_code() {
        return author_code;
    }

    public String getAuthor_name() {
        return author_name;
    }
    
    
}
