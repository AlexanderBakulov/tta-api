package com.bakulovas.tta.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="role")
public class Role implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "role_id"),
//                                inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> users;

}