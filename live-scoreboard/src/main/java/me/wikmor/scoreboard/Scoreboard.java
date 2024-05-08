package me.wikmor.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private final List<Match> matches = new ArrayList<>();

    public void addMatch(String home, String away) {
        matches.add(new Match(home, away));
    }

    public void updateMatchScore(String home, String away, int homeScore, int awayScore) {
        for (Match match : matches) {
            if (match.getHomeTeam().equals(home) && match.getAwayTeam().equals(away)) {
                match.setHomeTeamScore(homeScore);
                match.setAwayTeamScore(awayScore);
                break;
            }
        }
    }

    public List<Match> getMatches() {
        return new ArrayList<>(matches);
    }

}
