package project.mobile.mnemosyne;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
