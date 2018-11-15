package project.mobile.mnemosyne;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements FrontCardFragment.OnFragmentInteractionListener, BackCardFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Test data example
        Deck deck1 = new Deck("Brain Anatomy", "Basic brain parts anatomy cramming");
        //Deck deck2 = new Deck("Know your Toons!", "Fun brain teaser to test your cartoon,game,anime character trivia");

        //Add cards for the Brain Anatomy flashcards.
        Card testCard = new Card("Name this brain part", null, "Cerebral Cortex Definition...", null, -1);
        deck1.addCardInDeck(testCard);
        /*
        testCard = new Card("How is this area called?", "insert app>src>main>res>raw>baGraphics>frontallobeQ.png>>", "Frontal Lobe Definition...", "insert app>src>main>res>raw>baGraphics>frontallobeA.png", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Recall this brain part.", "insert app>src>main>res>raw>baGraphics>hypothalamusQ.png>>", "Definition...", "insert app>src>main>res>raw>baGraphics>hypothalamusA.png", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Name the part!", "insert app>src>main>res>raw>baGraphics>temporallobeQ.png>>", "Definition...", "insert app>src>main>res>raw>baGraphics>temporallobeA.png", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Name the brain area", "insert app>src>main>res>raw>baGraphics>*Q.png>>", "Definition...", "insert app>src>main>res>raw>baGraphics>*A.png", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Name the brain area", "insert app>src>main>res>raw>baGraphics>*Q.png>>", "Definition...", "insert app>src>main>res>raw>baGraphics>*A.png", -1);
        deck1.addCardInDeck(testCard);
        testCard = new Card("Name the brain area", "insert app>src>main>res>raw>baGraphics>*Q.png>>", "Definition...", "insert app>src>main>res>raw>baGraphics>*A.png", -1);
        deck1.addCardInDeck(testCard);
        */



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.devs);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tried to go to activity_deck_holder.xml however, it does not recognize the DeckHolder.class
               // Intent go = new Intent(this, DeckHolder.class);
               // startActivity(go);
            }
        });

        //Add front card fragment to layout
        FrontCardFragment frontCardFragment = new FrontCardFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, frontCardFragment);
        transaction.commit();

        //Save User
        Button btnSaveUser = findViewById(R.id.btnSaveUser);
        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtTest = findViewById(R.id.textView);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    User user = new User("Kostas", "1234", format.parse("1980-12-31"));
                    txtTest.setText(user.getHashedPassword());

                    DBAdapter db = new DBAdapter(MainActivity.this);
                    db.open();
                    db.saveUser(user);
                    db.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Take picture
        Button btnTakePicture = findViewById(R.id.btnTakePicture);
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String dir = Environment.getExternalStorageDirectory() + "/Mnemosyne/";
                File newdir = new File(dir);
                String file = dir + 1 + ".jpg";
                File newfile = new File(file);
                try {
                    newfile.createNewFile();
                } catch (IOException e) {
                }

                newdir.mkdirs();

                Uri outputFileUri = Uri.fromFile(newfile);

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, 1001);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Photo taken", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
