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

    @ManyToMany
    private List<Notable> noteAssignedTo;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<NoteObj> noteData;

    @OneToOne
    private User noteCreator;

    public Note() {
        this.noteData = new ArrayList<>();
        this.noteAssignedTo = new ArrayList<>();
    }

    public Note(List<Notable> noteAssignedTo, List<NoteObj> noteData, User noteCreator) {
        this.noteAssignedTo = noteAssignedTo;
        this.noteData = noteData;
        this.noteCreator = noteCreator;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public List<Notable> getNoteAssignedTo() {
        return noteAssignedTo;
    }

    public void setNoteAssignedTo(List<Notable> noteAssignedTo) {
        this.noteAssignedTo = noteAssignedTo;
    }

    public void addNoteAssignee(Notable assignee) {
        this.noteAssignedTo.add(assignee);
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

    public User getNoteCreator() {
        return noteCreator;
    }

    public void setNoteCreator(User noteCreator) {
        this.noteCreator = noteCreator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (noteId != note.noteId) return false;
        if (noteAssignedTo != null ? !noteAssignedTo.equals(note.noteAssignedTo) : note.noteAssignedTo != null)
            return false;
        if (noteData != null ? !noteData.equals(note.noteData) : note.noteData != null) return false;
        return noteCreator != null ? noteCreator.equals(note.noteCreator) : note.noteCreator == null;
    }

    @Override
    public int hashCode() {
        int result = noteId;
        result = 31 * result + (noteAssignedTo != null ? noteAssignedTo.hashCode() : 0);
        result = 31 * result + (noteData != null ? noteData.hashCode() : 0);
        result = 31 * result + (noteCreator != null ? noteCreator.hashCode() : 0);
        return result;
    }
}
