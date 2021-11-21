package com.bakulovas.tta.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @Column(name="login")
    private String loginName;
    @Column(name="password")
    private String password;
    @Column(name="temp")
    private boolean tempPassword;
    @Column(name="email")
    private String email;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="active")
    private boolean active;
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
    private boolean executor;
    @Column(name="free")
    private boolean free;
    @Column(name="ticket_counter")
    private int ticketCounter;
    @Column(name="reject_counter")
    private int rejectCounter;

    public User(int id, String loginName, String password, boolean tempPassword, String email,
                String firstName, String lastName, boolean active, Office office, Division division,
                Set<Role> roles, boolean executor, boolean free, int ticketCounter, int rejectCounter) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.tempPassword = tempPassword;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.office = office;
        this.division = division;
        this.roles = roles;
        this.executor = executor;
        this.free = free;
        this.ticketCounter = ticketCounter;
        this.rejectCounter = rejectCounter;
    }

    public User(String loginName, String password, String email, String firstName, String lastName, Office office, Division division) {
        this(0, loginName, password, false, email, firstName, lastName, true, office, division, new HashSet<>(), false, false, 0, 0);
    }
}
