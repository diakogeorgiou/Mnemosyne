package project.mobile.mnemosyne;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

public class DeckHolder extends AppCompatActivity {
    ListView decksListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_holder);

        decksListView = findViewById(R.id.decksList);
        DeckListAdapter deckListAdapter = new DeckListAdapter(DeckHolder.this, Data.getInstance().getDecks());
        decksListView.setAdapter(deckListAdapter);

        decksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mainTest = new Intent(DeckHolder.this, MainActivity.class);

                //Pass current deck to activity
                Bundle bundle = new Bundle();
                bundle.putSerializable("deck", Data.getInstance().getDecks().get(position));
                mainTest.putExtras(bundle);
                startActivity(mainTest);
            }
        });

        //Enable context menu on long press
        registerForContextMenu(decksListView);

        //Add new deck activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newDeck = new Intent(DeckHolder.this, NewDeckActivity.class);
                startActivityForResult(newDeck, 1002);
            }
        });
    }

    //Create the context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = DeckHolder.this.getMenuInflater();
        inflater.inflate(R.menu.deckholder_context_menu, menu);
    }

    //Context menu selection
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();

        switch (item.getItemId()) {
            case R.id.mn_delete:
                //Delete Deck
                //Show alert dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Confirmation")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setMessage("Are you sure you want to delete this deck ?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Delete deck
                                Data.getInstance().deleteDeck(Data.getInstance().getDecks().get(info.position));
                                //Refresh list
                                DeckListAdapter deckListAdapter = new DeckListAdapter(DeckHolder.this, Data.getInstance().getDecks());
                                decksListView.setAdapter(deckListAdapter);

                                Toast.makeText(DeckHolder.this, "Deck successfully deleted", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1002 && resultCode == RESULT_OK) {
            //Refresh list
            DeckListAdapter deckListAdapter = new DeckListAdapter(DeckHolder.this, Data.getInstance().getDecks());
            decksListView.setAdapter(deckListAdapter);
        }
    }
}
