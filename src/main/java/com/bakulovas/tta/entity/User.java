package com.bakulovas.tta.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),
                                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @Column(name="executor")
    private boolean executor;
    @Column(name="free")
    private boolean free;
    @Column(name="ticket_counter")
    private int ticketCounter;
    @Column(name="reject_counter")
    private int rejectCounter;

//    public User() {
//    }
//
//    public User(int id, String loginName, String password, boolean tempPassword, String email, String firstName,
//                String lastName, boolean active, Office office, List<Role> roles, boolean executor, boolean free,
//                int ticketCounter, int rejectCounter) {
//        this.id = id;
//        this.loginName = loginName;
//        this.password = password;
//        this.tempPassword = tempPassword;
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.active = active;
//        this.office = office;
//        this.roles = roles;
//        this.executor = executor;
//        this.free = free;
//        this.ticketCounter = ticketCounter;
//        this.rejectCounter = rejectCounter;
//    }
//
//    public User(String loginName, String password, String email, String firstName, String lastName, Office office) {
//        this(0, loginName, password, true, email, firstName, lastName, true, office, new ArrayList<>(), false, true, 0,0);
//    }

}
