package com.score.board.repository;

import com.score.board.BaseTest;
import com.score.board.domain.Game;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class GameRepositoryTest extends BaseTest {
    private Map<String, Game> mockDb;
    private GameRepository gameRepository;
    @BeforeEach
    void setUp() {
        this.mockDb = new ConcurrentHashMap<>();
        this.gameRepository = new GameRepository(mockDb);
    }

    @Test
    void testSave() {
        var savedGame = gameRepository.save(random(Game.class));
        Assertions.assertThat(savedGame).isEqualTo(mockDb.get(savedGame.getId()));
    }

    @Test
    void testUpdate() {
        var savedGame = gameRepository.save(random(Game.class));
        savedGame.setName("Ukraine - England");
        gameRepository.update(savedGame);
        Assertions.assertThat(savedGame).isEqualTo(mockDb.get(savedGame.getId()));
    }

    @Test
    void testGetAll() {
        var games = randomList(Game.class);
        var resultList = new ArrayList<>();
        games.forEach(it -> resultList.add(gameRepository.save(it)));
        Assertions.assertThatList(games).isEqualTo(resultList);
    }

    @Test
    void testDeleteById() {
        var savedGame = gameRepository.save(random(Game.class));
        gameRepository.deleteById(savedGame.getId());
        Assertions.assertThat(mockDb).isEmpty();
    }

    @AfterEach
    void tearDown() {
        mockDb.clear();
    }
}