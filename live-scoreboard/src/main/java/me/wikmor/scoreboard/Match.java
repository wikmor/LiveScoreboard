package me.wikmor.scoreboard;

public class Match {

    private final String homeTeam;
    private final String awayTeam;
    private final long startTime;
    private int homeTeamScore;
    private int awayTeamScore;

    public Match(String homeTeam, String awayTeam) {
        if (homeTeam.equalsIgnoreCase(awayTeam)) { // TODO check non-standard characters or spacing?
            throw new IllegalArgumentException("A team cannot play a match against itself.");
        }

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTime = System.currentTimeMillis();
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
}
