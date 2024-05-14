package me.wikmor.scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

public class Scoreboard {

    private final List<Match> matches = new ArrayList<>();

    public void addMatch(Team home, Team away) {
        for (Match match : matches) {
            if (match.getHomeTeam().equals(home) && match.getAwayTeam().equals(away)) {
                throw new IllegalArgumentException("You cannot add a match that already exists.");
            }
            if (match.getHomeTeam().equals(home)) {
                throw new IllegalArgumentException("Home team is already playing against another team.");
            }
            if (match.getAwayTeam().equals(away)) {
                throw new IllegalArgumentException("Away team is already playing against another team.");
            }
        }
        matches.add(new Match(home, away));
    }

    public void updateScore(Team home, Team away, int homeScore, int awayScore) {
        boolean updated = false;
        for (Match match : matches) {
            if (match.getHomeTeam().equals(home) && match.getAwayTeam().equals(away)) {
                match.getHomeTeam().setScore(homeScore);
                match.getAwayTeam().setScore(awayScore);
                updated = true;
                break;
            }
        }
        if (!updated) {
            throw new NoSuchElementException("Match between " + home.getName() + " and " + away.getName() + " not found.");
        }
    }

    public void finishMatch(Team home, Team away) {
        matches.removeIf(match -> match.getHomeTeam().equals(home) && match.getAwayTeam().equals(away));
    }

    public List<Match> getSummary() {
        return matches.stream()
                .sorted(comparing(Match::getTotalScore, reverseOrder())
                        .thenComparing(Match::getStartTime, reverseOrder()))
                .toList();
    }

    List<Match> getMatches() {
        return new ArrayList<>(matches);
    }
}
