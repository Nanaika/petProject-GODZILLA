package com.company;


import org.apache.commons.cli.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        System.out.println("Welcome to GODZILLA.\n");


//        Properties prop = new Properties();
//        prop.load(new FileInputStream(new File("./src/config.ini")));

        Options options = new Options();


        Option parseOpt = new Option("p", "parse", true, "parse csv file");
        parseOpt.setArgs(2);
        parseOpt.setArgName("1st arg : path to file, 2nd arg : separator");
        parseOpt.setValueSeparator(' ');
        parseOpt.setOptionalArg(true);
        options.addOption(parseOpt);

        Option downloadOpt = new Option("d", "download", true, "download csv file");

        downloadOpt.setArgs(2);
        downloadOpt.setArgName("1st arg : download path, 2nd arg : save folder path");
        downloadOpt.setValueSeparator(' ');
        options.addOption(downloadOpt);

        Option backupOpt = new Option("b", "backup", true, "backup parsed data to file");
        backupOpt.setRequired(false);
        backupOpt.setArgName("path to save folder");
        backupOpt.setOptionalArg(true);
        options.addOption(backupOpt);


        CommandLineParser clparser = new DefaultParser();

        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd = null;


        try {
            cmd = clparser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(150,"utility-name",
                    "======",options,"======");

            System.exit(1);
        }


        if (cmd.hasOption("d")) {

            Downloader downloader = new Downloader();

            String[] values = cmd.getOptionValues("d");

            downloader.setFileUrl(values[0]);
            downloader.setSaveDir(values[1]);
            downloader.downloadFile();

        }
        if (cmd.hasOption("p")) {

            if (cmd.hasOption("d")) {

                String[] valuesDown = cmd.getOptionValues("d");
                String[] valuesPars = cmd.getOptionValues("p");

                String name = valuesDown[0].substring(valuesDown[0].lastIndexOf("/") + 1);
                String path = valuesDown[1] + File.separator + name;


                Reader reader = new Reader(path,valuesPars[0]);
                List<String[]> data = reader.readFile();

                Parser parser = new Parser(data);
                ParsedData parsedData = parser.calculateAllDeaths();
                System.out.println("File parsed\n");
                System.err.println("Deaths count is : " + parsedData.getAllDeaths() + "\n");


            } else {

                String[] valuesPars = cmd.getOptionValues("p");

                Reader reader = new Reader(valuesPars[0], valuesPars[1]);
                List<String[]> data = reader.readFile();

                Parser parser = new Parser(data);
                ParsedData parsedData = parser.calculateAllDeaths();
                System.out.println("File parsed\n");
                System.err.println("Deaths count is : " + parsedData.getAllDeaths() + "\n");
            }




        }
        if (cmd.hasOption("b")) {
            if (!(cmd.hasOption("p"))) {

                System.out.println("Cant backup data without parsing file!");

            } else {

                String[] valuesPars = cmd.getOptionValues("p");

                Reader reader = new Reader(valuesPars[0], valuesPars[1]);
                List<String[]> data = reader.readFile();

                Parser parser = new Parser(data);
                ParsedData parsedData = parser.calculateAllDeaths();

                parsedData.backupData();
                System.out.println("Data backuped!");
            }

        }
        if (cmd.getOptions().length == 0) {

            System.err.println("     Possible commands : ");
            PrintWriter pw = new PrintWriter(System.err);
            formatter.printOptions(pw, 150, options, 5, 10);


            pw.flush();
            pw.close();


        }
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




