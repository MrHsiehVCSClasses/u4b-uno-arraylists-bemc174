package u6pp;


import java.util.ArrayList;
import java.util.Scanner;



public class UnoFrontend {

    public void play(){
        Scanner scan = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        CardStack drawPile = new CardStack();
        for (String color : Card.COLORS){
            for (String value: Card.VALUES){
                drawPile.push(new Card(color,value));
            }
        }

        drawPile.shuffle();
        for (Player p : players) {
            for (int i = 0; i<7; i++){
                p.addCard(drawPile.pop());
            }
        }
        Uno game = new Uno(players, drawPile);

        while (!game.hasWinner()){
            Player current = game.getCurrentPlayer();
            System.out.println("Top card: " + game.getTopCard());
            System.out.println(current);

            System.out.println("Play a card (COLOR VALUE) or DRAW:");
            String input = scan.nextLine();

            if(input.equalsIgnoreCase("DRAW")){
                game.drawCard();
            } else {
                String[] parts = input.split(" ");
                if(parts.length == 2){
                    Card attempt = new Card(parts[0], parts[1]);
                    if(!game.playCard(attempt)){
                        System.out.println("Invalid play.");
                    }
                }
            }
        }
        Player winner = game.getWinner();
        if (winner != null){
            System.out.println("Winner: " + winner.getName());
        }
    }
}
