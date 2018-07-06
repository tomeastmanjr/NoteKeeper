package com.example.tomeastman.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    // create a constants value
    public static final String NOTE_INFO = "com.example.tomeastman.notekeeper.NOTE_INFO";

    private NoteInfo mNote;
    private boolean mIsNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a reference to our spinner
        Spinner spinnerCourses = (Spinner) findViewById(R.id.spinner_courses);

        // Create list
        List<CourseInfo> courses = DataManager.getInstance().getCourses();

        // Create adapter
        ArrayAdapter<CourseInfo> adapterCourses = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);

        // Associate resource we want to use for the dropdown
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // associate adapter with the spinner
        spinnerCourses.setAdapter(adapterCourses);

        readDisplayStateValues();

        // get view references
        EditText textNoteTitle = findViewById(R.id.text_note_title);
        EditText textNoteText = findViewById(R.id.text_note_text);

        // display the note
        if (!mIsNewNote) {
            displayNote(spinnerCourses, textNoteTitle, textNoteText);
        }

    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText) {

        // get the list of courses from the data manager
        List<CourseInfo> courses = DataManager.getInstance().getCourses();

        // get the index of the course from within the list
        int courseIndex = courses.indexOf(mNote.getCourse());

        // set the spinner
        spinnerCourses.setSelection(courseIndex);

        // set the text views
        textNoteTitle.setText(mNote.getTitle());
        textNoteText.setText(mNote.getText());
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        mNote = intent.getParcelableExtra(NOTE_INFO);
        mIsNewNote = mNote == null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
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
}
