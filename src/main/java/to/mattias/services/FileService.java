package to.mattias.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * <h1>Created by Mattias on 2017-02-18.</h1>
 */
@Service
public class FileService {


    @Autowired
    private Environment env;

    private static int counter = 1;

    public boolean store(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String originalFilenamePrefix = filename.substring(0, filename.lastIndexOf("."));
        String fileSuffix = filename.substring(filename.lastIndexOf("."), filename.length());
        String path = env.getProperty("upload.base.dir") + getFileType(fileSuffix);

        boolean ok = false;
        File fileToStore;

        // Check if the file already exists
        do {
            fileToStore = new File(path + filename);
            if (fileToStore.exists()) {
                String filenamePrefix = originalFilenamePrefix + " - " + counter++;
                filename = filenamePrefix + filename.substring(filename.lastIndexOf("."));
            } else {
                counter = 1;
                ok = true;
            }
        } while(!ok);

        // If the file contains data, write it to disk
        if(!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path + filename)));
                bos.write(bytes);
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private String getFileType(String fileSuffix) {
        if (fileSuffix.equals(".jpg") || fileSuffix.equals(".gif") ||
                fileSuffix.equals(".png") || fileSuffix.equals(".JPG") ||
                fileSuffix.equals(".GIF") || fileSuffix.equals(".PNG")) {
            return env.getProperty("upload.image.dir");
        } else {
            return env.getProperty("upload.file.dir");
        }
    }
}
