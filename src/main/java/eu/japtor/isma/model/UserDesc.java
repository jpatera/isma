/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import java.io.Serializable;

/**
 *
 * @author Honza
 */
//@Embeddable
public class UserDesc implements Serializable {
    private static final long serialVersionUID = 13L;   
    
    private String code;    // Domain code
    private String loginName;
    private String fullName;
    private String email;
    
    
    public UserDesc() {
        // ..for JAXB & JPA
    }

    public UserDesc(User aUser) {
        if (aUser == null) {
            throw new IllegalArgumentException("UserVo: user is null.");
        }
        this.code = aUser.getCode();
        this.loginName = aUser.getLoginName();
        this.fullName = aUser.getFullName();
        this.email = aUser.getEmail();
    }

    @Override
    public String toString() {
        return "UserVo {code=" + code
             + ", loginName=" + loginName
             + ", fullName=" + fullName
             + ", email=" + email + '}';
    }
    

    public String getCode() {
        return code;
    }
    
    public String getLoginName() {
        return loginName;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
}
