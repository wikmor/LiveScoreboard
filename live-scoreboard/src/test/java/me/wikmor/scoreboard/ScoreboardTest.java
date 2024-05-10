package me.wikmor.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void addMatch_shouldReturnError_whenSameCountryWithDifferentCapitalizationAddedToScoreboard() {
        // Given
        Scoreboard scoreboard = new Scoreboard();
        String homeTeam = "Mexico";
        String awayTeam = "MEXICO";

        // When
        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.addMatch(homeTeam, awayTeam);
        });
        assertEquals("A team cannot play a match against itself.", exception.getMessage());
    }

    @Test
    void updateMatchScore_shouldReturnUpdatedScoreboard_whenScoreChanged() {
        // Given
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.addMatch("Home", "Away");

        // When
        scoreboard.updateMatchScore("Home", "Away", 1, 0);

        // Then
        Match match = scoreboard.getMatches().get(0);
        assertEquals(1, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());
    }

    @Test
    void finishMatch_shouldReturnEmptyScoreboard_whenAllMatchesRemovedFromScoreboard() {
        // Given
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.addMatch("Home", "Away");

        // When
        scoreboard.finishMatch("Home", "Away");

        // Then
        assertTrue(scoreboard.getMatches().isEmpty());
    }

    @Test
    void finishMatch_shouldReturnOneMatch_whenOneMatchRemovedFromScoreboard() {
        // Given
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.addMatch("Mexico", "Canada");
        scoreboard.addMatch("Spain", "Brazil");

        // When
        scoreboard.finishMatch("Mexico", "Canada");

        // Then
        assertEquals(1, scoreboard.getMatches().size());
        assertEquals("Spain", scoreboard.getMatches().get(0).getHomeTeam());
        assertEquals("Brazil", scoreboard.getMatches().get(0).getAwayTeam());
    }

    @Test
    void getSummary_shouldReturnOrderedScoreboard_whenCalled() throws InterruptedException {
        // Given
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.addMatch("Mexico", "Canada");
        Thread.sleep(100);
        scoreboard.addMatch("Spain", "Brazil");
        Thread.sleep(100);
        scoreboard.addMatch("Germany", "France");
        Thread.sleep(100);
        scoreboard.addMatch("Uruguay", "Italy");
        Thread.sleep(100);
        scoreboard.addMatch("Argentina", "Australia");

        scoreboard.updateMatchScore("Mexico", "Canada", 0, 5);
        scoreboard.updateMatchScore("Spain", "Brazil", 10, 2);
        scoreboard.updateMatchScore("Germany", "France", 2, 2);
        scoreboard.updateMatchScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateMatchScore("Argentina", "Australia", 3, 1);

        // When
        List<Match> actualMatches = scoreboard.getSummary();

        // Then
        assertEquals(actualMatches.get(0).getHomeTeam(), "Uruguay");
        assertEquals(actualMatches.get(0).getAwayTeam(), "Italy");
        assertEquals(actualMatches.get(1).getHomeTeam(), "Spain");
        assertEquals(actualMatches.get(1).getAwayTeam(), "Brazil");
        assertEquals(actualMatches.get(2).getHomeTeam(), "Mexico");
        assertEquals(actualMatches.get(2).getAwayTeam(), "Canada");
        assertEquals(actualMatches.get(3).getHomeTeam(), "Argentina");
        assertEquals(actualMatches.get(3).getAwayTeam(), "Australia");
        assertEquals(actualMatches.get(4).getHomeTeam(), "Germany");
        assertEquals(actualMatches.get(4).getAwayTeam(), "France");
    }

}
