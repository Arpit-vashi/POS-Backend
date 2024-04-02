package org.pgs.postp.model;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "Users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long userID;

    @Column(name = "Username", unique = true, nullable = false)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private BigInteger phone;

    @Column(name = "Role", nullable = false)
    private String role; // Changed to String datatype

    // Constructors
    public UserModel() {
    }

    public UserModel(String username, String password, String name, String email, BigInteger phone, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Getters and Setters
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public BigInteger getPhone() {return phone;}

    public void setPhone(BigInteger phone) {this.phone = phone;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}
}
