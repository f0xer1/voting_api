package com.workshop.bouali.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "voting")
public class Voting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    @Column(name = "pretendants_column")
    @OneToMany(mappedBy = "voting", orphanRemoval = true)
    private Set<User> pretendants = new LinkedHashSet<>();


    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "voting", orphanRemoval = true)
    private Set<Vote> votes = new LinkedHashSet<>();

}
