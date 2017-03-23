package to.mattias.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue
    private int noteId;


    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<NoteObj> noteData;

    private int noteCreator;

    public Note() {
        this.noteData = new ArrayList<>();
    }

    public Note(List<NoteObj> noteData, int noteCreator) {
        this.noteData = noteData;
        this.noteCreator = noteCreator;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public List<NoteObj> getNoteData() {
        return noteData;
    }

    public void setNoteData(List<NoteObj> noteData) {
        this.noteData = noteData;
    }

    public void addNoteData(NoteObj noteObj) {
        this.noteData.add(noteObj);
    }

    public int getNoteCreator() {
        return noteCreator;
    }

    public void setNoteCreator(int noteCreator) {
        this.noteCreator = noteCreator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (noteId != note.noteId) return false;
        if (noteCreator != note.noteCreator) return false;
        return noteData.equals(note.noteData);
    }

    @Override
    public int hashCode() {
        int result = noteId;
        result = 31 * result + noteData.hashCode();
        result = 31 * result + noteCreator;
        return result;
    }
}
