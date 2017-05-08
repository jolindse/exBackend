package to.mattias;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import to.mattias.constants.NoteType;
import to.mattias.entities.Note;
import to.mattias.entities.NoteObj;
import to.mattias.entities.User;
import to.mattias.repositories.NotableRepository;
import to.mattias.services.NoteObjService;
import to.mattias.services.NoteService;
import to.mattias.services.UserService;

import javax.transaction.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * <h1>Created by Mattias on 2017-02-15.</h1>
 */

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class NoteTest {

    @Autowired
    NoteService noteService;
    @Autowired
    UserService userService;
    @Autowired
    NoteObjService noteObjService;


    private Note defaultNote;
    private int noteId;

    @Before
    public void setup() {
        defaultNote = new Note();
        noteId = noteService.save(defaultNote).getNoteId();
    }

    @Test
    public void testToGetAllNotes() {
        assertTrue("Couldn't retrieve a list of Notes", noteService.findAll().size() > 0);
    }

    @Test
    public void testToGetASpecificNote() {
        assertTrue("Couldn't retrieve a specific note", noteService.findById(noteId).equals(defaultNote));
    }

    @Test
    public void testToAddANoteObject() {
        NoteObj noteObject = new NoteObj();
        noteObject.setNoteType(NoteType.COMMENT);
        noteObject.setNoteObjContent("This is a comment");
        defaultNote.getNoteData().add(noteObject);
        noteService.save(defaultNote);
        List<NoteObj> noteObjects = noteService.findById(noteId).getNoteData();
        assertTrue("NoteObj doesn't contain anything", noteObjects.size() > 0);
        assertTrue("NoteObjectContent doesn't match", noteObjects.get(0).getNoteObjContent() == "This is a comment");
    }

    @Test
    public void testToAddANoteToNotable() {
        // Create a NoteObject
        NoteObj noteObject = new NoteObj();
        noteObject.setNoteType(NoteType.HTTP_URL);
        noteObject.setNoteObjContent("http://www.disney.com");

        // Create a User to assign the Note to
        User user = new User();
        user.setUsername("Mattias");

        User user2 = new User();
        user2.setUsername("Johan");

        defaultNote.addNoteData(noteObject);
        noteService.save(defaultNote);

        user.addNote(defaultNote);

        // Save the user and assert that it contains a note
        User currUser = userService.saveUser(user);
        assertTrue("The User should contain 1 note",
                userService.findById(currUser.getId()).getNotes().size() == 1);
    }

    @Test
    public void testToChangeNoteType() {
        NoteObj noteObject = new NoteObj();
        noteObject.setNoteType(NoteType.COMMENT);

        defaultNote.addNoteData(noteObject);
        noteService.save(defaultNote);

        NoteObj returnedNoteObject = noteService.findById(noteId).getNoteData().get(0);
        returnedNoteObject.setNoteType(NoteType.FILE_URL);
        noteService.save(defaultNote);

        assertTrue("Couldn't change notetype", returnedNoteObject.getNoteType().equals(NoteType.FILE_URL));
    }


    @Test
    public void testToUpdateNoteObject() {
        Note note = new Note();
        NoteObj noteObj = new NoteObj();
        noteObj.setNoteType(NoteType.COMMENT);
        noteObj.setNoteObjContent("This is a comment");
        note.addNoteData(noteObj);

        Note returnedNote = noteService.save(note);

        assertTrue("Notes NoteObj List doesn't contain one object", returnedNote.getNoteData().size() == 1);

        returnedNote.getNoteData().get(0).setNoteType(NoteType.IMAGE_URL);
        returnedNote.getNoteData().get(0).setNoteObjContent("This is an image url");

        assertTrue("Noteobject isn't an image url", noteService.save(returnedNote)
                .getNoteData().get(0).getNoteType() == NoteType.IMAGE_URL);
    }

    @Test
    public void testToDeleteAnEmptyNote() {
        noteService.delete(noteId);
        assertTrue("Note still persisted", noteService.findAll().size() == 0);
    }


    @Test
    public void testToDeleteANoteWithNoteData() {
        NoteObj noteObj = new NoteObj();
        noteObj.setNoteType(NoteType.COMMENT);

        defaultNote.addNoteData(noteObj);

        noteService.save(defaultNote);
        assertTrue("Persisted note doesn't contain data", noteService.findById(noteId)
            .getNoteData().size() > 0);

        // Delete the note and assert that the NoteObject is deleted as well
        noteService.delete(noteId);
        assertFalse("Note Object is still persisted", noteObjService.findAll().contains(noteObj));
    }


    @Test
    public void testToDeleteNoteObjAssignedToNote() {
        NoteObj noteObj = new NoteObj();
        defaultNote.addNoteData(noteObj);

        noteService.save(defaultNote);

        int noteObjId = noteService.findById(noteId).getNoteData().get(0).getNoteObjId();

        // Delete the noteObj and assert that the note still exists
        noteObjService.delete(noteObjId);
        assertTrue("The note is also deleted", noteService.findById(noteId).equals(defaultNote));
    }




}
