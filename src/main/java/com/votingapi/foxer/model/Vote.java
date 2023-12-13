package com.votingapi.foxer.model;

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
    private  String vote;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "voting_id")
    private Voting voting;

}
