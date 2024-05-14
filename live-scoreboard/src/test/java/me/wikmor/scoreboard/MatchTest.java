package me.wikmor.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatchTest {

    @Test
    void constructor_shouldReturnError_whenHomeTeamEqualsAwayTeam() {
        // Given
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("Mexico");

        // When
        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Match(homeTeam, awayTeam);
        });
        assertEquals("A team cannot play a match against itself.", exception.getMessage());
    }

    @Test
    void constructor_shouldReturnError_whenHomeTeamEqualsIgnoreCaseAwayTeam() {
        // Given
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("MEXICO");

        // When
        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Match(homeTeam, awayTeam);
        });
        assertEquals("A team cannot play a match against itself.", exception.getMessage());
    }
}
