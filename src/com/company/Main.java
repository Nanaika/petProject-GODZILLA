package com.company;

import com.company.download.Downloader;
import com.company.parse.ParsedData;
import com.company.parse.Parser;
import com.company.read.Reader;
import org.apache.commons.cli.*;

import java.io.*;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        System.out.println("Welcome to GODZILLA.\n");


        Options options = new Options();


        Option parse = new Option("p", "parse", true, "parse csv file");
        parse.setOptionalArg(true);
        parse.setArgName("path to file");
        options.addOption(parse);

        Option download = new Option("d", "download", true, "download csv file");
        download.setArgName("download path");
        options.addOption(download);

        Option backup = new Option("b", "backup", true, "backup parsed data to file");
        backup.setArgName("path to save folder");
        options.addOption(backup);

        Option output = new Option("o", "output", true, "path to save downloaded file");
        output.setArgName("path to save the file");
        options.addOption(output);

        Option separator = new Option("sep", "separator", true, "defines a separator for working with a file");
        separator.setArgName("separator for file");
        options.addOption(separator);

        CommandLineParser clparser = new DefaultParser();


        CommandLine cmd = null;


        try {
            cmd = clparser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            File help = new File("./HELP.txt");
            try {
                FileInputStream fis = new FileInputStream(help.getPath());
                byte[] helpBuffer = new byte[8000];
                while (fis.read() != -1) {
                    helpBuffer = fis.readAllBytes();
                }

                for (byte a : helpBuffer
                ) {
                    System.out.print((char) a);
                }

                System.out.println("\n");
                fis.close();

            } catch (FileNotFoundException ex) {
                System.out.println("Help file not found.");
            } catch (IOException ex) {
                System.out.println("IO exception!");
            }

            System.exit(0);
        }


        if (cmd.hasOption("d")) {
            if (cmd.hasOption("o")) {
                if (cmd.hasOption("p")) {
                    if (cmd.hasOption("sep") || !(cmd.hasOption("sep"))) {
                        if (cmd.hasOption("b")) {
                            Downloader downloader = new Downloader();

                            downloader.setFileUrl(cmd.getOptionValue("d"));
                            downloader.setSaveDir(cmd.getOptionValue("o"));
                            downloader.downloadFile();

                            String filename = downloader.getFileName();

                            String path = cmd.getOptionValue("o");
                            String sep = cmd.getOptionValue("sep");

                            Reader reader = new Reader();
                            reader.setFilePath(path + File.separator + filename);
                            reader.setSeparator(sep);
                            List<String[]> data = reader.readFile();

                            Parser parser = new Parser(data);
                            ParsedData parsedData = parser.calculateAllDeaths();
                            System.out.println("File parsed\n");
                            System.out.println("Count is : " + parsedData.getAllDeaths() + "\n");

                            parsedData.setBackupPath(cmd.getOptionValue("b"));

                            parsedData.backupData();
                            System.out.println("Data backuped!");
                        } else {
                            Downloader downloader = new Downloader();

                            downloader.setFileUrl(cmd.getOptionValue("d"));
                            downloader.setSaveDir(cmd.getOptionValue("o"));
                            downloader.downloadFile();

                            String filename = downloader.getFileName();

                            String path = cmd.getOptionValue("o");
                            String sep = cmd.getOptionValue("sep");

                            Reader reader = new Reader();
                            reader.setFilePath(path + File.separator + filename);
                            reader.setSeparator(sep);
                            List<String[]> data = reader.readFile();

                            Parser parser = new Parser(data);
                            ParsedData parsedData = parser.calculateAllDeaths();
                            System.out.println("File parsed\n");
                            System.out.println("Count is : " + parsedData.getAllDeaths() + "\n");
                        }
                    } else {
                        Downloader downloader = new Downloader();

                        downloader.setFileUrl(cmd.getOptionValue("d"));
                        downloader.setSaveDir(cmd.getOptionValue("o"));
                        downloader.downloadFile();

                        String filename = downloader.getFileName();

                        String path = cmd.getOptionValue("o");

                        Reader reader = new Reader();
                        reader.setFilePath(path + File.separator + filename);
                        List<String[]> data = reader.readFile();

                        Parser parser = new Parser(data);
                        ParsedData parsedData = parser.calculateAllDeaths();
                        System.out.println("File parsed\n");
                        System.out.println("Count is : " + parsedData.getAllDeaths() + "\n");
                    }
                } else {
                    Downloader downloader = new Downloader();

                    downloader.setFileUrl(cmd.getOptionValue("d"));
                    downloader.setSaveDir(cmd.getOptionValue("o"));
                    downloader.downloadFile();
                }
            } else {
                System.out.println("Enter command -o for output path");
            }
        } else if (cmd.hasOption("p")) {
            if (cmd.hasOption("sep") || !(cmd.hasOption("sep"))) {
                if (cmd.hasOption("b")) {
                    String path = cmd.getOptionValue("p");
                    String sep = cmd.getOptionValue("sep");

                    if (cmd.getOptionValue("p") == null) {

                        System.out.println("Enter path to file for command -p!");
                        System.exit(0);
                    }

                    Reader reader = new Reader();
                    reader.setFilePath(path);
                    reader.setSeparator(sep);
                    List<String[]> data = reader.readFile();

                    Parser parser = new Parser(data);
                    ParsedData parsedData = parser.calculateAllDeaths();
                    System.out.println("File parsed\n");
                    System.out.println("Count is : " + parsedData.getAllDeaths() + "\n");
                    parsedData.setBackupPath(cmd.getOptionValue("b"));

                    parsedData.backupData();
                    System.out.println("Data backuped!");
                } else {
                    String path = cmd.getOptionValue("p");
                    String sep = cmd.getOptionValue("sep");

                    if (cmd.getOptionValue("p") == null) {

                        System.out.println("Enter path to file for command -p!");
                        System.exit(0);
                    }


                    Reader reader = new Reader();
                    reader.setFilePath(path);
                    reader.setSeparator(sep);
                    List<String[]> data = reader.readFile();

                    Parser parser = new Parser(data);
                    ParsedData parsedData = parser.calculateAllDeaths();
                    System.out.println("File parsed\n");
                    System.out.println("Count is : " + parsedData.getAllDeaths() + "\n");

                }
            } else {
                String path = cmd.getOptionValue("p");

                Reader reader = new Reader();
                reader.setFilePath(path);
                List<String[]> data = reader.readFile();

                Parser parser = new Parser(data);
                ParsedData parsedData = parser.calculateAllDeaths();
                System.out.println("File parsed\n");
                System.out.println("Count is : " + parsedData.getAllDeaths() + "\n");
            }
        } else if (cmd.getOptions().length == 0) {

            File help = new File("./HELP.txt");
            try {
                FileInputStream fis = new FileInputStream(help.getPath());
                byte[] helpBuffer = new byte[8000];
                while (fis.read() != -1) {
                    helpBuffer = fis.readAllBytes();
                }

                for (byte a : helpBuffer
                ) {
                    System.out.print((char) a);
                }

                System.out.println("\n");
                fis.close();

            } catch (FileNotFoundException e) {
                System.out.println("Help file not found.");
            } catch (IOException e) {
                System.out.println("IO exception!");
            }

        }




    }

}


// ===========================================================================




