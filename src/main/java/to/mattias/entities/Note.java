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
    @OneToMany
    @Cascade(CascadeType.ALL)
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
}
