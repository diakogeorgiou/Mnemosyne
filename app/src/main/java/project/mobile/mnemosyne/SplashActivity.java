package project.mobile.mnemosyne;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent intent = new Intent(this, DeckHolder.class);
        Intent intent = new Intent(this, LoginActivity.class);
        SystemClock.sleep(3000); //Added a few extra seconds to "sell" the logo (G).
        startActivity(intent);
        finish();
    }
}
