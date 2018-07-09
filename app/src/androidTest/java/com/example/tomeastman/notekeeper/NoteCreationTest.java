package com.example.tomeastman.notekeeper;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;



@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {
    @Rule
    public ActivityTestRule<NoteListActivity> mNoteListActivityRule =
            new ActivityTestRule<>(NoteListActivity.class);

    @Test
    public void createNewNote() {
        // get to the view
//        ViewInteraction fabNewNote = onView(withId(R.id.fab));
        // perform an action
//        fabNewNote.perform(click());

        // or all in one line, click the fab button
        onView(withId(R.id.fab)).perform(click());

        // enter in the note title and then the note text and close the soft keyboard
        onView(withId(R.id.text_note_title)).perform(typeText("Test note title"));
        onView(withId(R.id.text_note_text)).perform(typeText("This is the body of our test note"),
                closeSoftKeyboard());
    }


}