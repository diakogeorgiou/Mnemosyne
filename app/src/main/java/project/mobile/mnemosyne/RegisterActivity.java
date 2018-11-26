package project.mobile.mnemosyne;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private EditText newName;
    private EditText newPassword;
    private TextView Info;
    private Button Register;
    private Button Login;
    public static final String MY_PREFS_NAME = "UsersFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);






        newName = (EditText) findViewById(R.id.addUsername);
        newPassword = (EditText) findViewById(R.id.addPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Register = (Button) findViewById(R.id.btnReg);
        Login = (Button) findViewById(R.id.btnLogin);
        //Info.setText("Attempts remaining: 3");

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(newName.getText().toString(), newPassword.getText().toString());
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void saveUser(String newName, String newPassword) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        if (TextUtils.isEmpty(newName)) {
            Info.setText("user name cannot be empty");



        } else if (TextUtils.isEmpty(newPassword)) {


            Info.setText("password cannot be empty");

        } else {
            /*
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("username", String.valueOf(newName));
            editor.putString("password", String.valueOf(newPassword));
            editor.apply();

            */
            editor.putString("username", newName);
            editor.putString("password", newPassword);
            editor.commit();
            SystemClock.sleep(1000);
            Intent intent = new Intent(RegisterActivity.this, DeckHolder.class);
            startActivity(intent);

        }

    }
}
