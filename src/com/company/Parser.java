package com.company;

import java.util.List;

public class Parser {


    private List<String[]> stringData;

    Parser(List<String[]> stringData) {
        this.stringData = stringData;

    }

    public void setData(List<String[]> array) {
        this.stringData = array;
    }


    public ParsedData calculateAllDeaths() {

        String[] heading = stringData.get(0);


        int index = 0;
        int deaths = 0;


        for (int i = 0; i < heading.length; i++) {

            if (heading[i].equalsIgnoreCase("deaths") ||
                    heading[i].equalsIgnoreCase("death")) {
                index = i;
            }

        }

        if (!(heading[index].equalsIgnoreCase("deaths"))
        ) {
            if (!(heading[index].equalsIgnoreCase("death"))) {

                System.out.println("Keyword death or deaths not found.\n" +
                        "Check file.");

                System.exit(1);
            }

        }


        for (int x = 1; x < stringData.size(); x++) {

            String temp = stringData.get(x)[index];

            deaths += Integer.parseInt(temp);
        }

        ParsedData parsedData = new ParsedData();
        parsedData.setAllDeaths(deaths);
        return parsedData;

    }


}
