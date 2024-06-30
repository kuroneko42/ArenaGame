package na.kuroneko.arenagame2.round;

public class Round {
    private int currentRound = 1;

    public int getCurrentRound() {
        return currentRound;
    }

    public int nextRound() {
        currentRound++;
        return currentRound;
    }

    public void reset() {
        currentRound = 1;
    }
}
