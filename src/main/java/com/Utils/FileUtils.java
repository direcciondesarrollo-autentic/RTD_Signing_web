package com.Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by root on 8/31/16.
 */
public class FileUtils {

    static public void writeFileFromInputStream(InputStream inputStream, String pathDestiny) {

        try {
            OutputStream os = new FileOutputStream(pathDestiny);

            byte[] buffer = new byte[1024];
            int bytesRead;
            //read from is to buffer
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            os.flush();
            os.close();
            System.out.println("Rdy!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
