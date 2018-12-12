package gr.uom.android.lesson_10.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

@Entity(indexes = {
    @Index(value = "text, date DESC", unique = true)
})
public class Note {

    @Id
    private Long id;

    @NotNull
    private String text;
    private String comment;
    private Date date;

    private long studentId;

    @Convert(converter = NoteTypeConverter.class, columnType = String.class)
    private NoteType type;

    @Generated(hash = 136628276)
    public Note(Long id, @NotNull String text, String comment, Date date,
            long studentId, NoteType type) {
        this.id = id;
        this.text = text;
        this.comment = comment;
        this.date = date;
        this.studentId = studentId;
        this.type = type;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public NoteType getType() {
        return type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }

    public long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}