package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ParsedData {

    private Integer allDeaths;


    protected void setAllDeaths(Integer deaths) {
        this.allDeaths = deaths;
    }

    public Integer getAllDeaths() {
        return this.allDeaths;
    }


    public void backupData() {

        Integer data = this.allDeaths;

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh.mm-dd.MM.yyyy");

        String dateName = format.format(date);

        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream(new File("./src/config.ini")));

        } catch (IOException e) {

            System.out.println("Check disc access!");

        }

        try {

            FileOutputStream fos = new FileOutputStream(prop.getProperty("backupPath")
                    + dateName + "-backup.data");

            String pre = "Deaths count is ";
            byte[] arr = pre.getBytes();

            fos.write(arr);
            fos.write(String.valueOf(data).getBytes());
            fos.flush();
            fos.close();


        } catch (FileNotFoundException e) {

            System.out.println("File not found");

        } catch (IOException e) {

            System.out.println("Check disc access!");

        }

    }


}
