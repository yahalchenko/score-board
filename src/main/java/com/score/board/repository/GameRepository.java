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
        return null;
    }

    @Override
    public Game update(Game game) {
        return null;
    }

    @Override
    public Optional<Game> getById(String id) {
        return null;
    }

    public List<Game> getAll() {
        return List.of();
    }

    @Override
    public void deleteById(String id) {
    }
}
