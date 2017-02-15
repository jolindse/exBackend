package to.mattias;

import org.junit.Before;
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
import to.mattias.services.NoteService;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * <h1>Created by Mattias on 2017-02-15.</h1>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class NoteTest {

    @Autowired
    NoteService service;

    private Note defaultNote;
    private int noteId;

    @Before
    public void setup() {
        defaultNote = new Note();
        noteId = service.save(defaultNote).getNoteId();
    }

    @Test
    public void testToGetAllNotes() {
        assertTrue("Couldn't retrieve a list of Notes", service.findAll().size() > 0);
    }

    @Test
    public void testToGetASpecificNote() {
        assertTrue("Couldn't retrieve a specific note", service.findById(noteId).equals(defaultNote));
    }

    @Test
    public void testToAddANoteObject() {
        NoteObj noteObject = new NoteObj();
        noteObject.setNoteType(NoteType.COMMENT);
        noteObject.setNoteObjContent("This is a comment");
        defaultNote.getNoteData().add(noteObject);
        service.save(defaultNote);
        List<NoteObj> noteObjects = service.findById(noteId).getNoteData();
        assertTrue("NoteObj doesn't contain anything", noteObjects.size() > 0);
        assertTrue("NoteObjectContent doesn't match", noteObjects.get(0).getNoteObjContent() == "This is a comment");
    }

    @Test
    public void testToAddANotableToANote() {
        // CReate a NoteObject
        NoteObj noteObject = new NoteObj();
        noteObject.setNoteType(NoteType.HTTP_URL);
        noteObject.setNoteObjContent("http://www.disney.com");

        // Create a User to assign the Note to
        User user = new User();
        user.setUserName("Mattias");

        User user2 = new User();
        user2.setUserName("Johan");

        defaultNote.addNoteData(noteObject);
        defaultNote.addNoteAssignee(user);
        defaultNote.addNoteAssignee(user2);

        // Save the note and assert that it contains 2 assigned users
        service.save(defaultNote);
        assertTrue("The Note should contain 2 assignees", service.findById(noteId).getNoteAssignedTo().size() == 2);
    }

    @Test
    public void testToChangeNoteType() {
        NoteObj noteObject = new NoteObj();
        noteObject.setNoteType(NoteType.COMMENT);

        defaultNote.addNoteData(noteObject);
        service.save(defaultNote);

        NoteObj returnedNoteObject = service.findById(noteId).getNoteData().get(0);
        returnedNoteObject.setNoteType(NoteType.FILE_URL);
        service.save(defaultNote);

        assertTrue("Couldn't change notetype", returnedNoteObject.getNoteType().equals(NoteType.FILE_URL));
    }

    @Test
    public void testToRetrieveAllNotesAssignedToOneNotable() {
        User user = new User();
        user.setUserName("Mattias");

        defaultNote.addNoteData(new NoteObj(NoteType.FILE_URL, "File URL"));
        defaultNote.addNoteAssignee(user);
        service.save(defaultNote);

        Note secondNote = new Note();
        secondNote.addNoteData(new NoteObj(NoteType.IMAGE_URL, "Image Url"));
        secondNote.addNoteAssignee(user);
        service.save(secondNote);

        Note thirdNote = new Note();
        service.save(thirdNote);

        List<Note> notes = service.findByNotable(user);

        assertTrue("The list doesn't contain 2 notes", notes.size() == 2);
        
        
    }

}
