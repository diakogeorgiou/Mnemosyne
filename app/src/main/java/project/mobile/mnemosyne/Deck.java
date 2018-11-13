package project.mobile.mnemosyne;

import java.util.ArrayList;

public class Deck {
    private String title; // Deck's Title.
    private String desc; // Decks's Description.
    ArrayList<Card> cards; //Cards in deck

    public Deck (String title, String desc){
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void addCardInDeck(Card card) {
        if (this.cards == null) {
            cards = new ArrayList<>();
        }
        cards.add(card);
    }

}
