package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.exception.NameAlreadyExistsException;
import com.fut_sexta.fut_sexta.exception.PlayerNotFoundException;
import com.fut_sexta.fut_sexta.model.Player;
import com.fut_sexta.fut_sexta.model.User;
import com.fut_sexta.fut_sexta.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository repository;
    private final CurrentUserService currentUserService;



    public Player createPlayer(String name){
        if (repository.existsByNameAndUserId(name, currentUser().getId())) throw new NameAlreadyExistsException("Nome já cadastrado");
        return repository.save(new Player(name, currentUser()));
    }

    public List<Player> getPlayers(){
        return repository.findByUserId(currentUser().getId());
    }

    public Player getById(Long id){
        return repository.findByIdAndUserId(id, currentUser().getId()).orElseThrow(() -> new PlayerNotFoundException("Player não encontrado"));
    }

    private User currentUser(){
        return currentUserService.getCurrentUser();
    }

}
