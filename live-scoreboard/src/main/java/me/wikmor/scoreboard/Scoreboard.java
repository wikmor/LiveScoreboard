package me.wikmor.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private final List<Match> matches = new ArrayList<>();

    public void addMatch(String home, String away) {
        matches.add(new Match(home, away));
    }

    public List<Match> getMatches() {
        return new ArrayList<>(matches);
    }

}