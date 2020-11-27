package com.company;

import com.company.download.Downloader;
import com.company.parse.ParsedData;
import com.company.parse.Parser;
import com.company.read.Reader;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.PrintWriter;
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

        Option keyWord = new Option("kw", "keyword", true, "keyword for parsing operations");
        keyWord.setArgName("keyword for parsing");
        options.addOption(keyWord);

        Option keyCountry = new Option("kc", "keycountry",
                true, "keycountry for visualization");
        keyCountry.setArgName("keycountry for visualization");
        options.addOption(keyCountry);

        Option keyYearMonth = new Option("km", "keymonth", true,
                "keymonth for visualization");
        keyYearMonth.setArgName("keymonth for visualization");
        options.addOption(keyYearMonth);

        Option visual = new Option("v", "visual",
                true, "data visualization");
        visual.setArgName("path to file");
        options.addOption(visual);


        CommandLineParser clparser = new DefaultParser();
        HelpFormatter help = new HelpFormatter();

        CommandLine cmd = null;


        try {
            cmd = clparser.parse(options, args);
        } catch (ParseException e) {

            System.out.println("-d <url> -o <path> -p[path] -kw <keyword> -sep<\" \"> -b<path>");
            System.out.println("-v <path> -kw <keyword> -kc <country> -km <year-month>");
            System.out.println("-p <path> -kw <keyword> -sep<\" \"> -b<path>\n");

            System.out.println(e.getMessage());

            PrintWriter pw = new PrintWriter(System.out);
            help.printOptions(pw, 200, options, 10, 20);

            pw.flush();
            pw.close();

            System.exit(0);
        }


        if (cmd.hasOption("v")) {
            if (cmd.hasOption("kw")) {
                if (cmd.hasOption("kc")) {
                    if (cmd.hasOption("km")) {
                        Visualization vis = new Visualization();

                        vis.setKeyWord(cmd.getOptionValue("kw"));
                        vis.setKeyCountry(cmd.getOptionValue("kc"));
                        vis.setFilePath(cmd.getOptionValue("v"));
                        vis.setKeyYearMonth(cmd.getOptionValue("km"));
                        vis.saveChart(vis.createDataSet(), vis.getKeyYearMonth());

                    } else {

                        Visualization vis = new Visualization();
                        vis.setKeyWord(cmd.getOptionValue("kw"));
                        vis.setKeyCountry(cmd.getOptionValue("kc"));
                        vis.setFilePath(cmd.getOptionValue("v"));
                        vis.createAndSaveYearByMonths();

                    }
                }
            }

        }


        if (cmd.hasOption("d") && cmd.hasOption("o")) {

            Downloader downloader = new Downloader();

            downloader.setFileUrl(cmd.getOptionValue("d"));
            downloader.setSaveDir(cmd.getOptionValue("o"));
            downloader.downloadFile();

        } else if (cmd.hasOption("d") && cmd.hasOption("o")
                && cmd.hasOption("p")) {

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
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

        } else if (cmd.hasOption("d") && cmd.hasOption("o")
                && cmd.hasOption("p") && cmd.hasOption("kw")) {

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
            parser.setKeyword(cmd.getOptionValue("kw"));
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");


        } else if (cmd.hasOption("d") && cmd.hasOption("o")
                && cmd.hasOption("p") && cmd.hasOption("sep")) {

            Downloader downloader = new Downloader();

            downloader.setFileUrl(cmd.getOptionValue("d"));
            downloader.setSaveDir(cmd.getOptionValue("o"));
            downloader.downloadFile();

            String filename = downloader.getFileName();

            String path = cmd.getOptionValue("o");

            Reader reader = new Reader();
            reader.setSeparator(cmd.getOptionValue("sep"));
            reader.setFilePath(path + File.separator + filename);
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");


        } else if (cmd.hasOption("d") && cmd.hasOption("o")
                && cmd.hasOption("p") && cmd.hasOption("kw")
                && cmd.hasOption("sep")) {

            Downloader downloader = new Downloader();

            downloader.setFileUrl(cmd.getOptionValue("d"));
            downloader.setSaveDir(cmd.getOptionValue("o"));
            downloader.downloadFile();

            String filename = downloader.getFileName();

            String path = cmd.getOptionValue("o");

            Reader reader = new Reader();
            reader.setSeparator(cmd.getOptionValue("sep"));
            reader.setFilePath(path + File.separator + filename);
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            parser.setKeyword(cmd.getOptionValue("kw"));
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");


        } else if (cmd.hasOption("d") && cmd.hasOption("o")
                && cmd.hasOption("p") && cmd.hasOption("kw")
                && cmd.hasOption("sep")
                && cmd.hasOption("b")) {

            Downloader downloader = new Downloader();

            downloader.setFileUrl(cmd.getOptionValue("d"));
            downloader.setSaveDir(cmd.getOptionValue("o"));
            downloader.downloadFile();

            String filename = downloader.getFileName();

            String path = cmd.getOptionValue("o");

            Reader reader = new Reader();
            reader.setSeparator(cmd.getOptionValue("sep"));
            reader.setFilePath(path + File.separator + filename);
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            parser.setKeyword(cmd.getOptionValue("kw"));
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

            parsedData.setBackupPath(cmd.getOptionValue("b"));

            parsedData.backupData();
            System.out.println("Data backuped!");
        } else if (cmd.hasOption("d") && cmd.hasOption("o")
                && cmd.hasOption("p") && cmd.hasOption("kw")
                && cmd.hasOption("b")) {

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
            parser.setKeyword(cmd.getOptionValue("kw"));
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

            parsedData.setBackupPath(cmd.getOptionValue("b"));

            parsedData.backupData();
            System.out.println("Data backuped!");
        } else if (cmd.hasOption("d") && cmd.hasOption("o")
                && cmd.hasOption("p") && cmd.hasOption("sep")
                && cmd.hasOption("b")) {

            Downloader downloader = new Downloader();

            downloader.setFileUrl(cmd.getOptionValue("d"));
            downloader.setSaveDir(cmd.getOptionValue("o"));
            downloader.downloadFile();

            String filename = downloader.getFileName();

            String path = cmd.getOptionValue("o");

            Reader reader = new Reader();
            reader.setSeparator(cmd.getOptionValue("sep"));
            reader.setFilePath(path + File.separator + filename);
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

            parsedData.setBackupPath(cmd.getOptionValue("b"));

            parsedData.backupData();
            System.out.println("Data backuped!");

        } else if (cmd.hasOption("p")) {

            Reader reader = new Reader();
            reader.setFilePath(cmd.getOptionValue("p"));
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

        } else if (cmd.hasOption("p") &&
                cmd.hasOption("sep")) {

            Reader reader = new Reader();
            reader.setSeparator("sep");
            reader.setFilePath(cmd.getOptionValue("p"));
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

        } else if (cmd.hasOption("p") &&
                cmd.hasOption("kw")) {

            Reader reader = new Reader();
            reader.setFilePath(cmd.getOptionValue("p"));
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            parser.setKeyword(cmd.getOptionValue("kw"));
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

        } else if (cmd.hasOption("p") &&
                cmd.hasOption("kw")
                && cmd.hasOption("sep")) {

            Reader reader = new Reader();
            reader.setSeparator(cmd.getOptionValue("sep"));
            reader.setFilePath(cmd.getOptionValue("p"));
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            parser.setKeyword(cmd.getOptionValue("kw"));
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

        } else if (cmd.hasOption("p") && cmd.hasOption("b")) {

            Reader reader = new Reader();
            reader.setFilePath(cmd.getOptionValue("p"));
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

            parsedData.setBackupPath(cmd.getOptionValue("b"));

            parsedData.backupData();
            System.out.println("Data backuped!");

        } else if (cmd.hasOption("p") &&
                cmd.hasOption("sep") &&
                cmd.hasOption("b")) {

            Reader reader = new Reader();
            reader.setSeparator("sep");
            reader.setFilePath(cmd.getOptionValue("p"));
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

            parsedData.setBackupPath(cmd.getOptionValue("b"));

            parsedData.backupData();
            System.out.println("Data backuped!");

        } else if (cmd.hasOption("p") &&
                cmd.hasOption("kw") &&
                cmd.hasOption("b")) {

            Reader reader = new Reader();
            reader.setFilePath(cmd.getOptionValue("p"));
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            parser.setKeyword(cmd.getOptionValue("kw"));
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

            parsedData.setBackupPath(cmd.getOptionValue("b"));

            parsedData.backupData();
            System.out.println("Data backuped!");

        } else if (cmd.hasOption("p") &&
                cmd.hasOption("kw")
                && cmd.hasOption("sep") &&
                cmd.hasOption("b")) {

            Reader reader = new Reader();
            reader.setSeparator(cmd.getOptionValue("sep"));
            reader.setFilePath(cmd.getOptionValue("p"));
            List<String[]> data = reader.readFile();

            Parser parser = new Parser(data);
            parser.setKeyword(cmd.getOptionValue("kw"));
            ParsedData parsedData = parser.calculate();
            System.out.println("File parsed\n");
            System.out.println("Count is : " + parsedData.getCount() + "\n");

            parsedData.setBackupPath(cmd.getOptionValue("b"));

            parsedData.backupData();
            System.out.println("Data backuped!");

        }



        if (cmd.getOptions().length == 0) {

            System.out.println("-d <url> -o <path> -p[path] -kw <keyword> -sep<\" \"> -b<path>");
            System.out.println("-v <path> -kw <keyword> -kc <country> -km <year-month>");
            System.out.println("-p <path> -kw <keyword> -sep<\" \"> -b<path>\n");
            PrintWriter pw = new PrintWriter(System.out);
            help.printOptions(pw, 150, options, 5, 10);


            pw.flush();
            pw.close();

        }
    }


//        if (cmd.hasOption("d")) {
//            if (cmd.hasOption("o")) {
//                if (cmd.hasOption("p")) {
//                    if (cmd.hasOption("kw")) {
//                        if (cmd.hasOption("sep")) {
//                            if (cmd.hasOption("b")) {
//                                Downloader downloader = new Downloader();
//
//                                downloader.setFileUrl(cmd.getOptionValue("d"));
//                                downloader.setSaveDir(cmd.getOptionValue("o"));
//                                downloader.downloadFile();
//
//                                String filename = downloader.getFileName();
//
//                                String path = cmd.getOptionValue("o");
//                                String sep = cmd.getOptionValue("sep");
//
//                                Reader reader = new Reader();
//                                reader.setFilePath(path + File.separator + filename);
//                                reader.setSeparator(sep);
//                                List<String[]> data = reader.readFile();
//
//                                Parser parser = new Parser(data);
//                                ParsedData parsedData = parser.calculateAllDeaths();
//                                System.out.println("File parsed\n");
//                                System.out.println("Count is : " + parsedData.getCount() + "\n");
//
//                                parsedData.setBackupPath(cmd.getOptionValue("b"));
//
//                                parsedData.backupData();
//                                System.out.println("Data backuped!");
//                            } else {
//                                Downloader downloader = new Downloader();
//
//                                downloader.setFileUrl(cmd.getOptionValue("d"));
//                                downloader.setSaveDir(cmd.getOptionValue("o"));
//                                downloader.downloadFile();
//
//                                String filename = downloader.getFileName();
//
//                                String path = cmd.getOptionValue("o");
//                                String sep = cmd.getOptionValue("sep");
//
//                                Reader reader = new Reader();
//                                reader.setFilePath(path + File.separator + filename);
//                                reader.setSeparator(sep);
//                                List<String[]> data = reader.readFile();
//
//                                Parser parser = new Parser(data);
//                                ParsedData parsedData = parser.calculateAllDeaths();
//                                System.out.println("File parsed\n");
//                                System.out.println("Count is : " + parsedData.getCount() + "\n");
//                            }
//                        } else {
//                            Downloader downloader = new Downloader();
//
//                            downloader.setFileUrl(cmd.getOptionValue("d"));
//                            downloader.setSaveDir(cmd.getOptionValue("o"));
//                            downloader.downloadFile();
//
//                            String filename = downloader.getFileName();
//
//                            String path = cmd.getOptionValue("o");
//
//                            Reader reader = new Reader();
//                            reader.setFilePath(path + File.separator + filename);
//                            List<String[]> data = reader.readFile();
//
//                            Parser parser = new Parser(data);
//                            ParsedData parsedData = parser.calculateAllDeaths();
//                            System.out.println("File parsed\n");
//                            System.out.println("Count is : " + parsedData.getCount() + "\n");
//
//                            parsedData.setBackupPath(cmd.getOptionValue("b"));
//
//                            parsedData.backupData();
//                            System.out.println("Data backuped!");
//                        }
//                    } else {
//
//
//                    }
//                } else {
//                    Downloader downloader = new Downloader();
//
//                    downloader.setFileUrl(cmd.getOptionValue("d"));
//                    downloader.setSaveDir(cmd.getOptionValue("o"));
//                    downloader.downloadFile();
//                }
//            } else {
//                System.out.println("Enter command -o for output path");
//            }
//        } else if (cmd.hasOption("p")) {
//            if (cmd.hasOption("sep") || !(cmd.hasOption("sep"))) {
//                if (cmd.hasOption("b")) {
//                    String path = cmd.getOptionValue("p");
//                    String sep = cmd.getOptionValue("sep");
//
//                    if (cmd.getOptionValue("p") == null) {
//
//                        System.out.println("Enter path to file for command -p!");
//                        System.exit(0);
//                    }
//
//                    Reader reader = new Reader();
//                    reader.setFilePath(path);
//                    reader.setSeparator(sep);
//                    List<String[]> data = reader.readFile();
//
//                    Parser parser = new Parser(data);
//                    ParsedData parsedData = parser.calculateAllDeaths();
//                    System.out.println("File parsed\n");
//                    System.out.println("Count is : " + parsedData.getCount() + "\n");
//                    parsedData.setBackupPath(cmd.getOptionValue("b"));
//
//                    parsedData.backupData();
//                    System.out.println("Data backuped!");
//                } else {
//                    String path = cmd.getOptionValue("p");
//                    String sep = cmd.getOptionValue("sep");
//
//                    if (cmd.getOptionValue("p") == null) {
//
//                        System.out.println("Enter path to file for command -p!");
//                        System.exit(0);
//                    }
//
//
//                    Reader reader = new Reader();
//                    reader.setFilePath(path);
//                    reader.setSeparator(sep);
//                    List<String[]> data = reader.readFile();
//
//                    Parser parser = new Parser(data);
//                    ParsedData parsedData = parser.calculateAllDeaths();
//                    System.out.println("File parsed\n");
//                    System.out.println("Count is : " + parsedData.getCount() + "\n");
//
//                }
//            } else {
//                String path = cmd.getOptionValue("p");
//
//                Reader reader = new Reader();
//                reader.setFilePath(path);
//                List<String[]> data = reader.readFile();
//
//                Parser parser = new Parser(data);
//                ParsedData parsedData = parser.calculateAllDeaths();
//                System.out.println("File parsed\n");
//                System.out.println("Count is : " + parsedData.getCount() + "\n");
//            }
//        } else if (cmd.getOptions().length == 0) {
//
//
//            System.out.println("-d <url> -o <path> -p[path] -sep<\" \"> -b<path>\n");
//
//            PrintWriter pw = new PrintWriter(System.out);
//            help.printOptions(pw, 150, options, 5, 10);
//
//
//            pw.flush();
//            pw.close();
//
//        }


}


// ===========================================================================




