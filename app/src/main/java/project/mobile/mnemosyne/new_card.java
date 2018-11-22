package project.mobile.mnemosyne;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class new_card extends AppCompatActivity {
    Deck deck;
    public Card push;
    private EditText txtQuestion; // The text of Side A (question)
    private Button usersmediaQ; // The capture button for media.
    private ImageView questionImage; // Where media input will be stored.
    private EditText txtAnswer;
    private ImageView answerImage;
    private Button usersmediaA;
    private Button btnFinish;
    private Button btnSaveCard;
    //    public String answer, question;
    public Bitmap photoA, photoB;

    private String strQuestionImageName, strAnswerImageName;
    private Uri outputFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        //Get deck from activity
        deck = (Deck) getIntent().getSerializableExtra("deck");

        btnFinish = (Button) findViewById(R.id.btnSaveDeck);
        btnSaveCard = findViewById(R.id.btnSaveCard);
        txtQuestion = (EditText) findViewById(R.id.question);
        usersmediaQ = (Button) findViewById(R.id.photoQ);
        questionImage = (ImageView) findViewById(R.id.mediaQ);
        txtAnswer = (EditText) findViewById(R.id.answer);
        usersmediaA = (Button) findViewById(R.id.photoA);
        answerImage = (ImageView) findViewById(R.id.mediaA);

        //Take question picture
        usersmediaQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareToSavePicture();

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, 0);
            }
        });

        //Take answer picture
        usersmediaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareToSavePicture();

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, 1);
            }
        });

        //Select question image from gallery
        questionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 10);
            }
        });

        //Select answer image from gallery
        answerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 11);
            }
        });

        //Finish button
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Send deck object to NewDeckActivity
                if (deck.getCards() == null) {
                    Toast.makeText(new_card.this, "Deck does not contain cards.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("deck", deck);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

        });

        //Save card
        btnSaveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtQuestion.getText()) || TextUtils.isEmpty(txtAnswer.getText())) {
                    Toast.makeText(new_card.this, "Please enter question and answer fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Card card = new Card(txtQuestion.getText().toString(), strQuestionImageName, txtAnswer.getText().toString(), strAnswerImageName);
                //Indicates an external image resource
                card.setExternalBitmaps(true);
                deck.addCardInDeck(card);

                //Clear fields
                txtQuestion.setText("");
                txtAnswer.setText("");
                strQuestionImageName = null;
                strAnswerImageName = null;
                questionImage.setImageResource(android.R.color.transparent);
                answerImage.setImageResource(android.R.color.transparent);
                Toast.makeText(new_card.this, "Card added.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Converting camera pictures to data and pushing to imageview fields.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Question camera
        if (requestCode == 0 && resultCode == RESULT_OK) { //Checking the flag "which button was pressed?".
//            strQuestionImageName = getFileNameFromUri(outputFileUri);
            strQuestionImageName = outputFileUri.getPath();
            File imgFile = new File(outputFileUri.getPath());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                questionImage.setImageBitmap(myBitmap);
            }
            //Answer camera
        } else if (requestCode == 1 && resultCode == RESULT_OK) {
            strAnswerImageName = outputFileUri.getPath();
            File imgFile = new File(outputFileUri.getPath());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                answerImage.setImageBitmap(myBitmap);
            }
        }

        //Question image from gallery
        if (requestCode == 10 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            strQuestionImageName = picturePath;
            questionImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }

        if (requestCode == 11 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            strAnswerImageName = picturePath;
            answerImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    private void prepareToSavePicture() {
        final String dir = Environment.getExternalStorageDirectory() + "/Mnemosyne/";
        File newdir = new File(dir);

        //Generate a random file name
        String uuid = UUID.randomUUID().toString();

        String file = dir + uuid + ".jpg";
        File newfile = new File(file);
        try {
            newfile.createNewFile();
        } catch (IOException e) {
        }

        newdir.mkdirs();

        outputFileUri = Uri.fromFile(newfile);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

//    public String getFileNameFromUri(Uri uri) {
//        String result = null;
//        if (uri.getScheme().equals("content")) {
//            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//            try {
//                if (cursor != null && cursor.moveToFirst()) {
//                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
//                }
//            } finally {
//                cursor.close();
//            }
//        }
//        if (result == null) {
//            result = uri.getPath();
//            int cut = result.lastIndexOf('/');
//            if (cut != -1) {
//                result = result.substring(cut + 1);
//            }
//        }
//
//        String strFinal = result.replace(".jpg", "");
//        return strFinal;
//    }
}
