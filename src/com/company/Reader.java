package com.company;

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

    private String separator;
    private String filePath;

    Reader(String path, String separator) {
        this.filePath = path;
        this.separator = separator;
    }


    public List<String[]> readFile() {

        List<String[]> arrayList = null;

        try {


            char sep = separator.charAt(0);


            CSVParserBuilder csvParser = new CSVParserBuilder();
            csvParser.withSeparator(sep);

            CSVParser csvParser1 = csvParser.build();

            CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(new FileReader(filePath));

            csvReaderBuilder.withCSVParser(csvParser1);

            CSVReader csvReader = csvReaderBuilder.build();


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

        } catch (CsvException e) {
            System.out.println("CSV exception!");
        }


        return arrayList;
    }


}
