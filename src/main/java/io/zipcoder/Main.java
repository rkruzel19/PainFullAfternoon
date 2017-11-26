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


        ArrayList<ArrayList<String>> arrayListOfItems = new ArrayList<ArrayList<String>>();


        System.out.println(arrayListOfStringItems.get(0));

//        for (String item : arrayListOfStringItems){
//            ArrayList<String> itemDetails = itemParser.findPairsInItemString(item);
//            arrayListOfItems.add(itemDetails);
//        }
//
//        for (ArrayList<String> item : arrayListOfItems){
//            System.out.println(item);
//        }
    }
}
