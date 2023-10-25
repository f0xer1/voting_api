package com.workshop.bouali.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private  String vote;

    @ManyToOne
    @JoinColumn(name = "voting_id")
    private Voting voting;

}
