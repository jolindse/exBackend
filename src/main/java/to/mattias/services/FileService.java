package to.mattias.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import to.mattias.constants.NoteType;
import to.mattias.entities.NoteObj;

import java.io.*;

/**
 * <h1>Created by Mattias on 2017-02-18.</h1>
 *
 * Stores an uploaded file to disk and creates a new
 * NoteObject and persists it in the db.
 *
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


    public NoteObj store(int projectId, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        // Set a unique filename for the file to be stored
        String filename = String.valueOf(System.currentTimeMillis());

        // Get the file-type
        String fileSuffix = originalFilename.substring(
                originalFilename.lastIndexOf("."), originalFilename.length());

        // Sets the path where to store the file
        String filePath = String.format("%s%s/%s/%s%s", uploadBaseDir, projectId, getFileType(fileSuffix), filename, fileSuffix);

        // Gets the path to the file to be stored
        String path = filePath.substring(0, filePath.lastIndexOf("/"));

        // Store the file if the path exists, otherwise create the dirs
        if(new File(path).exists()) {
           return storeFile(file, filePath, projectId, fileSuffix, filename);
        } else {
            new File(path).mkdirs();
            return storeFile(file, filePath, projectId, fileSuffix, filename);
        }
    }

    /**
     * Stores the uploaded file to disk
     * @param file
     * @param filePath
     * @param projectId
     * @param fileSuffix
     * @param filename
     * @return The newly created NoteObject
     */
    private NoteObj storeFile(MultipartFile file, String filePath,
                              int projectId, String fileSuffix, String filename) {

        // Check if the uploaded file is empty, if not store it
        if (!file.isEmpty()) {
            try {
                file.transferTo(new File(filePath));
                NoteObj noteObj = new NoteObj();
                noteObj.setNoteType(type);
                noteObj.setNoteObjContent(
                        String.format("/assets/%d/%s%s%s", projectId,
                                getFileType(fileSuffix), filename, fileSuffix));
                return noteObjService.save(noteObj);
            } catch (IOException e) {
                logger.error("Failed to store file on disk");
                return null;
            }
        } else {
            logger.error("Failed to store file - File is empty");
            return null;
        }
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
