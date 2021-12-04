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
@Table(name="options")
public class UserOptions implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @Column(name="free")
    private boolean isFree;
    @Column(name="on_duty")
    private boolean isOnDuty;
    @Column(name="ticket_counter")
    private int ticketCounter;
    @Column(name="reject_counter")
    private int rejectCounter;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserOptions(int id, boolean isFree, boolean isOnDuty, int ticketCounter,
                       int rejectCounter, User user) {
        this.id = id;
        this.isFree = isFree;
        this.ticketCounter = ticketCounter;
        this.rejectCounter = rejectCounter;
        this.user = user;
    }
}
