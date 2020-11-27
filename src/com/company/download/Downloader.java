package com.company.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Downloader {

    private String fileUrl = "";
    private String saveDir = "";
    private String fileName;
    private String saveFilePath;


    public String getFileName() {
        return fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String url) {
        this.fileUrl = url;
    }

    public void setSaveDir(String path) {
        this.saveDir = path;
    }

    public String getSaveDir() {
        return this.saveDir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Downloader that = (Downloader) o;
        return fileUrl.equals(that.fileUrl) &&
                saveDir.equals(that.saveDir) &&
                fileName.equals(that.fileName) &&
                saveFilePath.equals(that.saveFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileUrl, saveDir, fileName, saveFilePath);
    }

    public void downloadFile() {


        fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        if (saveDir.charAt(saveDir.length() - 1) == '/') {

            saveFilePath = saveDir + fileName;
        } else {

            saveFilePath = saveDir + File.separator + fileName;

        }

        URL url = null;
        try {

            url = new URL(fileUrl);

        } catch (MalformedURLException e) {

            System.out.println("Malformed URL!");
        }

        HttpURLConnection httpCon = null;
        try {

            assert url != null;
            httpCon = (HttpURLConnection) url.openConnection();

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
















