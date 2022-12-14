package com.score.board.domain;


import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Optional;

@Data
@Builder
public class Game {
    private String id;
    private String name;
    private Timestamp startTime;
    private Team homeTeam;
    private Team awayTeam;

    public Integer getTotalScore() {
        return homeTeam.getScore() + awayTeam.getScore();
    }

    @Override
    public String toString() {
        return String.join(" - ", Optional.ofNullable(homeTeam).map(it -> homeTeam.getName()).orElse(null),
                Optional.ofNullable(homeTeam).map(it -> awayTeam.getName()).orElse(null));
    }
}
