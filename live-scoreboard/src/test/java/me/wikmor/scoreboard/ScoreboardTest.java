package me.wikmor.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {

    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    void addMatch_shouldReturnNotEmptyList_whenMatchAddedToScoreboard() {
        // Given
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("Canada");
        Match expectedMatch = new Match(homeTeam, awayTeam);

        // When
        scoreboard.addMatch(homeTeam, awayTeam);

        // Then
        assertFalse(scoreboard.getMatches().isEmpty());
        assertEquals(expectedMatch, scoreboard.getMatches().get(0));
        assertEquals(expectedMatch.getHomeTeam().getName(), scoreboard.getMatches().get(0).getHomeTeam().getName());
        assertEquals(expectedMatch.getAwayTeam().getName(), scoreboard.getMatches().get(0).getAwayTeam().getName());
    }

    @Test
    void addMatch_shouldReturnError_whenHomeTeamIsAlreadyPlayingAgainstAnotherTeam() {
        // Given
        Team homeTeam = new Team("Mexico");
        Team anotherTeam = new Team("Canada");
        Team awayTeam = new Team("Spain");
        scoreboard.addMatch(homeTeam, anotherTeam);

        // When
        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.addMatch(homeTeam, awayTeam);
        });
        assertEquals("Home team is already playing against another team.", exception.getMessage());
    }

    @Test
    void addMatch_shouldReturnError_whenSpecifiedMatchAlreadyExists() {
        // Given
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("Canada");
        scoreboard.addMatch(homeTeam, awayTeam);

        // When
        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboard.addMatch(homeTeam, awayTeam);
        });
        assertEquals("You cannot add a match that already exists.", exception.getMessage());
    }

    @Test
    void updateMatchScore_shouldReturnUpdatedScoreboard_whenScoreChanged() {
        // Given
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("Canada");
        scoreboard.addMatch(homeTeam, awayTeam);

        // When
        scoreboard.updateScore(homeTeam, awayTeam, 1, 0);

        // Then
        Match match = scoreboard.getMatches().get(0);
        assertEquals(1, match.getHomeTeam().getScore());
        assertEquals(0, match.getAwayTeam().getScore());
    }

    @Test
    void finishMatch_shouldReturnEmptyScoreboard_whenAllMatchesRemovedFromScoreboard() {
        // Given
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("Canada");
        scoreboard.addMatch(homeTeam, awayTeam);

        // When
        scoreboard.finishMatch(homeTeam, awayTeam);

        // Then
        assertTrue(scoreboard.getMatches().isEmpty());
    }

    @Test
    void finishMatch_shouldReturnOneMatch_whenOneMatchRemovedFromScoreboard() {
        // Given
        scoreboard.addMatch(new Team("Mexico"), new Team("Canada"));
        scoreboard.addMatch(new Team("Spain"), new Team("Brazil"));

        // When
        scoreboard.finishMatch(new Team("Mexico"), new Team("Canada"));

        // Then
        assertEquals(1, scoreboard.getMatches().size());
        assertEquals("Spain", scoreboard.getMatches().get(0).getHomeTeam().getName());
        assertEquals("Brazil", scoreboard.getMatches().get(0).getAwayTeam().getName());
    }

//    @Test
//    void getSummary_shouldReturnOrderedScoreboard_whenCalled() throws InterruptedException {
//        // Given
//        scoreboard.addMatch("Mexico", "Canada");
//        Thread.sleep(100);
//        scoreboard.addMatch("Spain", "Brazil");
//        Thread.sleep(100);
//        scoreboard.addMatch("Germany", "France");
//        Thread.sleep(100);
//        scoreboard.addMatch("Uruguay", "Italy");
//        Thread.sleep(100);
//        scoreboard.addMatch("Argentina", "Australia");
//
//        scoreboard.updateScore("Mexico", "Canada", 0, 5);
//        scoreboard.updateScore("Spain", "Brazil", 10, 2);
//        scoreboard.updateScore("Germany", "France", 2, 2);
//        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
//        scoreboard.updateScore("Argentina", "Australia", 3, 1);
//
//        // When
//        List<Match> actualMatches = scoreboard.getSummary();
//
//        // Then
//        assertEquals("Uruguay", actualMatches.get(0).getHomeTeam().getName());
//        assertEquals(actualMatches.get(0).getAwayTeam().getName(), "Italy");
//        assertEquals(actualMatches.get(1).getHomeTeam().getName(), "Spain");
//        assertEquals(actualMatches.get(1).getAwayTeam().getName(), "Brazil");
//        assertEquals(actualMatches.get(2).getHomeTeam().getName(), "Mexico");
//        assertEquals(actualMatches.get(2).getAwayTeam().getName(), "Canada");
//        assertEquals(actualMatches.get(3).getHomeTeam().getName(), "Argentina");
//        assertEquals(actualMatches.get(3).getAwayTeam().getName(), "Australia");
//        assertEquals(actualMatches.get(4).getHomeTeam().getName(), "Germany");
//        assertEquals(actualMatches.get(4).getAwayTeam().getName(), "France");
//    }

}
