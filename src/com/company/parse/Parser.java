package com.company.parse;

import java.util.List;

public class Parser {

    private String keyword = "total_deaths";
    private List<String[]> stringData;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Parser(List<String[]> stringData) {
        this.stringData = stringData;

    }


    public ParsedData calculateAllDeaths() {

        String[] heading = stringData.get(0);


        int index = 0;
        Long deaths = 0L;


        for (int i = 0; i < heading.length; i++) {

            if (heading[i].equalsIgnoreCase(keyword) ||
                    heading[i].equalsIgnoreCase(keyword + "s")) {
                index = i;
            }

        }

        if (!(heading[index].equalsIgnoreCase(keyword))
        ) {
            if (!(heading[index].equalsIgnoreCase(keyword + "s"))) {

                System.out.println("Keyword not found.\n" +
                        "Or wrong separator!\n" +
                        "Check file.");

                System.exit(0);
            }

        }


        for (int x = 1; x < stringData.size(); x++) {

            String temp = stringData.get(x)[index];
            if (temp.equals("")) {
                continue;
            }

            double d = Double.parseDouble(temp);
            Long a = (long) d;
            deaths += a;
        }

        ParsedData parsedData = new ParsedData();
        parsedData.setAllDeaths(deaths);
        return parsedData;

    }


}
