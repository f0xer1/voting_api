package com.workshop.bouali.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(unique=true)
    private String username;
    private String password;
    private int age;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade =  CascadeType.ALL)
    private Set<Vote> votes = new LinkedHashSet<>();
}
