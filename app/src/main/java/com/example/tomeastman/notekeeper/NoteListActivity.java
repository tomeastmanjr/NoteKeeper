package com.example.tomeastman.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> mAdapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // since there is nothing extra to pass, just create the intent directly in startActivity
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));



            }
        });

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapterNotes.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        // get a reference to the list view
        final ListView listNotes = findViewById(R.id.list_notes);

        // get the content to put inside the listView
        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        // create an adapter to put in the listView
        mAdapterNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        // set the adapter
        listNotes.setAdapter(mAdapterNotes);

        // handle clicks
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // show our note activity
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);

                // get reference to the noteInfo
//                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(i);

                // add extra info to pass the note
                intent.putExtra(NoteActivity.NOTE_POSITION, i);

                startActivity(intent);
            }
        });
    }

}
