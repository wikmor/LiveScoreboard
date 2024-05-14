#  Live Football World Cup Scoreboard library

This project is a simple library that implements a Live Football World Cup Scoreboard. It tracks ongoing matches and their scores, providing functionalities to start a match, update scores, finish a match, and retrieve a summary of ongoing matches.

## Features

1. **Start a New Match**: Initialize a match with a home team and away team, starting with a score of 0-0.
2. **Update Score**: Update the scores for a match in progress by providing the absolute scores for both the home and away teams.
3. **Finish Match**: Remove a match that is currently in progress from the scoreboard.
4. **Get Summary**: Retrieve a summary of all matches in progress, ordered by their total score. Matches with the same total score are ordered by the most recently started match.

## Installation

### To include this library in your project, follow the steps below:

```bash
./gradlew clean build
```

### In your Java project main directory:
Create e.g. libs folder:
```bash
mkdir libs
```
Put ``live-scoreboard-1.0.0.jar`` from ``build/libs`` in library project to the ``/libs`` folder in your project.

Add to ``build.gradle``:
```gradle
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
}
```

## Usage

```java
Team brazil = new Team("Brazil");
Team germany = new Team("Germany");
Team spain = new Team("Spain");
Team italy = new Team("Italy");

Scoreboard scoreboard = new Scoreboard();

scoreboard.addMatch(brazil, germany);
Thread.sleep(100);
scoreboard.addMatch(spain, italy);

scoreboard.updateScore(brazil, germany, 3, 2);
scoreboard.updateScore(spain, italy, 3, 2);

scoreboard.getSummary().forEach(match ->
        System.out.println(match.getHomeTeam().getName() + " " + match.getHomeTeam().getScore() + " - " +
                           match.getAwayTeam().getName() + " " + match.getAwayTeam().getScore())
);

scoreboard.finishMatch(brazil, germany);

System.out.println("--------------------------------------------------------------------------------------");

scoreboard.getSummary().forEach(match ->
        System.out.println(match.getHomeTeam().getName() + " " + match.getHomeTeam().getScore() + " - " +
                match.getAwayTeam().getName() + " " + match.getAwayTeam().getScore())
);
```

## Assumptions:
- This library is intended for simplicity
- Decreasing the match score is permissible, for example, if a goal is initially deemed valid but is later confirmed not to be a goal after VAR verification.
- Each match is uniquely identified by its home and away teams.
- Similar to removing an item from a list in Java, we don't throw an exception when finishing a match that doesn't exist.
- While exceptions are thrown and caught in the test cases, there is no custom exception handling. In the future, creating custom exceptions to provide more specific error messages can be beneficial.
- The current library implementation is not thread-safe. Concurrent modifications to the scoreboard by multiple threads can lead to an inconsistent state. Therefore, it is not advisable to use this library in a multithreaded environment without proper synchronization mechanisms.
- Logging functionality is not included in the library. In a production environment, implementing logging would be crucial for monitoring and troubleshooting the application.
