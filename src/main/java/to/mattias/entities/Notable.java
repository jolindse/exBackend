package to.mattias.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Notable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Note> notes;

    public Notable() {
        notes = new ArrayList<>();
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
