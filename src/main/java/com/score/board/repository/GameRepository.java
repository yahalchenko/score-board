package com.score.board.repository;

import com.score.board.domain.Game;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GameRepository implements Repository<String, Game> {
    private final Map<String, Game> db;

    @Override
    public Game save(Game game) {
        var id = UUID.randomUUID().toString();
        game.setId(id);
        db.put(game.getId(), game);
        return game;
    }

    @Override
    public Game update(Game game) {
        return db.put(game.getId(), game);
    }

    @Override
    public Optional<Game> getById(String id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<Game> getAll() {
        return db.values().stream().toList();
    }

    @Override
    public void deleteById(String id) {
        db.remove(id);
    }
}