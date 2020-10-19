package com.company;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GodzillaReader {

    private String filePath;

    GodzillaReader(String path) {
        this.filePath = path;
    }


    public void setFilePath(String path) {
        this.filePath = path;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public List<String[]> readFile () {

        List<String[]> arrayList = null;

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {


            arrayList = csvReader.readAll();




        } catch (FileNotFoundException e) {
            System.out.println("Sorry!" +
                    "File not found!" +
                    "Check file path" +
                    " or change file path to valid!");
        } catch (IOException e) {
            System.out.println("Something go wrong!");

        } catch (CsvException e) {
            System.out.println("CSV exception!");
        }


        return arrayList;
    }


}
