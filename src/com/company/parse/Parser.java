package com.company.parse;

import java.util.List;

public class Parser {

    private String keyword = "new_deaths";
    private List<String[]> stringData;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Parser(List<String[]> stringData) {
        this.stringData = stringData;

    }


    public ParsedData calculate() {

        String[] heading = stringData.get(0);

        for (int i = 0; i < heading.length; i++) {

            heading[i] = heading[i].trim();

        }

        int index = 0;
        long deaths = 0L;


        for (int i = 0; i < heading.length; i++) {

            if (heading[i].equalsIgnoreCase(keyword)) {
                index = i;
            }

        }

        if (!(heading[index].equalsIgnoreCase(keyword))
        ) {


                System.out.println("Keyword not found.\n" +
                        "Or wrong separator!\n" +
                        "Check file.");

                System.exit(0);


        }


        for (int x = 1; x < stringData.size(); x++) {

            String temp = stringData.get(x)[index];
            if (temp.equals("")) {
                continue;
            }

            double d = Double.parseDouble(temp);
            long a = (long) d;
            deaths += a;
        }

        ParsedData parsedData = new ParsedData();
        parsedData.setCount(deaths);
        return parsedData;

    }


}
