package me.wikmor.scoreboard;

public class Team {

    private final String name;
    private int score;

    public Team(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The team name cannot be blank.");
        }

        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("The team score cannot be a negative number.");
        }
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Team && ((Team) obj).getName().equalsIgnoreCase(name);
    }
}
