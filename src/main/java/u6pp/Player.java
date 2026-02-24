package u6pp;
import java.util.ArrayList;


public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name){
        if (name == null){
            name = "";
        }
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName(){
        return name;
    }
    public ArrayList<Card> getHand(){
        return hand;
    }

    public void addCard(Card card){
        if (card != null){
            hand.add(card);
        }
    }

    public Card removeCard(Card card){
        if (card == null){
            return null;
        }
        int index = hand.indexOf(card);
        if(index == -1){
            return null;
        }
        return hand.remove(index);
    }

    public int getHandSize(){
        return hand.size();
    }

    public boolean hasWon(){
        return hand.isEmpty();
    }

    public String toString() {
        return name + " 's hand: " + hand.toString();
    }

}
