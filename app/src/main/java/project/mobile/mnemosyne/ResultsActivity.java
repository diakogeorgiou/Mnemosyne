package project.mobile.mnemosyne;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Deck deck = (Deck) getIntent().getSerializableExtra("deck");

        //Calculate and show results
        //Title
        TextView txtDeckTitle = findViewById(R.id.txtDeckTitle);
        txtDeckTitle.setText(deck.getTitle());

        //Result
        int result = deck.getAmountOfKnowCards() * 100 / deck.getTotalNumberOfCards();
        TextView txtResult = findViewById(R.id.txtResult);
        txtResult.setText(String.valueOf(Math.round(result)) + '%');
    }
}