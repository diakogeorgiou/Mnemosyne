package project.mobile.mnemosyne;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable {
    private String title; // Deck's Title.
    private String desc; // Decks's Description.
    private ArrayList<Card> cards; //Cards in deck

    private int currentCard;

    public Deck(String title, String desc) {
        this.title = title;
        this.desc = desc;

        this.currentCard = 0;
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

    public Card getCurrentCard() {
        return cards.get(currentCard);
    }

    public boolean gotoNextCard() {
        if (currentCard < cards.size() - 1) {
            currentCard++;
            return true;
        }

        return false;
    }

    public boolean gotoPreviousCard() {
        if (currentCard > 0) {
            currentCard--;
            return true;
        }

        return false;
    }
}
