package to.mattias.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import to.mattias.constants.NoteType;
import to.mattias.entities.*;
import to.mattias.repositories.*;

import java.io.IOException;
import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@Service
public class NoteService {

    private Logger logger = LoggerFactory.getLogger("kanban-logger");

    @Autowired
    private NoteRepository noteRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private SprintRepository sprintRepo;
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private FileService fileService;

    private Notable notable;
    private User noteCreator;

    public List<Note> findAll() {
        return noteRepo.findAll();
    }

    public Note save(Note note) {
        return noteRepo.save(note);
    }

    public Note findById(int id) {
        return noteRepo.findOne(id);
    }

    public Note update(Note note) {
        return save(note);
    }

    public void delete(int noteId) {
        noteRepo.delete(noteId);
    }

    public List<Note> findByNotable(Notable notable) {
        return noteRepo.findByNoteAssignedToIn(notable);
    }

    public Note newNoteWithFile(int projectId, int notableId,
                                MultipartFile file, int creatorId) {

        getNotable(notableId);
        getCreator(creatorId);

        Note noteToCreate = new Note();
        noteToCreate.addNoteAssignee(notable);
        noteToCreate.setNoteCreator(noteCreator);

        try {
            NoteObj noteObj = fileService.store(projectId, file);
            noteToCreate.addNoteData(noteObj);
            return noteRepo.save(noteToCreate);
        } catch (IOException e) {
            logger.error(String.format("File upload error - %s", e.getMessage()));
        }
        return null;
    }

    public Note newNoteWithoutFile(int notableId, NoteType noteType,
                                   String content, int creatorId) {

        getNotable(notableId);
        getCreator(creatorId);

        Note noteToCreate = new Note();
        NoteObj noteObj = new NoteObj();

        noteObj.setNoteType(noteType);
        noteObj.setNoteObjContent(content);
        noteToCreate.addNoteAssignee(notable);
        noteToCreate.setNoteCreator(noteCreator);

        return noteRepo.save(noteToCreate);
    }

    /**
     * Gets the Notable that the note belongs to
     *
     * @param notableId id of the entity
     * @return Notable
     */
    private void getNotable(int notableId) {

        JpaRepository[] repos = {customerRepo, projectRepo, sprintRepo, taskRepo, userRepo};

        for (int i = 0; i < repos.length; i++) {
            notable = (Notable) repos[i].findOne(notableId);
            if(notable != null) {
                break;
            }
        }
    }

    /**
     * Gets the User that created the Note
     *
     * @param creatorId User id
     * @return User
     */
    private void getCreator(int creatorId) {
        noteCreator = userRepo.findOne(creatorId);
    }
}
