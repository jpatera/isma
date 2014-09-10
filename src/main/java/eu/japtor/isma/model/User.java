/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model;

import static eu.japtor.isma.utils.StringUtils.stringHasValue;
import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Honza
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity()
@Table(name="usr")
@Access(AccessType.FIELD)
public class User implements Serializable {
    private static final long serialVersionUID = 13L;   
    
//    @XmlElement(name="id")
    @Id
//    @SequenceGenerator(name="Usr_Gen", sequenceName="Usr_Seq")    
    @GeneratedValue(strategy=GenerationType.SEQUENCE)  
//    @GeneratedValue(generator="Emp_Gen")    
//        @DecimalMin(value = "1")
        private Long id;        // Surrogate ID
//    @XmlElement(name="code")
        private String code;    // Domain user (UUID) code
//    @XmlElement(name="fullName")
//    @XmlElement(name="loginName")
      @NotNull(message = "Nezadané login jméno.")
      @Length(min = 4, max = 20)
        private String loginName;
//    @XmlElement(name="loginPwd")
        private String loginPwd;
        private String firstName;
        private String lastName;
        private String fullName;
//        @Email(message="{user.wrong.email}", regexp = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
      @Email(message="Špatná emailová adresa", regexp = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
        private String email;

    
    public User() {
        // ..for JAXB & JPA
    }

    public User(String aCode
            , String aLoginName
            , String aLoginPwd
            , String aFirstName
            , String aLastName
            , String aEmaiAddress) {
        if (!stringHasValue(aCode)) {
            throw new IllegalArgumentException("Code must not be empty");
        }
        if (!stringHasValue(aLoginName)) {
            throw new IllegalArgumentException("Login name must not be empty");
        }
        if (!stringHasValue(aLoginPwd)) {
            throw new IllegalArgumentException("Login password must not be empty");
        }
        if (!stringHasValue(aFirstName) && !stringHasValue(aLastName)) {
            throw new IllegalArgumentException("One of first or last name must not be empty");
        }
        this.code = aCode;
        this.loginName = aLoginName;
        this.loginPwd = aLoginPwd;
        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.fullName = makeupFullName(aFirstName, aLastName);
    }

    private static String makeupFullName(String aFirstName, String aLastName) {
        if ( stringHasValue(aFirstName) && stringHasValue(aLastName)) {
            return aFirstName + " " + aLastName;
        } else if (stringHasValue(aFirstName)) {
            return aFirstName;
        } else if (stringHasValue(aLastName)) {
            return aLastName;
        }
        return "NO NAME";
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

    private void setupFullName(String aFullName) {
        this.fullName = aFullName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
