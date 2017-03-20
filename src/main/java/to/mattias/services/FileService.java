package to.mattias.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import to.mattias.constants.NoteType;
import to.mattias.entities.NoteObj;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * <h1>Created by Mattias on 2017-02-18.</h1>
 */
@Service
public class FileService {

    private Logger logger = LoggerFactory.getLogger("kanban-logger");

    private NoteType type;

    private final NoteObjService noteObjService;

    private final String uploadBaseDir;
    private final String uploadImageDir;
    private final String uploadFileDir;

    @Autowired
    public FileService(NoteObjService service,
                       @Value("${upload.base.dir}") String baseDir,
                       @Value("${upload.image.dir}") String imageDir,
                       @Value("${upload.file.dir}") String fileDir) {
        this.noteObjService = service;
        this.uploadBaseDir = baseDir;
        this.uploadImageDir = imageDir;
        this.uploadFileDir = fileDir;
    }

    /**
     * Stores an uploaded file to disk and creates a new
     * NoteObject and persists it in the db.
     *
     * @param projectId An id to identify the project to which the file belongs
     * @param file The uploaded file
     * @return A new NoteObject
     */
    public NoteObj store(int projectId, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        // Set a unique filename for the file to be stored
        String filename = String.valueOf(System.currentTimeMillis());

        // Get the file-type
        String fileSuffix = originalFilename.substring(
                originalFilename.lastIndexOf("."), originalFilename.length());
        // Sets the path where to store the file
        String path = String.format("%s%s/%s/%s%s", uploadBaseDir, projectId, getFileType(fileSuffix), filename, fileSuffix);

        File fileToStore = new File(path);

        // If the file contains data, write it to disk
        if(!file.isEmpty()) {
            try {
                file.transferTo(fileToStore);
                NoteObj noteObj = new NoteObj();
                noteObj.setNoteType(type);

                // Make a new NoteObj and persist it
                NoteObj currNoteObj = noteObjService.save(noteObj);
                currNoteObj.setNoteObjContent(
                        String.format("/assets/%d/%s%s%s", projectId, getFileType(fileSuffix), filename, fileSuffix));

                return noteObjService.save(currNoteObj);

            } catch (IOException e) {
                logger.error(String.format("Failed to store file on disk: %s", fileToStore));
                // TODO: Remove printStackTrace
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * Checks the ending of the file and sets the NoteType
     * of the NoteObj. The method also sets the path to the asset
     * depending of whether it is an image or not
     *
     * @param fileSuffix A String containing the file ending.
     *                   Be sure to include the dot (.)
     * @return The subpath to the asset (/image or /file)
     */
    private String getFileType(String fileSuffix) {
        if (fileSuffix.equals(".jpg") || fileSuffix.equals(".gif") ||
                fileSuffix.equals(".png") || fileSuffix.equals(".JPG") ||
                fileSuffix.equals(".GIF") || fileSuffix.equals(".PNG")) {
            type = NoteType.IMAGE_URL;
            return uploadImageDir;
        } else {
            type = NoteType.FILE_URL;
            return uploadFileDir;
        }
    }
}
