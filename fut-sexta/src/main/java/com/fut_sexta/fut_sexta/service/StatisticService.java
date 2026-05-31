package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.DTO.output.ArtilheiroDTO;
import com.fut_sexta.fut_sexta.DTO.output.MatchGoalDetailOutput;
import com.fut_sexta.fut_sexta.model.Match;
import com.fut_sexta.fut_sexta.model.User;
import com.fut_sexta.fut_sexta.repository.GoalRepository;
import com.fut_sexta.fut_sexta.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CurrencyEditor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final GoalRepository goalRepository;
    private final MatchRepository matchRepository;
    private final CurrentUserService currentUserService;

    public List<ArtilheiroDTO> findArtilheiros(){
        return goalRepository.findArtilheiros(currentUser().getId());
    }

    public List<Match> listarHistorico(){
        return matchRepository.findByFinishedTrueAndUserId(currentUser().getId());
    }



    public List<MatchGoalDetailOutput> getMatchGoalDetails(Long id){
        return goalRepository.findMatchGoalDetails(id, currentUser().getId());
    }

    private User currentUser(){
        return currentUserService.getCurrentUser();
    }
}
