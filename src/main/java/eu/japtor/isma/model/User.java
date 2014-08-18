/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Honza
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity()
@Table(name="usr")
@Access(AccessType.FIELD)
//@Embeddable
public class User implements Serializable {
    private static final long serialVersionUID = 13L;   
    
    @XmlElement(name="id")
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)    
        private Long id;        // Surrogate ID
    @XmlElement(name="code")
        private String code;    // Domain user UUID code
    @XmlElement(name="fullName")
        private String fullName;
    @XmlElement(name="loginName")
        private String loginName;
    @XmlElement(name="loginPwd")
        private String loginPwd;

    
    public User() {
        // ..for JAXB & JPA
    }

    public User(String aCode, String aLoginName, String aFullName) {
        this.code = aCode;
        this.loginName = aLoginName;
        this.fullName = aFullName == null ? "NO NAME" : aFullName;
    }

    @Override
    public String toString() {
        return "User {" + "id=" + id + ", code=" + code + ", loginName=" + loginName + '}';
    }
    
    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getLoginName() {
        return loginName;
    }

//    public void setUserName(String loginName) {
//        this.loginName = loginName;
//    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }    
}
