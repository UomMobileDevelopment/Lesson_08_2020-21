package gr.uom.android.lesson_10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Date;

import gr.uom.android.lesson_10.model.DaoSession;
import gr.uom.android.lesson_10.model.Note;
import gr.uom.android.lesson_10.model.NoteDao;
import gr.uom.android.lesson_10.model.NoteType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoSession daoSession = ((App) getApplication()).getDaoSession();

        NoteDao noteDao = daoSession.getNoteDao();

        Note note = new Note();
        note.setText("Some text");
        note.setComment("A comment");
        note.setDate(new Date());
        note.setType(NoteType.TEXT);
        noteDao.insert(note);
        Log.d("DaoExample", "Inserted new note, ID: " + note.getId());
    }

}
