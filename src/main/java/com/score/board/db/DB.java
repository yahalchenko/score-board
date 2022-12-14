package com.score.board.db;

import com.score.board.domain.Game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum DB {
    INSTANCE;
    private final Map<String, Game> storage = new ConcurrentHashMap<>();

    public Map<String, Game> getStorage() {
        return storage;
    }
}
