package to.mattias.entities;

import javax.persistence.*;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Notable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
