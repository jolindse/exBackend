package to.mattias.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import to.mattias.entities.NoteObj;
import to.mattias.services.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * <h1>Created by Mattias on 2017-02-18.</h1>
 */
@RestController
@CrossOrigin("*")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ResponseEntity<NoteObj> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
    private final Logger logger = LoggerFactory.getLogger("kanban-logger");

    @PostMapping
    public ResponseEntity<NoteObj> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        NoteObj returnObj = fileService.store(file);
        if (returnObj != null) {
            return new ResponseEntity<>(returnObj, HttpStatus.OK);
        } else {
            // TODO: Log the error
            return new ResponseEntity<>(new NoteObj(), HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/assets/{dirName}/{fileName:.+}", method = RequestMethod.GET)
    public void serveFile(@PathVariable("dirName") String dirName, @PathVariable("fileName") String fileName, HttpServletResponse response) {
        String baseDir = env.getProperty("upload.base.dir");
        try {
            InputStream is = new FileInputStream(baseDir + dirName + "\\" + fileName);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            // TODO: Log the exception
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (IOException e) {
            // TODO: Log the exception
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}

