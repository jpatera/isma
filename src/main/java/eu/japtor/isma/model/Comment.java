/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import java.util.Date;

/**
 *
 * @author Honza
 */
public class Comment {
    private Long id;
    private Date created;
    private Person author;
    private String header;
    private String text;

    public Comment() {
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
