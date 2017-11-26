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


        for (String item : arrayListOfStringItems) {
            try {
                Item newItem = itemParser.parseStringIntoItem(item);
                items.add(newItem);
            } catch (ItemParseException ipe){
                numberOfErrors++;
            }
        }

        System.out.println(generateEachItemArray(items));


    }

    public static String generateEachItemArray(ArrayList<Item> items){

        ArrayList<ArrayList<Item>> allItems = new ArrayList<>();

        ArrayList<Item> milks = new ArrayList<>();
        ArrayList<Item> breads = new ArrayList<>();
        ArrayList<Item> apples = new ArrayList<>();
        ArrayList<Item> cookies = new ArrayList<>();

        String output = "";

        for (Item item : items){
            switch (item.getName()){
                case "Milk":
                    milks.add(item);
                    break;
                case "Bread":
                    breads.add(item);
                    break;
                case "Apples":
                    apples.add(item);
                    break;
                case "Cookies":
                    cookies.add(item);
                    break;
            }
        }

        allItems.add(apples);
        allItems.add(cookies);
        allItems.add(breads);
        allItems.add(milks);

        for (ArrayList<Item> eachItem : allItems){
            output += generateOutput(eachItem) + "\n";
            //System.out.println(temp);
        }

        return output;
    }

    public static String generateOutput(ArrayList<Item> items){
        String output = "name:   " + items.get(0).getName() + "       seen: " + items.size() + "times\n" +
                "================      =================\n";

        // find possible prices
        ArrayList<Double> prices = new ArrayList<>();
        for (Item item : items){
            if (!(prices.contains(item.getPrice()))){
                prices.add(item.getPrice());
            }
        }

        if (prices.size() == 1){
            output += "Price:  " + prices.get(0) + "         seen: " + items.size() + "\n" +
                    "---------------        --------------\n";
        } else {
            for (Double price : prices){

            }
        }

        for (Item item : items){

        }
        return output;
    }
}
