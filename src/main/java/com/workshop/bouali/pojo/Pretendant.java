package com.workshop.bouali.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "pretendant")
public class Pretendant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String username;
    private String password;
    private int age;

    @ManyToMany(mappedBy = "pretendants")
    private Set<Voting> voting = new LinkedHashSet<>();
}
