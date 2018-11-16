package project.mobile.mnemosyne;

import java.util.ArrayList;

//Singleton class to hold test data
public class Data {
    //Holder of all decks
    private static ArrayList<Deck> decks;
    private static final Data ourInstance = new Data();

    public static Data getInstance() {
        return ourInstance;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void deleteDeck(Deck deck) {
        decks.remove(deck);
    }

    private Data() {
        //Test data example
        Deck deck1 = new Deck("Brain Anatomy", "Basic brain parts anatomy cramming");

        //Add cards for the Brain Anatomy flashcards.
        Card testCard = new Card("Name this brain part", "frontallobe_q", "Cerebral Cortex Definition...", "frontallobe_a", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Recall this brain part.", "hypothalamus_q", "Definition...", "hypothalamus_a", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Name the part!", "temporallobe_q", "Definition...", "temporallobe_a", -1);
        deck1.addCardInDeck(testCard);

        //Deck 2
        Deck deck2 = new Deck("Know your Toons!", "Fun brain teaser to test your cartoon, game, anime character trivia");
        testCard = new Card("Name this character", "mickey_mouse", "Mickey Mouse", null, -1);
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "bugs_bunny", "Bugs Bunny", null, -1);
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "tweety", "Tweety", null, -1);
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "donald_duck", "Donald Duck", null, -1);
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "daffy_duck", "Daffy Duck", null, -1);
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "goofy", "Goofy", null, -1);
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "peter_griffin", "Peter Griffin", null, -1);
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "porky_pig", "Porky Pig", null, -1);
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "elmer_fudd", "Elmer Fudd", null, -1);
        deck2.addCardInDeck(testCard);


        //Deck 3
        Deck deck3 = new Deck("Pokemon challenge", "Can you name all Pokemons ?");
        testCard = new Card("Name this pokemon figure", "pikachu", "Pikachu", null, -1);
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "vulpix", "Vulpix", null, -1);
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "ponyta", "Ponyta", null, -1);
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "drowzee", "Drowzee", null, -1);
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "lapras", "Lapras", null, -1);
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "vaporeon", "Vaporeon", null, -1);
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "articuno", "Articuno", null, -1);
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "mewtwo", "Mewtwo", null, -1);
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "flaaffy", "Flaaffy", null, -1);
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "cutiefly", "Cutiefly", null, -1);
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "sawsbuck", "Sawsbuck", null, -1);
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "piplup", "Piplup", null, -1);
        deck3.addCardInDeck(testCard);



        //All decks holder array list
        decks = new ArrayList<>();

        //Added the same decks to test list
        decks.add(deck1);
        decks.add(deck2);
        decks.add(deck3);
        decks.add(deck2);
        decks.add(deck1);
        decks.add(deck3);
        decks.add(deck2);
        decks.add(deck1);
        decks.add(deck2);
        decks.add(deck3);
    }
}
