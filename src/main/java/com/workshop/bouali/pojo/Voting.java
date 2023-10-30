package com.workshop.bouali.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "owner")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "voting", cascade =  CascadeType.ALL)
    private Set<Vote> votes = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "voting_pretendants",
            joinColumns = @JoinColumn(name = "voting_id"),
            inverseJoinColumns = @JoinColumn(name = "pretendants_id"))
    private Set<Pretendant> pretendants = new LinkedHashSet<>();

}
