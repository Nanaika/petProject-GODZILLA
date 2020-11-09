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

    private String filePath;
    private String separator = "";

    public void setSeparator(String separator) {
        this.separator = separator;
    }


    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public List<String[]> readFile() {

        List<String[]> arrayList = null;

        try {

            if (separator == null) {
                separator = "";
            }

            if (!(separator.equals(""))) {
                char sep = separator.charAt(0);


                CSVParserBuilder csvParser = new CSVParserBuilder();
                csvParser.withSeparator(sep);

                CSVParser csvParser1 = csvParser.build();

                CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(new FileReader(filePath));

                csvReaderBuilder.withCSVParser(csvParser1);

                CSVReader csvReader = csvReaderBuilder.build();


                arrayList = csvReader.readAll();

                csvReader.close();

            } else {

                CSVReader csvReader = new CSVReader(new FileReader(filePath));
                arrayList = csvReader.readAll();

                csvReader.close();
            }

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
