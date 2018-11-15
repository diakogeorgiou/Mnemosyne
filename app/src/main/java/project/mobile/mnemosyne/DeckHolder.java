package project.mobile.mnemosyne;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class DeckHolder extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_holder);

/*
        //Test data example
        Deck deck1 = new Deck("Brain Anatomy", "Basic brain parts anatomy cramming");
        //Deck deck2 = new Deck("Know your Toons!", "Fun brain teaser to test your cartoon,game,anime character trivia");

        //Add cards for the Brain Anatomy flashcards.
        Card testCard = new Card("Name this brain part", "insert app>src>main>res>raw>baGraphics>cerebralcortexQ.png>>", "Cerebral Cortex Definition...", "insert app>src>main>res>raw>baGraphics>cerebralcortexA.png", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("How is this area called?", "insert app>src>main>res>raw>baGraphics>frontallobeQ.png>>", "Frontal Lobe Definition...", "insert app>src>main>res>raw>baGraphics>frontallobeA.png", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Recall this brain part.", "insert app>src>main>res>raw>baGraphics>hypothalamusQ.png>>", "Definition...", "insert app>src>main>res>raw>baGraphics>hypothalamusA.png", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Name the part!", "insert app>src>main>res>raw>baGraphics>temporallobeQ.png>>", "Definition...", "insert app>src>main>res>raw>baGraphics>temporallobeA.png", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Name the brain area", "insert app>src>main>res>raw>baGraphics>*Q.png>>", "Definition...", "insert app>src>main>res>raw>baGraphics>*A.png", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Name the brain area", "insert app>src>main>res>raw>baGraphics>*Q.png>>", "Definition...", "insert app>src>main>res>raw>baGraphics>*A.png", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Name the brain area", "insert app>src>main>res>raw>baGraphics>*Q.png>>", "Definition...", "insert app>src>main>res>raw>baGraphics>*A.png", -1);
        deck1.addCardInDeck(testCard);

        ListAdapter theAdapter = new ArrayAdapter<Deck>(this, android.R.layout.simple_list_item_2, (List<Deck>) deck1);

        ListView theListView = (ListView) findViewById(R.id.DecksList);

        theListView.setAdapter(theAdapter);
        */


    }
}
