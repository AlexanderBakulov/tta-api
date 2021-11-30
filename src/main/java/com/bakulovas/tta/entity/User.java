package com.bakulovas.tta.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
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
    @Column(name="executor")
    private boolean isExecutor;
    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


    public User(int id, String login, String password, boolean isTempPassword, String email,
                String firstName, String lastName, boolean isActive, boolean isExecutor,
                Office office, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.isTempPassword = isTempPassword;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.isExecutor = isExecutor;
        this.office = office;
        this.role = role;
    }

    public User(String login, String password, String email, String firstName, String lastName,
                Office office, Role role) {
        this(0, login, password, false, email, firstName, lastName, true,
                false, office, role);
    }
}
