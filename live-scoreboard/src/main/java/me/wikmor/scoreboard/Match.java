package me.wikmor.scoreboard;

public class Match {

    private final String homeTeam; // TODO Change String to Team/Country enum? Prevent blank names?
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
        if (homeTeamScore < 0) {
            throw new IllegalArgumentException("The home team score cannot be a negative number.");
        }
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        if (awayTeamScore < 0) {
            throw new IllegalArgumentException("The away team score cannot be a negative number.");
        }
        this.awayTeamScore = awayTeamScore;
    }
}
