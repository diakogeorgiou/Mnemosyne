package project.mobile.mnemosyne;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements FrontCardFragment.OnFragmentInteractionListener, BackCardFragment.OnFragmentInteractionListener {
    //Current deck holder
    private Deck currentDeck;
    private TextView txtKnowNo;
    private TextView txtDontKnowNo;
    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtKnowNo = findViewById(R.id.txtKnowNo);
        txtDontKnowNo = findViewById(R.id.txtDontKnowNo);
        btnFinish = findViewById(R.id.btnSaveDeck);

        //Get current deck
        currentDeck = (Deck) getIntent().getExtras().getSerializable("deck");

        //Add front card fragment to layout
        getIntent().putExtra("deck", currentDeck);

        FrontCardFragment frontCardFragment = new FrontCardFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, frontCardFragment);
        transaction.commit();

        //Display Known and Unknown number
        displayNumberOfKnownAndUnknownCards();

        //Known button clicked
        Button btnKnow = findViewById(R.id.btnKnow);
        btnKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set card to known
                currentDeck.getCurrentCard().setKnown(true);

                //Display Known and Unknown number
                displayNumberOfKnownAndUnknownCards();
            }
        });

        //Don't Known button clicked
        Button btnDontKnow = findViewById(R.id.btnDontKnow);
        btnDontKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set card to unknown
                currentDeck.getCurrentCard().setKnown(false);

                //Display Known and Unknown number
                displayNumberOfKnownAndUnknownCards();
            }
        });

        //Finish button clicked
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Close current activity
                finish();

                //Show results
                Intent resultsActivity = new Intent(MainActivity.this, ResultsActivity.class);
                resultsActivity.putExtra("deck", currentDeck);
                startActivity(resultsActivity);
            }
        });
    }

    private void displayNumberOfKnownAndUnknownCards() {
        txtKnowNo.setText(String.valueOf(currentDeck.getAmountOfKnowCards()));
        txtDontKnowNo.setText(String.valueOf(currentDeck.getAmountOfDontKnowCards()));

        //Check if all questions are rated and show finish button
        if (currentDeck.getAmountOfKnowCards() + currentDeck.getAmountOfDontKnowCards() == currentDeck.getTotalNumberOfCards()) {
            btnFinish.setVisibility(View.VISIBLE);
        }
    }

//        //Save User
//        Button btnSaveUser = findViewById(R.id.btnSaveUser);
//        btnSaveUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView txtTest = findViewById(R.id.textView);
//
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    User user = new User("Kostas", "1234", format.parse("1980-12-31"));
//                    txtTest.setText(user.getHashedPassword());
//
//                    DBAdapter db = new DBAdapter(MainActivity.this);
//                    db.open();
//                    db.saveUser(user);
//                    db.close();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

//        //Take picture
//        Button btnTakePicture = findViewById(R.id.btnTakePicture);
//        btnTakePicture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String dir = Environment.getExternalStorageDirectory() + "/Mnemosyne/";
//                File newdir = new File(dir);
//                String file = dir + 2 + ".jpg";
//                File newfile = new File(file);
//                try {
//                    newfile.createNewFile();
//                } catch (IOException e) {
//                }
//
//                newdir.mkdirs();
//
//                Uri outputFileUri = Uri.fromFile(newfile);
//
//                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//                StrictMode.setVmPolicy(builder.build());
//
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
//
//                startActivityForResult(cameraIntent, 1001);
//            }
//        });

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1001 && resultCode == RESULT_OK) {
//            Toast.makeText(this, "Photo taken", Toast.LENGTH_SHORT).show();
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
