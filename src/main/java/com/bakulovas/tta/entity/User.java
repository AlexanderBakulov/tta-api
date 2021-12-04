package com.bakulovas.tta.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @Column(name="login")
    private String login;
    @Column(name="password")
    private String password;
    @Column(name="temp")
    private boolean isTempPassword;
    @Column(name="email")
    private String email;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="active")
    private boolean isActive;
//    @ManyToOne
//    @JoinColumn(name = "office_id", nullable = false)
    @Column(name="office_id")
    private int officeId;
    @Column(name="role")
    private String role;


    public User(String login, String password, String email, String firstName, String lastName,
                int officeId, String role) {
        this(0, login, password, false, email, firstName, lastName, true, officeId, role);
    }
}
