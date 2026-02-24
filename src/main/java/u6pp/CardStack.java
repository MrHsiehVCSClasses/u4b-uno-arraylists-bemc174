package u6pp;

import java.util.ArrayList;

public class CardStack {

    private ArrayList<Card> stack;

    public CardStack(){
        stack = new ArrayList <> ();
    }

    public void push (Card card){
        if (card != null){
            stack.add(card);
        }
    }

    public Card pop(){
        if (stack.isEmpty()){
            return null;
        }
        return stack.remove(stack.size()-1);
    }

    public Card peek(){
        if (stack.isEmpty()){
            return null;
        }
        return stack.get(stack.size()-1);
    }

    public int getSize() {
        return stack.size();
    }

    public void clear(){
        stack.clear();
    }

    public void shuffle(){
        for(int i = 0; i < stack.size(); i++){
            int j = (int) (Math.random() * stack.size());
            Card temp = stack.get(i);
            stack.set(i, stack.get(j));
            stack.set(j,temp);
        }
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public void addAll(CardStack other){
        if(other == null || other == this) {
            return;
        }
        ArrayList<Card> temp = new ArrayList<>();
        while (!other.isEmpty()){
            temp.add(other.pop());
        }
        for (int i = temp.size() -1; i >= 0; i--){
            this.push(temp.get(i));
        }
    }

    public String toString(){
        return stack.toString();
    }   
}
