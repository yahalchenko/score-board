package com.score.board.service;

import com.score.board.domain.Game;
import com.score.board.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private final Repository<String, Game> gameRepository;
    @Override
    public List<Game> findAllSortedByTotalScoreAndMostRecentStartTime() {
        log.debug("Get all games sorted by by total score and most recent start time");
        return gameRepository.getAll().stream().sorted(Comparator.comparing(Game::getTotalScore)
                .thenComparing(it -> it.getStartTime().getTime()).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllGameSummariesSortedByTotalScoreAndMostRecentStartTime() {
        log.debug("Get all game summaries sorted by by total score and most recent start time");
        return gameRepository.getAll().stream().sorted(Comparator.comparing(Game::getTotalScore)
                .thenComparing(it -> it.getStartTime().getTime()).reversed()).map(Game::toString).collect(Collectors.toList());
    }
}
