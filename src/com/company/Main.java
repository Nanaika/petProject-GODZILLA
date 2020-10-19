package com.company;


import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String filePath = args[0];

        File file = new File(filePath);

        GodzillaReader fileReader = new GodzillaReader(file.getAbsolutePath());

        List<String[]> arrayList = fileReader.readFile();

        GodzillaParser godzillaParser = new GodzillaParser(arrayList);

        godzillaParser.printDeaths();

        System.out.println(fileReader.getFilePath());
        fileReader.setFilePath(".");
        fileReader.readFile();

    }
}
