package me.wikmor.scoreboard;

public class Match {

    private final Team homeTeam;
    private final Team awayTeam;
    private final long startTime;

    public Match(Team homeTeam, Team awayTeam) {
        if (homeTeam.getName().equalsIgnoreCase(awayTeam.getName())) {
            throw new IllegalArgumentException("A team cannot play a match against itself.");
        }

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTime = System.currentTimeMillis();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getTotalScore() {
        return homeTeam.getScore() + awayTeam.getScore();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Match
                && ((Match) obj).getHomeTeam().equals(homeTeam)
                && ((Match) obj).getAwayTeam().equals(awayTeam);
    }
}
