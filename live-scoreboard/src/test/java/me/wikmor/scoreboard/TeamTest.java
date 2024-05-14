package me.wikmor.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamTest {

    @Test
    void constructor_shouldReturnError_whenTeamNameIsEmpty() {
        // Given
        String name = "";

        // When
        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Team(name);
        });
        assertEquals("The team name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructor_shouldReturnError_whenTeamNameContainsOnlyWhiteSpaceCodepoints() {
        // Given
        String name = "   ";

        // When
        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Team(name);
        });
        assertEquals("The team name cannot be blank.", exception.getMessage());
    }

    // TODO Check for team name is null

    @Test
    void setScore_shouldReturnError_whenNegativeScoreProvided() {
        // Given
        int negativeScore = -1;
        Team someTeam = new Team("Mexico");

        // When
        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            someTeam.setScore(negativeScore);
        });
        assertEquals("The team score cannot be a negative number.", exception.getMessage());
    }
}
