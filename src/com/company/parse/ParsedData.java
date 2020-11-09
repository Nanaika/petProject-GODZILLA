package com.company.parse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParsedData {

    private Long allDeaths;
    private String backupPath = "";


    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }

    protected void setAllDeaths(Long deaths) {
        this.allDeaths = deaths;
    }

    public Long getAllDeaths() {
        return this.allDeaths;
    }


    public void backupData() {

        Long data = this.allDeaths;

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh.mm-dd.MM.yyyy");

        String dateName = format.format(date);


        try {

            if (backupPath == null) {
                backupPath = "./";
            }

            String check;
            check = backupPath.substring(backupPath.lastIndexOf("/") + 1);
            if (check.equals("")) {

                FileOutputStream fos = new FileOutputStream(backupPath
                        + dateName + "-backup.data");

                String pre = "Count is ";
                byte[] arr = pre.getBytes();

                fos.write(arr);
                fos.write(String.valueOf(data).getBytes());
                fos.flush();
                fos.close();

            } else {

                FileOutputStream fos = new FileOutputStream(backupPath, true);

                String newLine = "\n";
                String pre = "    Count is ";
                String tab = "Date of backup : ";

                byte[] arr = pre.getBytes();
                byte[] nl = newLine.getBytes();
                byte[] dateBytes = dateName.getBytes();
                byte[] tabBytes = tab.getBytes();

                fos.write(tabBytes);
                fos.write(dateBytes);
                fos.write(arr);
                fos.write(String.valueOf(data).getBytes());
                fos.write(nl);
                fos.flush();
                fos.close();


            }


        } catch (IOException e) {

            System.out.println("Cant write to source folder!\n" +
                    "Check access to source folder or disc!");
            System.exit(0);

        }

    }


}
