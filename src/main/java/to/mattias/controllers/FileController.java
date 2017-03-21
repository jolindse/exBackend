package to.mattias.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import to.mattias.entities.NoteObj;
import to.mattias.services.FileService;

import java.io.IOException;

/**
 * <h1>Created by Mattias on 2017-02-18.</h1>
 */
@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class FileController {

    private Logger logger = LoggerFactory.getLogger("kanban-logger");

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/file/{projectId}", method = RequestMethod.POST)
    @PreAuthorize("@securityService.hasRole(#projectId,'USERADMIN')")
    public ResponseEntity<NoteObj> uploadFile(@PathVariable int projectId, @RequestParam("file") MultipartFile file) throws IOException {
        NoteObj returnObj = fileService.store(projectId, file);
        if (returnObj != null) {
            return new ResponseEntity<>(returnObj, HttpStatus.OK);
        } else {
            logger.error("Failed to upload resource");
            return new ResponseEntity<>(new NoteObj(), HttpStatus.NO_CONTENT);
        }
    }
}