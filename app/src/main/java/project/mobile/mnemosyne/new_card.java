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

public class new_card extends AppCompatActivity {

    private EditText usersSideA; // The text of Side A (question)
    private Button usersmediaQ; // The capture button for media.
    private ImageView usersIVQ; // Where media input will be stored.
    private EditText usersSideB;
    private ImageView usersIVA;
    private Button usersmediaA;
    private Button usersSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        Button usersSubmit = (Button) findViewById(R.id.submitC);
        EditText usersSideA = (EditText) findViewById(R.id.question);
        Button usersmediaQ = (Button) findViewById(R.id.photoQ);
        ImageView usersIVQ = (ImageView) findViewById(R.id.mediaQ);
        EditText usersSideB = (EditText) findViewById(R.id.answer);
        Button usersmediaA = (Button) findViewById(R.id.photoA);
        ImageView usersIVA = (ImageView) findViewById(R.id.mediaA);

        usersmediaQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cptrMediaA = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cptrMediaA, 0);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        usersIVQ.setImageBitmap(bitmap);
    }
}
