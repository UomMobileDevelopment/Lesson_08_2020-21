package gr.uom.android.lesson_10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import org.greenrobot.greendao.query.Query;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import gr.uom.android.lesson_10.model.DaoSession;
import gr.uom.android.lesson_10.model.Note;
import gr.uom.android.lesson_10.model.NoteDao;
import gr.uom.android.lesson_10.model.NoteType;
import gr.uom.android.lesson_10.model.Student;
import gr.uom.android.lesson_10.model.StudentDao;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private View addNoteButton;

    private NoteDao noteDao;
    private Query<Note> notesQuery;
    private NotesAdapter notesAdapter;

    final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setUpViews();

        // get the note DAO
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        noteDao = daoSession.getNoteDao();

        StudentDao studentDao = daoSession.getStudentDao();

        Student teoStudent = studentDao.queryBuilder()
                .where(StudentDao.Properties.Name.eq("Theodore"))
                .list().get(0);

        List<Note> noteList = noteDao.queryBuilder()
                .where(NoteDao.Properties.Text.like("%Note"))
                .list();

     //   teoStudent.setNotes(noteList);

        for(Note note : noteList){
            note.setStudentId(teoStudent.getId());
            noteDao.save(note);
        }

        List<Note> teoNotes = teoStudent.getNotes();

        Log.d("TEO", "Teo Notes: ");

       for(Note n : teoNotes){
           Log.d("TEO", n.getText());
       }


        // query all notes, sorted a-z by their text
        notesQuery = noteDao.queryBuilder().orderAsc(NoteDao.Properties.Text).build();
        updateNotes();
    }

    private void updateNotes() {
        List<Note> notes = notesQuery.list();
        notesAdapter.setNotes(notes);
    }

    protected void setUpViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewNotes);
        //noinspection ConstantConditions
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesAdapter = new NotesAdapter(noteClickListener);
        notesAdapter.setLongClickListener(longClickListener);
        recyclerView.setAdapter(notesAdapter);

        addNoteButton = findViewById(R.id.buttonAdd);
        //noinspection ConstantConditions
        addNoteButton.setEnabled(false);

        editText = (EditText) findViewById(R.id.editTextNote);
        editText.setOnEditorActionListener(new OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addNote();
                    return true;
                }
                return false;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean enable = s.length() != 0;
                addNoteButton.setEnabled(enable);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void onAddButtonClick(View view) {
        addNote();
    }

    private void addNote() {
        String noteText = editText.getText().toString();
        editText.setText("");


        String comment = "Added on " + df.format(new Date());

        Note note = new Note();
        note.setText(noteText);
        note.setComment(comment);
        note.setDate(new Date());
        note.setType(NoteType.TEXT);
        noteDao.insert(note);
        Log.d("DaoExample", "Inserted new note, ID: " + note.getId());

        updateNotes();
    }

    NotesAdapter.NoteClickListener noteClickListener = new NotesAdapter.NoteClickListener() {
        @Override
        public void onNoteClick(int position) {
            Note note = notesAdapter.getNote(position);
            Long noteId = note.getId();

            note.setComment("Note last touched at "+ df.format(new Date()));

            Log.d("DaoExample", "Updated note, ID: " + noteId);

            updateNotes();
        }
    };

    NotesAdapter.LongClickListener longClickListener = new NotesAdapter.LongClickListener() {

        @Override
        public boolean onLongClick(int position) {

            Note note = notesAdapter.getNote(position);
            Long noteId = note.getId();
            noteDao.deleteByKey(noteId);
            Log.d("DaoExample", "Deleted note, ID: " + noteId);
            updateNotes();

            Toast.makeText(NoteActivity.this, "Note Deleted!", Toast.LENGTH_SHORT).show();

            return true;
        }

    };

}