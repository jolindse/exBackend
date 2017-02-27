package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import to.mattias.services.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h1>Created by Mattias on 2017-02-18.</h1>
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public void uploadFile(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        if (fileService.store(file)) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            response.getWriter().write("You successfully uploaded " + file.getOriginalFilename());
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Oops, something went wrong");
        }
    }


}

