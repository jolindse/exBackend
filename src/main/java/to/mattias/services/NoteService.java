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

//    private Logger logger = LoggerFactory.getLogger("kanban-logger");

    @Autowired
    private NoteRepository noteRepo;
    @Autowired
    private FileService fileService;
    @Autowired
    private NotableRepository notableRepo;

    private Notable notable;

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


    /**
     * Persists a note with attached file
     *
     * @param notableId - The entity that is noted
     * @param file - Uploaded file
     * @param creatorId - Id of the user that made the note
     * @return The newly persisted Note-object
     */
    public Note newNoteWithFile(int notableId,
                                MultipartFile file, int creatorId) {

        getNotable(notableId);

        Note noteToCreate = new Note();
        noteToCreate.setNoteCreator(creatorId);

        try {
            NoteObj noteObj = fileService.store(notableId, file);
            noteToCreate.addNoteData(noteObj);
            return saveNote(noteToCreate, notable);
        } catch (IOException e) {
//            logger.error(String.format("File upload error - %s", e.getMessage()));
        }
        return null;
    }

    /**
     * Persists a note without an attached file
     *
     * @param notableId - The entity that is noted
     * @param noteType - Type of note
     * @param content - String that represents the note content
     * @param creatorId - Id of the user that made the note
     * @return The newly persisted Note-object
     */
    public Note newNoteWithoutFile(int notableId, NoteType noteType,
                                   String content, int creatorId) {

        getNotable(notableId);

        Note noteToCreate = new Note();
        NoteObj noteObj = new NoteObj();

        noteObj.setNoteType(noteType);
        noteObj.setNoteObjContent(content);
        noteToCreate.setNoteCreator(creatorId);
        noteToCreate.addNoteData(noteObj);

        return saveNote(noteToCreate, notable);
    }

    /**
     * Gets the Notable that the note belongs to and determines which repository to use
     * when updating the notable
     *
     * @param notableId id of the entity
     *
     */
    private void getNotable(int notableId) {
        notable = notableRepo.findOne(notableId);
    }

    private Note saveNote(Note noteToSave, Notable notable) {
        Note currNote = noteRepo.save(noteToSave);
        notable.addNote(currNote);
        notableRepo.save(notable);
        return currNote;
    }
}
