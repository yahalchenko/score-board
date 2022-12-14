package com.score.board.service;

import com.score.board.domain.Game;
import com.score.board.repository.GameRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.score.board.BaseTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest extends BaseTest {
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    void findAllSortedByTotalScoreAndMostRecentStartTime() {
        var games = randomList(Game.class);
        var firstGame = games.get(0);
        firstGame.setId("1");
        firstGame.setName("Spain - Japan");
        firstGame.getHomeTeam().setScore(1);
        firstGame.getAwayTeam().setScore(2);
        firstGame.setStartTime(Timestamp.valueOf(LocalDateTime.now().minusDays(3)));

        var secondGame = games.get(1);
        secondGame.setId("2");
        secondGame.setName("Brazil - Serbia");
        secondGame.getHomeTeam().setScore(4);
        secondGame.getAwayTeam().setScore(4);
        firstGame.setStartTime(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));

        var thirdGame = games.get(2);
        thirdGame.setId("3");
        thirdGame.setName("Argentina - Croatia");
        thirdGame.getHomeTeam().setScore(2);
        thirdGame.getAwayTeam().setScore(1);
        thirdGame.setStartTime(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));

        when(gameRepository.getAll()).thenReturn(games);

        games.forEach(it -> gameRepository.save(it));
        var resultList =  gameService.findAllSortedByTotalScoreAndMostRecentStartTime();

        Assertions.assertThatList(resultList).isEqualTo(List.of(secondGame, thirdGame, firstGame));
    }

    @Test
    void findAllGameSummariesSortedByTotalScoreAndMostRecentStartTime() {
        var games = randomList(Game.class);
        var firstGame = games.get(0);
        firstGame.setId("1");
        firstGame.getHomeTeam().setName("Spain");
        firstGame.getAwayTeam().setName("Japan");
        firstGame.getHomeTeam().setScore(1);
        firstGame.getAwayTeam().setScore(2);
        firstGame.setStartTime(Timestamp.valueOf(LocalDateTime.now().minusDays(3)));

        var secondGame = games.get(1);
        secondGame.setId("2");
        secondGame.getHomeTeam().setName("Brazil");
        secondGame.getAwayTeam().setName("Serbia");
        secondGame.getHomeTeam().setScore(4);
        secondGame.getAwayTeam().setScore(4);
        firstGame.setStartTime(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));

        var thirdGame = games.get(2);
        thirdGame.setId("3");
        thirdGame.getHomeTeam().setName("Argentina");
        thirdGame.getAwayTeam().setName("Croatia");
        thirdGame.getHomeTeam().setScore(2);
        thirdGame.getAwayTeam().setScore(1);
        thirdGame.setStartTime(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));


        when(gameRepository.getAll()).thenReturn(games);

        games.forEach(it -> gameRepository.save(it));
        var resultList =  gameService.findAllGameSummariesSortedByTotalScoreAndMostRecentStartTime();

        Assertions.assertThatList(resultList).isEqualTo(List.of(
                "Brazil 4 - Serbia 4",
                "Argentina 2 - Croatia 1",
                "Spain 1 - Japan 2"
        ));
    }
}