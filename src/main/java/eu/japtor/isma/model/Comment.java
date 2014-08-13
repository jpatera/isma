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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Honza
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
    private Person author;
    private String header;
    private String text;

    public Comment() {
        // ..for JAXB & JPA
    }

    public Comment(Person author, String header) {
        this.author = author;   // Mandatory
        this.header = header;   // Mandatory
    }

    public Long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public Person getAuthor() {
        return author;
    }

    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }
    
    
}
