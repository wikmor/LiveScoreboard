package me.wikmor.scoreboard;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

public class Scoreboard {

    private final List<Match> matches = new ArrayList<>();

    public void addMatch(String home, String away) {
        for (Match match : matches) {
            if (match.getHomeTeam().equals(home) && match.getAwayTeam().equals(away)) {
                throw new IllegalArgumentException("You cannot add a match that already exists.");
            }
            if (match.getHomeTeam().equals(home) || match.getAwayTeam().equals(away)) {
                throw new IllegalArgumentException("Home or away team is already playing against another team.");
            }
        }
        matches.add(new Match(home, away));
    }

    public void updateMatchScore(String home, String away, int homeScore, int awayScore) { // TODO Updating by one goal would be more realistic?
        for (Match match : matches) {
            if (match.getHomeTeam().equals(home) && match.getAwayTeam().equals(away)) {
                match.setHomeTeamScore(homeScore);
                match.setAwayTeamScore(awayScore);
                break;
            }
        }
    }

    public void finishMatch(String home, String away) {
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
