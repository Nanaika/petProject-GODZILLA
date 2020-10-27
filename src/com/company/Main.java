package com.company;


import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {


    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to GODZILLA.\n" +
                "Please enter command.\n" +
                "or type help for list of commands.\n");

        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("./src/config.ini")));

        Options options = new Options();

        Option parseOpt = new Option("p", "parse", true, "parse csv file");
        parseOpt.setRequired(false);
        options.addOption(parseOpt);

        Option downloadOpt = new Option("d", "download", true, "download file");
        downloadOpt.setRequired(false);
        options.addOption(downloadOpt);


        CommandLineParser clparser = new DefaultParser();

        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd = null;


        try {
            cmd = clparser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }


        if (cmd.hasOption("d")) {

            Downloader downloader = new Downloader();
            downloader.setFileUrl(cmd.getOptionValue("d"));
            downloader.setSaveDir(prop.getProperty("saveDir"));
            downloader.downloadFile();

        } else if (cmd.hasOption("p")) {

            Reader reader = new Reader(cmd.getOptionValue("p"));
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            ParsedData parsedData = parser.calculateAllDeaths();

            System.out.println("File parsed");
            parsedData.backupData();

        }


    }


//        boolean con = true;
//
//        while (con) {
//
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            String options;
//
//            options = in.readLine();
//
//
//            switch (options) {
//                case "help":
//                case "-h":
//                case "?":
//                    System.out.println("Type:\n" +
//                            "'-d' to download file.\n" +
//                            "'exit' to exit from programm.\n");
//                    break;
//
//                case "-d":
//                    Downloader downloader = new Downloader();
//                    downloader.setFileUrl(prop.getProperty("fileUrl"));
//                    downloader.setSaveDir(prop.getProperty("saveDir"));
//                    downloader.downloadFile();
//                    break;
//
//                case "-p":
//                    String filePath = prop.getProperty("filePath");
//
//                    Reader fileReader = new Reader(filePath);
//
//                    List<String[]> arrayList = fileReader.readFile();
//
//                    Parser parser = new Parser(arrayList);
//
//                    ParsedData parsedData = parser.calculateAllDeaths();
//                    Integer allDeaths = parsedData.getAllDeaths();
//                    System.out.println("Deaths count is :" + allDeaths);
//                    break;
//
//                case "exit":
//                    con = false;
//            }
//
//
//        }


}

