package repository;

import com.score.board.domain.Game;
import com.score.board.repository.GameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class GameRepositoryTest extends BaseTest{
    private Map<String, Game> mockDb;
    private GameRepository gameRepository;
    @BeforeEach
    void setUp() {
        this.mockDb = new ConcurrentHashMap<>();
        this.gameRepository = new GameRepository(mockDb);
    }

    @Test
    void testSave() {
        Game mockedGame = random(Game.class);
        gameRepository.save(mockedGame);
        Assertions.assertEquals(mockedGame, mockDb.get(mockedGame.getId()));
    }

    @Test
    void testUpdate() {
        Game mockedGame = random(Game.class);
        gameRepository.update(mockedGame);
        Assertions.assertEquals(mockedGame, mockDb.get(mockedGame.getId()));
    }

    @Test
    void testGetAll() {
        List<Game> games = randomList(Game.class);
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

        games.forEach(it -> gameRepository.save(it));
        var resultList =  gameRepository.getAll();

        Assertions.assertEquals(secondGame, resultList.get(0));
        Assertions.assertEquals(thirdGame, resultList.get(1));
        Assertions.assertEquals(firstGame, resultList.get(2));
    }

    @Test
    void testDeleteById() {
        var mockedGame = gameRepository.save(random(Game.class));
        gameRepository.deleteById(mockedGame.getId());
        Assertions.assertEquals(mockedGame, gameRepository.getById(mockedGame.getId()));
    }

    @AfterEach
    void tearDown() {
        mockDb.clear();
    }
}