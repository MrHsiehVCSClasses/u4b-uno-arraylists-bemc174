package u6pp;

import java.util.ArrayList;

public class Card {

    public static String RED = "RED";
    public static String GREEN = "GREEN";
    public static String BLUE = "BLUE";
    public static String YELLOW = "YELLOW";

    public static String ZERO = "0";
    public static String ONE = "1";
    public static String TWO = "2";
    public static String THREE = "3";
    public static String FOUR = "4";
    public static String FIVE = "5";
    public static String SIX = "6";
    public static String SEVEN = "7";
    public static String EIGHT = "8";
    public static String NINE = "9";

    public static String DRAW_2 = "DRAW_2";
    public static String REVERSE = "REVERSE";
    public static String SKIP = "SKIP";
    public static String WILD = "WILD";
    public static String WILD_DRAW_4 = "WILD_DRAW_4";

    // Wild color is the default color for wilds, before they are played. 
    public static String[] COLORS = {RED, GREEN, BLUE, YELLOW, WILD}; 
    public static String[] VALUES = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, 
        DRAW_2, REVERSE, SKIP, WILD, WILD_DRAW_4
    };
    


    private String color;
    private String value;

    public Card(String color, String value){
        if (color == null){
           this.color = ""; 
        } else {
            this.color = color.toUpperCase();
        }
        if (value == null){
            this.value = "";
        } else {
            this.value = value.toUpperCase();
        }
    }
    public String getColor(){
        return color;
    }

    public String getValue(){
        return value;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isWild(){
        return value.equals(WILD) || value.equals(WILD_DRAW_4);
    }

    public boolean trySetColor(String newColor){
        if(!isWild()){
            return false;
        }
        if (newColor == null){
            return false;
        }
        String newCol = newColor.toUpperCase();
        if(!arrayContains(new String[]{RED, GREEN, BLUE, YELLOW,}, newCol)){
            return false;
        }
        this.color = newCol;
        return true;
    }

    public boolean canPlayOn(Card other){
        if (other == null) return false;
        if (this.isWild()) return true;
        if (this.color.equals(other.color)) return true;

        return this.value.equals(other.value);
    }

 
    private boolean arrayContains(String[] arr, String target){
        if (target == null) return false;
        ArrayList<String> list = new ArrayList<>();
        for (String s : arr){
            list.add(s);
        }
        return list.contains(target);
    }

    public String toString(){
        return color + " " + value;
    }

    public boolean equals (Object obj){
        if(!(obj instanceof Card)) {
            return false;
        }
        Card other = (Card) obj;
        return this.color.equals(other.color) && this.value.equals(other.value);
    }
}

