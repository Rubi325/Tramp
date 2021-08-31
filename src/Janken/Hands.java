package Janken;

public enum Hands {
    Rock,
    Scissors,
    Paper;

    @Override
    public String toString() {
        switch (this) {
            case Rock     : return "グー";
            case Scissors : return "チョキ";
            case Paper    : return "パー";
        }
        throw new IllegalStateException();
    }

    public static Hands fromInt(int n) {
        switch (n) {
            case 0 : return Rock;
            case 1 : return Scissors;
            case 2 : return Paper;
        }
        throw new IllegalArgumentException(Integer.toString(n));
    }

    public boolean winTo(Hands hand) {
        switch (this) {
            case Rock     : return hand == Scissors;
            case Scissors : return hand == Paper;
            case Paper    : return hand == Rock;
        }
        throw new IllegalStateException();
    }

    public boolean loseTo(Hands hand) {
        return this != hand && !this.winTo(hand); 
    }

}