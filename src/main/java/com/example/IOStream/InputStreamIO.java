package com.example.IOStream;

import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * InputStream使用.
 *
 * @author 易富军
 */
public class InputStreamIO {
    
    
    public static void text(List<InputStream> inputStreams) {
        for (InputStream inputStream : inputStreams) {
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream("D:\\java工作");
                int bytesWritten = 0;
                int byteCount = 0;
                byte[] bytes = new byte[1024];
                while ((byteCount = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, bytesWritten, byteCount);
                    bytesWritten += byteCount;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }  
        }
        

    }

}
