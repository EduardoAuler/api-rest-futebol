package com.fut_sexta.fut_sexta.controller;


import com.fut_sexta.fut_sexta.DTO.input.PlayerTeamInputDTO;
import com.fut_sexta.fut_sexta.DTO.output.PlayerOutputDTO;
import com.fut_sexta.fut_sexta.mapper.PlayerMapper;
import com.fut_sexta.fut_sexta.model.Player;
import com.fut_sexta.fut_sexta.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService service;
    private final PlayerMapper mapper;


    @PostMapping
    public ResponseEntity<PlayerOutputDTO> createPlayer(@RequestBody @Valid PlayerTeamInputDTO in){
        Player p  = service.createPlayer(in.name());


        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(p));
    }

    @GetMapping
    public ResponseEntity<List<PlayerOutputDTO>> getPlayers(){
        return ResponseEntity.ok(service.getPlayers().stream()
                .map(mapper::toDTO).toList());
    }


}
