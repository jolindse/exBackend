package to.mattias.entities;

import to.mattias.constants.NoteType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Entity
@Table(name = "noteObjects")
public class NoteObj {

    @Id
    @GeneratedValue
    private int noteObjId;
    private NoteType noteType;
    private String noteObjContent;

    public NoteObj() {
    }

    public NoteObj(NoteType noteType, String noteObjContent) {
        this.noteType = noteType;
        this.noteObjContent = noteObjContent;
    }

    public int getNoteObjId() {
        return noteObjId;
    }

    public void setNoteObjId(int noteObjId) {
        this.noteObjId = noteObjId;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }

    public String getNoteObjContent() {
        return noteObjContent;
    }

    public void setNoteObjContent(String noteObjContent) {
        this.noteObjContent = noteObjContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteObj noteObj = (NoteObj) o;

        if (noteObjId != noteObj.noteObjId) return false;
        if (noteType != noteObj.noteType) return false;
        return noteObjContent != null ? noteObjContent.equals(noteObj.noteObjContent) : noteObj.noteObjContent == null;
    }

    @Override
    public int hashCode() {
        int result = noteObjId;
        result = 31 * result + (noteType != null ? noteType.hashCode() : 0);
        result = 31 * result + (noteObjContent != null ? noteObjContent.hashCode() : 0);
        return result;
    }
}
