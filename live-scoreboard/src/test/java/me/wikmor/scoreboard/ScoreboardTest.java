package me.wikmor.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void addMatch_shouldReturnError_whenSameCountryAddedToScoreboard() {
        // Given
        Scoreboard scoreboard = new Scoreboard();
        String homeTeam = "Mexico";
        String awayTeam = "Mexico";

        // When
        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.addMatch(homeTeam, awayTeam);
        });
        assertEquals("A team cannot play a match against itself.", exception.getMessage());
    }

}
