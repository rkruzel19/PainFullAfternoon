package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;


public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        //System.out.println(output);
        // TODO: parse the data in output into items, and display to console.

        ItemParser itemParser = new ItemParser();
        ArrayList<String> arrayListOfStringItems = itemParser.parseRawDataIntoStringArray(output);

        ArrayList<Item> items = new ArrayList<Item>();
        int numberOfErrors = 0;
        int numberOfBread = 0;
        int numberOfMilk = 0;
        int numberOfApples = 0;
        int numberOfCookies = 0;

        for (String item : arrayListOfStringItems) {
            try {
                Item newItem = itemParser.parseStringIntoItem(item);
                items.add(newItem);
            } catch (ItemParseException ipe){
                numberOfErrors++;
            }
        }

        for (Item item : items){
            System.out.println(item.getName());
        }

//        // prints entire Item list
//        int itemNumber = 1;
//        for (Item item : items){
//            System.out.println(itemNumber + " " + item);
//            itemNumber++;
//        }

        //System.out.println(numberOfErrors);


    }
}
