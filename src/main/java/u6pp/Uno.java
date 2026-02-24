package u6pp;

import java.util.ArrayList;

public class Uno {
    
    private ArrayList<Player> players;
    private CardStack drawPile;
    private CardStack discardPile;
    private int currentPlayer;
    private boolean  clockwise;

    public Uno(int numPlayers){
        players = new ArrayList<>();
        for (int i =1; i <= numPlayers; i++){
            players.add(new Player ("p " + i));
        }
        drawPile = new CardStack();
        discardPile = new CardStack();
        currentPlayer = 0;
        clockwise = false;

        for (String color : Card.COLORS){
            for (String value : Card.VALUES){
                drawPile.push(new Card(color, value));
            }
        }
        drawPile.shuffle();
        for (Player p : players){
            for (int i = 0; i < 7; i++){
                reshuffleIfNeeded();
                p.addCard(drawPile.pop());
            }
        }
        reshuffleIfNeeded();
        discardPile.push(drawPile.pop());
    }


    public Uno(ArrayList<Player> players, CardStack drawPile){
        this.players = players;
        this.drawPile = drawPile;
        this.discardPile = new CardStack();
        currentPlayer = 0;
        this.clockwise = false;

        reshuffleIfNeeded();
        discardPile.push(drawPile.pop());
    }

    public Uno(ArrayList<Player> players, CardStack drawPile, CardStack discardPile, int startingPlayer, boolean clockwise){
        this.players = players;
        this.drawPile = drawPile;
        this.discardPile = discardPile;
        currentPlayer = startingPlayer;
        this.clockwise = clockwise;
    } 

    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    public Card getTopCard(){
        return discardPile.peek();
    }

    public Card getTopDiscard(){
        return discardPile.peek();
    }

    public Player getNextPlayer(){
        int nextIndex;
        if(!clockwise){
            nextIndex = (currentPlayer + 1) % players.size();
        } else{
            nextIndex = (currentPlayer -1 + players.size()) % players.size();
        }
        return players.get(nextIndex);
    }

    private void nextPlayer(){
        advanceTurn();
    }

    private void advanceTurn(){
        if(!clockwise){
            currentPlayer = (currentPlayer + 1) % players.size();
        }
        else {
            currentPlayer = (currentPlayer - 1 + players.size()) % players.size();
        }
    }

    private void reshuffleIfNeeded(){
        if(drawPile.isEmpty()){
            Card top = discardPile.pop();

            while(!discardPile.isEmpty()){
                drawPile.push(discardPile.pop());
            }
            drawPile.shuffle();
            discardPile.push(top);
        }
    }

    public void drawCard() {
        reshuffleIfNeeded();
        Card drawn = drawPile.pop();
        if (drawn != null){
            getCurrentPlayer().addCard(drawn);
        }
        nextPlayer();
    }


    public boolean playCard(Card card){
        if(card == null){
            reshuffleIfNeeded();
            Card drawn = drawPile.pop();
            if(drawn !=null){
                getCurrentPlayer().addCard(drawn);
            }
            nextPlayer();
            return true;
        }

        Player p = getCurrentPlayer();
        if (!p.getHand().contains(card)) return false;

        if (!card.canPlayOn(getTopCard())){
            return false;
        }

        Card removed = p.removeCard(card);
        if (removed == null) return false;

        discardPile.push(removed);

        if (removed.getValue().equals(Card.REVERSE)){
            clockwise = !clockwise;
            nextPlayer();
        } else if (removed.getValue().equals(Card.SKIP)){
            nextPlayer();
            nextPlayer();
        } else if (removed.getValue().equals(Card.DRAW_2)){
            nextPlayer();
            drawPileDraw(2);
            nextPlayer();
        } else if (removed.getValue().equals(Card.WILD_DRAW_4)){
            nextPlayer();
            drawPileDraw(4);
            nextPlayer();
        } else{
            nextPlayer();
        }

        return true;
    }

    public boolean playCard(Card card, String col){
        if(card != null &&(card.getValue().equals(Card.WILD) || card.getValue().equals(Card.WILD_DRAW_4))){
            if (col != null && !col.isEmpty()){
                card.setColor(col);
            }
        }
        return playCard(card);
    }

    private void drawPileDraw(int count){
        for (int i = 0; i < count; i++){
            reshuffleIfNeeded();
            Card c = drawPile.pop();
            if (c != null) {
                getCurrentPlayer().addCard(c);
            }
        }
    }

    public boolean hasWinner(){
        for(Player p : players){
            if (p.hasWon()){
                return true;
            }
        }
        return false;
    }

    public Player getWinner(){
        for (Player p : players){
            if (p.hasWon()){
                return p;
            }
        }
        return null;
    }
}
