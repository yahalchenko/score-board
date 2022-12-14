package com.score.board.service;

import com.score.board.domain.Game;

import java.util.List;

public interface GameService {
    List<Game> findAllSortedByTotalScoreAndMostRecentStartTime();
    List<String> findAllGameSummariesSortedByTotalScoreAndMostRecentStartTime();
}
