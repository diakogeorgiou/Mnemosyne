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

    public void addDeck(Deck deck) {
        decks.add(deck);
    }

    private Data() {
        //Test data example
        Deck deck1 = new Deck("Brain Anatomy", "Basic brain parts anatomy cramming");

        //Add cards for the Brain Anatomy flashcards.
        Card testCard = new Card("Name this brain part", "frontallobe_q", "Cerebral Cortex Definition...", "frontallobe_a");
        deck1.addCardInDeck(testCard);
        testCard = new Card("Recall this brain part.", "hypothalamus_q", "Definition...", "hypothalamus_a");
        deck1.addCardInDeck(testCard);
        testCard = new Card("Name the part!", "temporallobe_q", "Definition...", "temporallobe_a");
        deck1.addCardInDeck(testCard);

        //Deck 2
        Deck deck2 = new Deck("Know your Toons!", "Fun brain teaser to test your cartoon, game, anime character trivia");
        testCard = new Card("Name this character", "mickey_mouse", "Mickey Mouse", "mickey_mouse");
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "super_mario", "Super Mario", "super_mario");
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "bugs_bunny", "Bugs Bunny", "bugs_bunny");
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "tweety", "Tweety", "tweety");
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "donald_duck", "Donald Duck", "donal_duck");
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "daffy_duck", "Daffy Duck", "daffy_duck");
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "goofy", "Goofy", "goofy");
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "peter_griffin", "Peter Griffin", "peter_griffin");
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "porky_pig", "Porky Pig", "porky_pig");
        deck2.addCardInDeck(testCard);
        testCard = new Card("Name this character", "elmer_fudd", "Elmer Fudd", "elmer_fudd");
        deck2.addCardInDeck(testCard);


        //Deck 3
        Deck deck3 = new Deck("Pokemon challenge", "Can you name all Pokemons ?");
        testCard = new Card("Most popular lighting rat?", "pikachu", "Pikachu", "pikachu");
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "vulpix", "Vulpix", "vulpix");
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "ponyta", "Ponyta", "ponyta");
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "drowzee", "Drowzee", "drowzee");
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "lapras", "Lapras", "lapras");
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "vaporeon", "Vaporeon", "vaporeon");
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "articuno", "Articuno", "articuno");
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "mewtwo", "Mewtwo", "mewtwo");
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "flaaffy", "Flaaffy", "flaafy");
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "cutiefly", "Cutiefly", "cutiefly");
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "sawsbuck", "Sawsbuck", "sawsbuck");
        deck3.addCardInDeck(testCard);
        testCard = new Card("Name this pokemon figure", "piplup", "Piplup", "pilpup");
        deck3.addCardInDeck(testCard);



        //All decks holder array list
        decks = new ArrayList<>();

        //Added the same decks to test list
        decks.add(deck1);
        decks.add(deck2);
        decks.add(deck3);

    }
}
