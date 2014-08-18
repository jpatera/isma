/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Honza
 */
@Embeddable
@Access(AccessType.FIELD)
public class Actor implements Serializable {
    private static final long serialVersionUID = 13L;   
    
//    @Id
//    @GeneratedValue(strategy=GenerationType.SEQUENCE)    
        private Long id;        // Surrogate ID
//    @XmlElement(name="code")
        private String code;    // Domain code
//    @XmlElement(name="name")
        private String name;
    
    
    public Actor() {
        // ..for JAXB & JPA
    }

    public Actor(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Actor {" + "id=" + id + ", code=" + code + ", name=" + name + '}';
    }
    
    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
