package casino.shared;

public class Card2 {
    public static final String HEARTS = "hearts";
    public static final String DIAMONDS = "diamonds";
    public static final String CLUBS = "clubs";
    public static final String SPADES = "spades";

    private final int value;
    private final String suit;

    public Card2(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public String toString() {
        return value + " " + suit;
    }

    public int getValue() { // Getter
        return value;
    }

    // public void setValue(int value) { // setter
    // this.value = value;
    // }

    public String getSuit() { // Getter
        return suit;
    }

    public boolean isAce() {
        return value == 1;
    }

    public boolean isKing() {
        return value == 13;
    }

    public boolean isQueen() {
        return value == 12;
    }

    public boolean isJack() {
        return value == 11;
    }

};
