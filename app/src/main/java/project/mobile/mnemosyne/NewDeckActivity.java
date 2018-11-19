package project.mobile.mnemosyne;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewDeckActivity extends AppCompatActivity {

    private String title,desc;
    private EditText usersTitle;
    private EditText usersDesc;
    private Button usersSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_deck);

        usersTitle = (EditText) findViewById(R.id.deckTitle);
        usersDesc = (EditText) findViewById(R.id.deckDesc);
        usersSubmit = (Button) findViewById(R.id.deckSubmit);

        usersSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = usersTitle.getText().toString();
                desc = usersDesc.getText().toString();

                showToast(desc);
                addNewCard();
            }
        });



    }

    private void showToast(String text){
        Toast.makeText(NewDeckActivity.this, text, Toast.LENGTH_SHORT).show();

    }

    private void addNewCard(){
        Intent openAdCard = new Intent(this, new_card.class);
        startActivity(openAdCard);
    }

}
