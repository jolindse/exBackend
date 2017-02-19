package to.mattias.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * <h1>Created by Mattias on 2017-02-18.</h1>
 */
@Service
public class FileService {

    private static int counter = 1;

    public boolean store(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String originalFilenamePrefix = filename.substring(0, filename.lastIndexOf("."));
        boolean ok = false;
        File fileToStore;


        // Check if the file already exists
        do {
            fileToStore = new File(filename);
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
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filename)));
                bos.write(bytes);
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
