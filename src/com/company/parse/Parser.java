package com.company.parse;

import java.util.List;

public class Parser {


    private String[] checkHeading(List<String[]> data) {

        String[] heading = data.get(0);

        for (int i = 0; i < heading.length; i++) {

            heading[i] = heading[i].trim();

        }
        return heading;
    }


    public ParsedData calculate(List<String[]> data, String keyword) {

        String[] heading = checkHeading(data);

        int index = 0;
        long countKeyword = 0L;


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


        for (int x = 1; x < data.size(); x++) {

            String temp = data.get(x)[index];
            if (temp.equals("")) {
                continue;
            }

            double d = Double.parseDouble(temp);
            long a = (long) d;
            countKeyword += a;
        }

        ParsedData parsedData = new ParsedData();
        parsedData.setCount(countKeyword);
        return parsedData;

    }


}
