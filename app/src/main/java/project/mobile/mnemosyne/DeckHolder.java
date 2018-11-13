package project.mobile.mnemosyne;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DeckHolder extends AppCompatActivity {

    TextView title1,desc1,title2,desc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_holder);

        title1 = (TextView)findViewById(R.id.deckTitle1);
        title2 = (TextView)findViewById(R.id.deckTitle2);
        desc1 = (TextView)findViewById(R.id.deckDesc1);
        desc1 = (TextView)findViewById(R.id.deckDesc1);

        title1.setText("Title 1");
        desc1.setText("The first description");
        title2.setText("Title 2");
        desc2.setText("The second description");

    }
}
