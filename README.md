# Live Football World Cup Score Board Library
Project represents a library with service that shows all the ongoing matches and their scores.

## Table of Contents
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Usage](#usage)
<!-- * [License](#license) -->

## Technologies Used
- Java 18
- Assert4J, Mockito, JUnit5, EasyRandom
- Lombok

## Features
List the ready features here:
- Start a new game, assuming initial score 0 – 0 and adding it the scoreboard.
This should capture following parameters:
a. Home team
b. Away team
- Update score. This should receive a pair of absolute scores: home team score and away team score.
- Finish game currently in progress. This removes a match from the scoreboard.
- Get a summary of games in progress ordered by their total score. The games with the same
   total score will be returned ordered by the most recently started match in the scoreboard.

## Usage
The main business and DB logic is represented by `GameRepository.class` and `GameService.class` classes.
<br/> Use `GameRepository.save(Game game)` to create Game in DB
<br/> Use `GameRepository.update(Game game)` to update Game in DB
<br/> Use `GameRepository.deleteById(String id)` to delete Game in DB
<br/> Use `GameService.findAllSortedByTotalScoreAndMostRecentStartTime()` to find all games (represented by Game type) in progress ordered by their total score. The games with the same total score will be returned ordered by the most recently started match in the scoreboard.
<br/> Use `GameService.findAllGameSummariesSortedByTotalScoreAndMostRecentStartTime()` to find all game summaries (represented by String type) in progress ordered by their total score. The games with the same total score will be returned ordered by the most recently started match in the scoreboard.