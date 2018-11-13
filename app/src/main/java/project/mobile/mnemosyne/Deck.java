package project.mobile.mnemosyne;

public class Deck {

    private Integer ai = 0; // An initializer for the array can and will be changed to our needs.
    private String title; // Deck's Title.
    private String desc; // Decks's Description.
    private Card[] useableDeck = new Card[ai];

    public Deck (String title, String desc, Integer ai, Card[] usableDeck) {
        this.title = title;
        this.desc = desc;
        this.ai = ai;
        this.useableDeck = usableDeck;
    }






}
