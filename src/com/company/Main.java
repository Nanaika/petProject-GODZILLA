package com.company;


import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String filePath = args[0];

        File file = new File(filePath);

        Reader fileReader = new Reader(file.getAbsolutePath());

        List<String[]> arrayList = fileReader.readFile();

        Parser parser = new Parser(arrayList);

        ParsedData parsedData = parser.calculateAllDeaths();
        Integer allDeaths = parsedData.getAllDeaths();
        System.out.println("Deaths count is :" + allDeaths);

        Downloader downloader = new Downloader();
        downloader.downloadFile();













    }
}
