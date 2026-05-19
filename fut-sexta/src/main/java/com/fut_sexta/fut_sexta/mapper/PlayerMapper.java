package com.fut_sexta.fut_sexta.mapper;

import com.fut_sexta.fut_sexta.DTO.output.PlayerOutputDTO;
import com.fut_sexta.fut_sexta.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerOutputDTO toDTO(Player p){
        return new PlayerOutputDTO(p.getId(), p.getName());
    }
}
