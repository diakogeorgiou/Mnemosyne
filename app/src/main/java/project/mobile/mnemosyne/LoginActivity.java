package project.mobile.mnemosyne;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button Register;
    public static final String MY_PREFS_NAME = "UsersFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText) findViewById(R.id.editName);
        Password = (EditText) findViewById(R.id.editPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        Register = (Button) findViewById(R.id.btnAddUser);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private void validate(String userName, String userPassword) {

        //SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = prefs.edit();

        String user = prefs.getString("username", "No username defined");
        String pass = prefs.getString("password", "No password defined");


        if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(userPassword)) {
            Info.setText("no user exists");


        } else if (user.equals(userName) && pass.equals(userPassword)) {

            finish();
            Intent intent = new Intent(LoginActivity.this, DeckHolder.class);
            startActivity(intent);

        } else {
            Info.setText("incorrect credentials");


        }


    }


}
