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
    private Long noteObjId;
    private NoteType noteType;
    private String noteObjContent;

    public NoteObj() {
    }

    public NoteObj(NoteType noteType, String noteObjContent) {
        this.noteType = noteType;
        this.noteObjContent = noteObjContent;
    }

    public Long getNoteObjId() {
        return noteObjId;
    }

    public void setNoteObjId(Long noteObjId) {
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
}
