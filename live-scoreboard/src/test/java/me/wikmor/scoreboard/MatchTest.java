package me.wikmor.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatchTest {

    @Test
    void constructor_shouldReturnError_whenHomeTeamEqualsAwayTeam() {
        // Given
        String homeTeam = "Mexico";
        String awayTeam = "Mexico";

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
        String homeTeam = "Mexico";
        String awayTeam = "MEXICO";

        // When
        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Match(homeTeam, awayTeam);
        });
        assertEquals("A team cannot play a match against itself.", exception.getMessage());
    }

    @Test
    void setHomeTeamScore_shouldReturnError_whenNegativeScoreProvided() {
        // Given
        int negativeScore = -1;
        Match match = new Match("Mexico", "Canada");

        // When
        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            match.setHomeTeamScore(negativeScore);
        });
        assertEquals("The home team score cannot be a negative number.", exception.getMessage());
    }
}
