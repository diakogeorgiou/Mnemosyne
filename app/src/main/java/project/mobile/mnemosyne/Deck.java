package project.mobile.mnemosyne;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable {
    private String title; // Deck's Title.
    private String desc; // Decks's Description.
    private ArrayList<Card> cards; //Cards in deck

    private int currentCardNumber;

    public Deck(String title, String desc) {
        this.title = title;
        this.desc = desc;

        this.currentCardNumber = 0;
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

    public int getCurrentCardNumber() {
        return currentCardNumber + 1;
    }

    public int getTotalNumberOfCards() {
        return cards.size();
    }

    public Card getCurrentCard() {
        return cards.get(currentCardNumber);
    }

    public boolean gotoNextCard() {
        if (currentCardNumber < cards.size() - 1) {
            currentCardNumber++;
            return true;
        }

        return false;
    }

    public boolean gotoPreviousCard() {
        if (currentCardNumber > 0) {
            currentCardNumber--;
            return true;
        }

        return false;
    }

    public int getAmountOfKnowCards() {
        int count = 0;
        for (Card card : cards) {
            if (card.isKnown() != null) {
                if (card.isKnown() == true)
                    count++;
            }
        }
        return count;
    }

    public int getAmountOfDontKnowCards() {
        int count = 0;
        for (Card card : cards) {
            if (card.isKnown() != null) {
                if (card.isKnown() == false)
                    count++;
            }
        }
        return count;
    }
}
