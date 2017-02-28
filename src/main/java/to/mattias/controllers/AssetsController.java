package to.mattias.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <h1>Created by Mattias on 2017-02-28.</h1>
 */
@RestController
public class AssetsController {
    @Autowired
    private Environment env;

    @RequestMapping(value = "/assets/{dirName}/{fileName:.+}", method = RequestMethod.GET)
    public void serveFile(@PathVariable("dirName") String dirName, @PathVariable("fileName") String fileName, HttpServletResponse response) {
        String baseDir = env.getProperty("upload.base.dir");
        try {
            InputStream is = new FileInputStream(baseDir + dirName + "\\" + fileName);
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
