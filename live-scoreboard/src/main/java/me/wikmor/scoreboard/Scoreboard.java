package me.wikmor.scoreboard;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void finishMatch(String home, String away) {
        Iterator<Match> it = matches.iterator();
        while (it.hasNext()) {
            Match match = it.next();
            if (match.getHomeTeam().equals(home) && match.getAwayTeam().equals(away)) {
                it.remove();
            }
        }
    }

    public List<Match> getMatches() {
        return new ArrayList<>(matches);
    }

}
