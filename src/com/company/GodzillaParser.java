package com.company;

import java.util.List;

public class GodzillaParser {


    List<String[]> stringData;

    GodzillaParser(List<String[]> stringData) {
        this.stringData = stringData;
    }

    public void printDeaths() {

        String[] heading = stringData.get(0);
        int index = 0;
        int deaths = 0;

        for (int i = 0; i < heading.length; i++) {

            if (heading[i].equals("deaths")) {
                index = i;
            }

        }

        for (int x = 1; x < stringData.size(); x++) {

            String temp = stringData.get(x)[index];

            deaths += Integer.parseInt(temp);
        }

        System.out.println(deaths);
    }


}
