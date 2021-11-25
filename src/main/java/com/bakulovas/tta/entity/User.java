package com.bakulovas.tta.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;
    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    private Division division;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),
                                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @Column(name="executor")
    private boolean isExecutor;
    @Column(name="free")
    private boolean isFree;
    @Column(name="ticket_counter")
    private int ticketCounter;
    @Column(name="reject_counter")
    private int rejectCounter;


    public User(int id, String login, String password, boolean isTempPassword, String email,
                String firstName, String lastName, boolean isActive, Office office, Division division,
                Set<Role> roles, boolean isExecutor, boolean isFree, int ticketCounter, int rejectCounter) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.isTempPassword = isTempPassword;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.office = office;
        this.division = division;
        this.roles = roles;
        this.isExecutor = isExecutor;
        this.isFree = isFree;
        this.ticketCounter = ticketCounter;
        this.rejectCounter = rejectCounter;
    }

    public User(String login, String password, String email, String firstName, String lastName, Office office, Division division) {
        this(0, login, password, false, email, firstName, lastName, true, office, division, new HashSet<>(), false, false, 0, 0);
    }
}
