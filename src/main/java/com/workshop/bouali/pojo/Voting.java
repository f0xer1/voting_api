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

    @ManyToMany
    @JoinTable(
            name = "voting_pretendants",
            joinColumns = @JoinColumn(name = "voting_id"),
            inverseJoinColumns = @JoinColumn(name = "pretendants_id"))
    private Set<Pretendant> pretendants = new LinkedHashSet<>();

}
