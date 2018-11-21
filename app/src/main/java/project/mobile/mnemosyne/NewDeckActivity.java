package project.mobile.mnemosyne;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewDeckActivity extends AppCompatActivity {

    private String title, desc;
    private EditText usersTitle;
    private EditText usersDesc;
    private Button btnAddCards;
    private Button btnSaveDeck;
    private Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_deck);

        usersTitle = (EditText) findViewById(R.id.deckTitle);
        usersDesc = (EditText) findViewById(R.id.deckDesc);
        btnAddCards = (Button) findViewById(R.id.btnAddCards);
        btnSaveDeck = (Button) findViewById(R.id.btnSaveDeck);

        //Add cards
        btnAddCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = usersTitle.getText().toString();
                desc = usersDesc.getText().toString();

                Intent openAdCard = new Intent(NewDeckActivity.this, new_card.class);
                Deck deck = new Deck(title, desc);
                openAdCard.putExtra("deck", deck);
                startActivityForResult(openAdCard, 1001);
            }
        });

        //Save deck
        btnSaveDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save deck to Data
                if (TextUtils.isEmpty(usersTitle.getText()) || TextUtils.isEmpty(usersDesc.getText())) {
                    Toast.makeText(NewDeckActivity.this, "Title and description are empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (deck == null) {
                    Toast.makeText(NewDeckActivity.this, "Deck is empty.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (deck.getCards() == null) {
                    Toast.makeText(NewDeckActivity.this, "Deck is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Data.getInstance().addDeck(deck);
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(NewDeckActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            //Get deck from activity
            deck = (Deck) data.getSerializableExtra("deck");
        }
    }
}
