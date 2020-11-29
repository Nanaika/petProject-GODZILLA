package com.company.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {

    public String extractName(String url) {

        return url.substring(url.lastIndexOf("/") + 1);

    }

    public String createSavePath(String url, String saveDir) {

        String fileName = extractName(url);
        String saveFilePath;

        if (saveDir.charAt(saveDir.length() - 1) == '/') {

            saveFilePath = saveDir + fileName;

        } else {

            saveFilePath = saveDir + File.separator + fileName;

        }

        return saveFilePath;

    }


    public void downloadFile(String url, String saveDir) {


        String saveFilePath = createSavePath(url, saveDir);


        URL urlObj = null;
        try {

            urlObj = new URL(url);

        } catch (MalformedURLException e) {

            System.out.println("Malformed URL!");
        }

        HttpURLConnection httpCon = null;
        try {

            assert urlObj != null;
            httpCon = (HttpURLConnection) urlObj.openConnection();

        } catch (IOException e) {

            System.out.println("I/O exception");

        }


        assert httpCon != null;
        try (InputStream is = httpCon.getInputStream();
             FileOutputStream fos = new FileOutputStream(saveFilePath)) {

            int bytesRead;
            byte[] buffer = new byte[20485760];
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                fos.flush();
            }

            System.out.println("File downloaded.\n");

        } catch (IOException e) {
            System.out.println("Failed to download file!\n" +
                    "===========\n" +
                    "Possible problems\n" +
                    "1.File not available for download!" +
                    "Check url!\n" +
                    "2.Check folder or disk access!\n");
            System.exit(0);

        }


    }

}
















