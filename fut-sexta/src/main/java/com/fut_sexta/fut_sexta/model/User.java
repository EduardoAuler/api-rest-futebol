package com.fut_sexta.fut_sexta.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "username", unique = true, nullable = false)
    private String username;


    @Column(name = "password", nullable = false)
    private String password;


    @OneToMany(mappedBy = "user")
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Match> matches = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Goal> goals = new ArrayList<>();
}
