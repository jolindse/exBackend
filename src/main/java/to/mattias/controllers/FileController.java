package to.mattias.controllers;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import to.mattias.entities.NoteObj;
import to.mattias.services.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h1>Created by Mattias on 2017-02-18.</h1>
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<NoteObj> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        NoteObj returnObj = fileService.store(file);
        if (returnObj != null) {
            return new ResponseEntity<>(returnObj, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new NoteObj(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

