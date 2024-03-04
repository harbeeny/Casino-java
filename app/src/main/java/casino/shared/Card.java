package casino.shared;

public class Card {
    
    public enum Suit {
        Spades, Diamonds, Clubs, Hearts
    }

    public enum Value {
        
        Two(2), 
        Three(3), 
        Four(4),
        Five(5),
        Six(6),
        Seven(7),
        Eight(8),
        Nine(9),
        Ten(10),
        Jack(11),
        Queen(12),
        King(13),
        Ace(14);

        private final int _val;

        public int Num() {
            return _val;
        }

        Value(int value) {
            _val = value;
        }
    }

    private final Suit _suit;
    private final Value _value;

    public Suit getSuit() {
        return _suit;
    }
    
    public Value getValue() {
        return _value;
    }

    public Card(Suit s, Value v) {
        _suit = s;
        _value = v;
    }

    // public String toString() {
    //     return 
    // }
}
