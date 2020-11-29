package com.company.read;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Reader {




    public List<String[]> readFile(String filePath, String separator) {

        char sep = separator.charAt(0);

        List<String[]> arrayList = null;
        CSVReader csvReader;

        try {
            CSVParserBuilder csvParserBuilder = new CSVParserBuilder();
            csvParserBuilder.withSeparator(sep);
            CSVParser csvParser = csvParserBuilder.build();
            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(new FileReader(filePath));
            csvReaderBuilder.withCSVParser(csvParser);
            csvReader = csvReaderBuilder.build();
            arrayList = csvReader.readAll();
            csvReader.close();


        } catch (FileNotFoundException e) {
            System.out.println("Sorry!" +
                    "File not found!" +
                    "Check file path" +
                    " or change file path to valid!");
        } catch (IOException e) {
            System.out.println("Something go wrong!" +
                    "Check folder or disk access.");
            System.exit(0);
        } catch (CsvException e) {
            System.out.println("CSV exception!");
        }


        return arrayList;
    }


    public List<String[]> readFile(String filePath) {

        List<String[]> arrayList = null;
        CSVReader csvReader;

        try {
            csvReader = new CSVReader(new FileReader(filePath));
            arrayList = csvReader.readAll();
            csvReader.close();


        } catch (FileNotFoundException e) {
            System.out.println("Sorry!" +
                    "File not found!" +
                    "Check file path" +
                    " or change file path to valid!");
        } catch (IOException e) {
            System.out.println("Something go wrong!" +
                    "Check folder or disk access.");
            System.exit(0);
        } catch (CsvException e) {
            System.out.println("CSV exception!");
        }


        return arrayList;
    }


}
