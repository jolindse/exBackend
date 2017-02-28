package to.mattias.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import to.mattias.entities.NoteObj;
import to.mattias.services.FileService;

import javax.servlet.http.HttpServletRequest;
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

    private final Logger logger = LoggerFactory.getLogger("kanban-logger");

    @PostMapping
    public ResponseEntity<NoteObj> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        NoteObj returnObj = fileService.store(file);
        if (returnObj != null) {
            return new ResponseEntity<>(returnObj, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new NoteObj(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

