package com.fut_sexta.fut_sexta.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "team_players",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Team(String name, User user){
        this.name = name;
        players = new ArrayList<>();
        this.user = user;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

}
