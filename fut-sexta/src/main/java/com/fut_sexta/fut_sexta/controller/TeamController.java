package com.fut_sexta.fut_sexta.controller;


import com.fut_sexta.fut_sexta.DTO.input.PlayerTeamInputDTO;
import com.fut_sexta.fut_sexta.DTO.output.TeamOutputDTO;
import com.fut_sexta.fut_sexta.mapper.TeamMapper;
import com.fut_sexta.fut_sexta.model.Team;
import com.fut_sexta.fut_sexta.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService service;
    private final TeamMapper mapper;

    @PostMapping
    public ResponseEntity<TeamOutputDTO> createTeam(@RequestBody @Valid PlayerTeamInputDTO in){
        Team t = service.createTeam(in.name());

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(t));
    }

    @GetMapping
    public ResponseEntity<List<TeamOutputDTO>> getTeams(){
        List<TeamOutputDTO> response = service.getTeams().stream()
                .map(mapper::toDTO).toList();

        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<TeamOutputDTO> changeName(@PathVariable Long id, @RequestBody @Valid PlayerTeamInputDTO in){
        Team team = service.changeName(id, in.name());
        TeamOutputDTO dto = mapper.toDTO(team);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/{id}/add/{playerId}")
    public ResponseEntity<TeamOutputDTO> addPlayer(@PathVariable Long id, @PathVariable Long playerId){
        TeamOutputDTO dto = mapper.toDTO(service.addPlayer(id, playerId));

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/{id}/remove/{playerId}")
    public ResponseEntity<TeamOutputDTO> removePlayer(@PathVariable Long id, @PathVariable Long playerId){
        TeamOutputDTO dto = mapper.toDTO(service.removePlayer(id, playerId));

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        service.deleteTeam(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
