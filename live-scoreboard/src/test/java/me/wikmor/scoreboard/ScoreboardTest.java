package me.wikmor.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void getSummary_shouldReturnOrderedScoreboard_whenCalled() throws InterruptedException {
        // Given
        Team mexico = new Team("Mexico");
        Team canada = new Team("Canada");
        Team spain = new Team("Spain");
        Team brazil = new Team("Brazil");
        Team germany = new Team("Germany");
        Team france = new Team("France");
        Team uruguay = new Team("Uruguay");
        Team italy = new Team("Italy");
        Team argentina = new Team("Argentina");
        Team australia = new Team("Australia");

        scoreboard.addMatch(mexico, canada);
        Thread.sleep(100);
        scoreboard.addMatch(spain, brazil);
        Thread.sleep(100);
        scoreboard.addMatch(germany, france);
        Thread.sleep(100);
        scoreboard.addMatch(uruguay, italy);
        Thread.sleep(100);
        scoreboard.addMatch(argentina, australia);

        scoreboard.updateScore(mexico, canada, 0, 5);
        scoreboard.updateScore(spain, brazil, 10, 2);
        scoreboard.updateScore(germany, france, 2, 2);
        scoreboard.updateScore(uruguay, italy, 6, 6);
        scoreboard.updateScore(argentina, australia, 3, 1);

        // When
        List<Match> actualMatches = scoreboard.getSummary();

        // Then
        assertEquals(uruguay, actualMatches.get(0).getHomeTeam());
        assertEquals(italy, actualMatches.get(0).getAwayTeam());
        assertEquals(spain, actualMatches.get(1).getHomeTeam());
        assertEquals(brazil, actualMatches.get(1).getAwayTeam());
        assertEquals(mexico, actualMatches.get(2).getHomeTeam());
        assertEquals(canada, actualMatches.get(2).getAwayTeam());
        assertEquals(argentina, actualMatches.get(3).getHomeTeam());
        assertEquals(australia, actualMatches.get(3).getAwayTeam());
        assertEquals(germany, actualMatches.get(4).getHomeTeam());
        assertEquals(france, actualMatches.get(4).getAwayTeam());
    }

}
