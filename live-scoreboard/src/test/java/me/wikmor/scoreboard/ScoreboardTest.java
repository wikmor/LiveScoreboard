package me.wikmor.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ScoreboardTest {

    @Test
    void addMatch_shouldReturnNotEmptyList_whenMatchAddedToScoreboard() {
        // Given
        Scoreboard scoreboard = new Scoreboard();
        String homeTeam = "Mexico";
        String awayTeam = "Canada";
        Match expectedMatch = new Match(homeTeam, awayTeam);

        // When
        scoreboard.addMatch(homeTeam, awayTeam);

        // Then
        assertFalse(scoreboard.getMatches().isEmpty());
        assertEquals(expectedMatch.getHomeTeam(), scoreboard.getMatches().get(0).getHomeTeam());
        assertEquals(expectedMatch.getAwayTeam(), scoreboard.getMatches().get(0).getAwayTeam());
    }

}
