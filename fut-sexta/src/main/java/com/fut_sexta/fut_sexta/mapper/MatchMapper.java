package com.fut_sexta.fut_sexta.mapper;

import com.fut_sexta.fut_sexta.DTO.input.MatchInputDTO;
import com.fut_sexta.fut_sexta.DTO.output.MatchOutputDTO;
import com.fut_sexta.fut_sexta.model.Match;
import com.fut_sexta.fut_sexta.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MatchMapper {

    private final TeamService service;


    public MatchOutputDTO toDTO(Match match){
        return new MatchOutputDTO(match.getTeamAName(), match.getTeamBName(),
                match.getMinutos(), match.getScoreA(), match.getScoreB(),
                match.getLocalDate());
    }

}
