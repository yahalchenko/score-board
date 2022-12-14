package com.score.board.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Team {
    private String name;
    private Integer score;
}

