package to.mattias.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue
    private Long noteId;
    private List<Notable> noteAssignedTo;
    private List<NoteObj> noteData;
    private User noteCreator;

    public Note() {
    }

    public Note(List<Notable> noteAssignedTo, List<NoteObj> noteData, User noteCreator) {
        this.noteAssignedTo = noteAssignedTo;
        this.noteData = noteData;
        this.noteCreator = noteCreator;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public List<Notable> getNoteAssignedTo() {
        return noteAssignedTo;
    }

    public void setNoteAssignedTo(List<Notable> noteAssignedTo) {
        this.noteAssignedTo = noteAssignedTo;
    }

    public List<NoteObj> getNoteData() {
        return noteData;
    }

    public void setNoteData(List<NoteObj> noteData) {
        this.noteData = noteData;
    }

    public User getNoteCreator() {
        return noteCreator;
    }

    public void setNoteCreator(User noteCreator) {
        this.noteCreator = noteCreator;
    }
}
