package project.mobile.mnemosyne;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class new_card extends AppCompatActivity {

    public Card push;
    private EditText usersSideA; // The text of Side A (question)
    private Button usersmediaQ; // The capture button for media.
    private ImageView usersIVQ; // Where media input will be stored.
    private EditText usersSideB;
    private ImageView usersIVA;
    private Button usersmediaA;
    private Button usersSubmit;
    public String answer,question;
    public Bitmap photoA, photoB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        usersSubmit = (Button) findViewById(R.id.submitC);
        usersSideA = (EditText) findViewById(R.id.question);
        usersmediaQ = (Button) findViewById(R.id.photoQ);
        usersIVQ = (ImageView) findViewById(R.id.mediaQ);
        usersSideB = (EditText) findViewById(R.id.answer);
        usersmediaA = (Button) findViewById(R.id.photoA);
        usersIVA = (ImageView) findViewById(R.id.mediaA);

        usersmediaQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cptrMediaQ = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cptrMediaQ, 0); //Request code serves as flag to distinguish the activity.
            }
        });

        usersmediaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cptrMediaA = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cptrMediaA, 1);
            }
        });

        usersSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question = usersSideA.getText().toString();
                answer = usersSideB.getText().toString();

                push = new Card(question, null, answer, null);

            }

    });

    }

    //Converting camera pictures to data and pushing to imageview fields.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            if (requestCode==0) { //Checking the flag "which button was pressed?".
                usersIVQ.setImageBitmap(bitmap);
                photoA = (Bitmap)data.getExtras().get("data");
            }else{
                usersIVA.setImageBitmap(bitmap);
                photoB = (Bitmap)data.getExtras().get("data");
            }
    }

    private void showToast(String text){
        Toast.makeText(new_card.this, text, Toast.LENGTH_SHORT).show();

    }

}
