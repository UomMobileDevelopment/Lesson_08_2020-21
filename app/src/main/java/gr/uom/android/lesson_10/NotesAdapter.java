package gr.uom.android.lesson_10;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gr.uom.android.lesson_10.model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private NoteClickListener clickListener;
    private LongClickListener longClickListener;
    private List<Note> dataset;

    public interface NoteClickListener {
        void onNoteClick(int position);
    }

    public interface LongClickListener {
        boolean onLongClick(int position);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public TextView comment;

        public NoteViewHolder(View itemView, final NoteClickListener clickListener, final LongClickListener longClickListener) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textViewNoteText);
            comment = (TextView) itemView.findViewById(R.id.textViewNoteComment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(longClickListener != null){
                        return longClickListener.onLongClick(getAdapterPosition());
                    }
                    return false;
                }
            });
        }
    }

    public NotesAdapter(NoteClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<Note>();
    }

    public void setNotes(@NonNull List<Note> notes) {
        dataset = notes;
        notifyDataSetChanged();
    }

    public Note getNote(int position) {
        return dataset.get(position);
    }

    @Override
    public NotesAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view, clickListener, longClickListener);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.NoteViewHolder holder, int position) {
        Note note = dataset.get(position);
        holder.text.setText(note.getText());
        holder.comment.setText(note.getComment());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setLongClickListener(LongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setClickListener(NoteClickListener clickListener) {
        this.clickListener = clickListener;
    }
}