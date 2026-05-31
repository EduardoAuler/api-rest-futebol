package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.DTO.output.MatchGoalDetailOutput;
import com.fut_sexta.fut_sexta.exception.MatchAlreadyFinishedException;
import com.fut_sexta.fut_sexta.exception.MatchNotFoundException;
import com.fut_sexta.fut_sexta.exception.SameTeamMatchException;
import com.fut_sexta.fut_sexta.model.*;
import com.fut_sexta.fut_sexta.repository.GoalRepository;
import com.fut_sexta.fut_sexta.repository.MatchRepository;
import com.fut_sexta.fut_sexta.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final PlayerService playerService;
    private final GoalRepository goalRepository;
    private final TeamService teamService;
    private final CurrentUserService currentUserService;


    public Match getById(Long id){return matchRepository.findByIdAndUserId(id, currentUser().getId()).orElseThrow(() -> new MatchNotFoundException("Partida não encontrada"));
    }

    private void checkMatchIsFinished(Match match){
        if (match.isFinished()) throw new MatchAlreadyFinishedException("Partida já encerrada");
    }



    public Match createMatch(Long teamAId, Long teamBId, int minutos){
        if(teamAId.equals(teamBId)) throw new SameTeamMatchException("Os times devem ser diferentes");

        String teamA = teamService.getById(teamAId).getName();
        String teamB = teamService.getById(teamBId).getName();

        Match match = new Match(teamA,teamB,minutos, currentUser());
        return matchRepository.save(match);
    }


    @Transactional
    public Match endMatch(Long id){
        Match match = getById(id);

        checkMatchIsFinished(match);

        match.setFinished(true);

        return matchRepository.save(match);
    }

    @Transactional
    public Match addGoal(Long matchId, Long playerId, TeamSide side){
        Match match = getById(matchId);

        checkMatchIsFinished(match);

        Player player = playerService.getById(playerId);

        Goal goal = new Goal(player, match, side, currentUser());

        if (side == TeamSide.A){
            match.setScoreA(match.getScoreA() + 1);
        }
        if (side == TeamSide.B){
            match.setScoreB(match.getScoreB() + 1);
        }

        goalRepository.save(goal);

        return match;
    }


    @Transactional
    public Match removeGoal(Long goalId){
        Goal goal = goalRepository.findByIdAndUserId(goalId, currentUser().getId()).orElseThrow(() -> new EntityNotFoundException("Gol não encontrado"));

        Match match = goal.getMatch();

        checkMatchIsFinished(match);

        match.removeGoal(goal);

        if (goal.getTeamSide() == TeamSide.A){
            match.setScoreA(match.getScoreA() - 1);
        }

        if (goal.getTeamSide() == TeamSide.B){
            match.setScoreB(match.getScoreB() - 1);
        }

        goalRepository.delete(goal);

        return match;
    }


    private User currentUser(){
        return currentUserService.getCurrentUser();
    }

}
