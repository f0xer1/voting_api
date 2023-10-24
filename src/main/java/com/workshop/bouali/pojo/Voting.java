package com.workshop.bouali.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "voting")
public class Voting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private  String subtitle;
}
