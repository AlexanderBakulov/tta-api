package com.bakulovas.tta.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

}
