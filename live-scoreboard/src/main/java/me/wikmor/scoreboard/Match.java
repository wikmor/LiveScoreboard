package me.wikmor.scoreboard;

public class Match {

    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;

    public Match(String homeTeam, String awayTeam) {
        if (homeTeam.equalsIgnoreCase(awayTeam)) { // TODO check non-standard characters or spacing?
            throw new IllegalArgumentException("A team cannot play a match against itself.");
        }

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }
}
