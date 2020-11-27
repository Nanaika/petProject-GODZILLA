package com.company;

import com.company.read.Reader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Visualization  {

    private String filepath = "";
    private String keyWord = "";
    private String keyCountry = "";
    private String keyYearMonth = "";



    public void setFilePath(String path) {
        this.filepath = path;
    }

    public String getFilePath() {
        return this.filepath;
    }


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }


    public String getKeyCountry() {
        return keyCountry;
    }

    public void setKeyCountry(String keyCountry) {
        this.keyCountry = keyCountry;
    }

    public String getKeyYearMonth() {
        return keyYearMonth;
    }

    public void setKeyYearMonth(String keyYearMonth) {
        this.keyYearMonth = keyYearMonth;
    }


    public DefaultCategoryDataset createDataSet() {

        Reader reader = new Reader();
        reader.setFilePath(getFilePath());

        List<String[]> data = reader.readFile();

        String[] heading = data.get(0);


        for (int i = 0; i < heading.length; i++) {

            heading[i] = heading[i].trim();


        }

        if (keyYearMonth == null) {
            this.keyYearMonth = "";
        }

        int indexKeyWord = 0;
        int indexdate = 0;
        int indexCountry = 0;

        for (int a = 0; a < heading.length; a++) {

            if (heading[a].equals(keyWord)) {
                indexKeyWord = a;
            }
            if (heading[a].equals("Date_reported")) {
                indexdate = a;
            }
            if (heading[a].equals("Country")) {
                indexCountry = a;
            }
        }

        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();


        for (int i = 1; i < data.size(); i++) {

            String[] temp1 = data.get(i);
            String date = temp1[indexdate];
            String day = date.substring(date.length() - 2);

            if (data.get(i)[indexCountry].equals(keyCountry)) {


                if (data.get(i)[indexdate].startsWith(keyYearMonth)) {


                    String[] temp = data.get(i);


                    dataset1.addValue(Double.parseDouble(temp[indexKeyWord]),
                            "deaths", day);


                }


            }

        }

        return dataset1;
    }

    public JFreeChart saveChart(DefaultCategoryDataset dataset, String id) {
        JFreeChart chart = ChartFactory.createBarChart(keyYearMonth,
                "days",
                "count",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);







        File file = new File(id + "Deaths " + ".png");
        try {
            ChartUtilities.saveChartAsPNG(file, chart, 1366, 768);
        } catch (IOException e) {
            System.out.println("I/O exception.\n" +
                    "File not saved.");
        }

        return chart;
    }




    public void createAndSaveYearByMonths() {

        Reader reader = new Reader();
        reader.setFilePath(getFilePath());

        List<String[]> data = reader.readFile();

        String[] heading = data.get(0);


        for (int i = 0; i < heading.length; i++) {

            heading[i] = heading[i].trim();


        }

        if (keyYearMonth == null) {
            this.keyYearMonth = "";
        }

        int indexKeyWord = 0;
        int indexdate = 0;
        int indexCountry = 0;

        for (int a = 0; a < heading.length; a++) {

            if (heading[a].equals(keyWord)) {
                indexKeyWord = a;
            }
            if (heading[a].equals("Date_reported")) {
                indexdate = a;
            }
            if (heading[a].equals("Country")) {
                indexCountry = a;
            }
        }

        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        String id = "2020-01";
        this.keyYearMonth = id;
        String dateMonth = "01";
        String days = "01";
        for (int i = 1; i < data.size(); i++) {


            if (data.get(i)[indexCountry].equals(keyCountry)) {
                String[] temp = data.get(i);
                String date = temp[indexdate];
                String dataDay = date.substring(date.length() - 2);

                if (data.get(i)[indexdate].substring(5, 7).equals(dateMonth)) {
                    if (!(days.equals(dataDay))) {

                        days = dataDay;
                    }
                    dataset1.addValue(Double.parseDouble(temp[indexKeyWord]),
                            "deaths", days);


                } else {

                    saveChart(dataset1, id);
                    dataset1.clear();
                    id = date.substring(0, date.length() - 3);
                    dateMonth = data.get(i)[indexdate].substring(5, 7);
                    days = "01";
                    this.keyYearMonth = id;
                    dataset1.addValue(Double.parseDouble(temp[indexKeyWord]),
                            "deaths", days);
                }


            }


        }
        saveChart(dataset1, id);

    }


}


