package com.gtvt.backendcustomermanagement.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Base64;

public class ConvertImage {
    public static String encodeImage(String imgPath, String savePath) throws Exception {

        // read image from file
        FileInputStream stream = new FileInputStream(imgPath);

        // get byte array from image stream
        int bufLength = 2048;
        byte[] buffer = new byte[2048];

        byte[] data;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int readLength;
        while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
            out.write(buffer, 0, readLength);
        }

        data = out.toByteArray();

        String imageString = Base64.getEncoder().encodeToString(data);

        FileWriter fileWriter = new FileWriter(savePath);

        fileWriter.write(imageString);

        // close streams
        fileWriter.close();
        out.close();
        stream.close();

        return imageString;

    }

    /**
     * decode/convert base64 string back into an image file and save on disk
     *
     * @param txtPath  path of file on disk which contains base64 string
     * @param savePath path on disk where we want to save the new image created from base64 string.
     * @throws Exception
     */
    public static void decodeImage(String txtPath, String savePath) throws Exception {

        byte[] data = Base64.getDecoder().decode(txtPath);

        FileOutputStream fileOutputStream = new FileOutputStream(savePath);

        // write array of bytes to an image file
        fileOutputStream.write(data);

        // close streams
        fileOutputStream.close();


    }


}
