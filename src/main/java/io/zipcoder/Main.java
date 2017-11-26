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

        System.out.println(generateOutput(items));



        //System.out.println(prices);
        //System.out.println(numberOfApples + " " + numberOfBread + " " + numberOfCookies + " " + numberOfMilk);

//        // prints entire Item list
//        int itemNumber = 1;
//        for (Item item : items){
//            System.out.println(itemNumber + " " + item);
//            itemNumber++;
//        }

        //System.out.println(numberOfErrors);


    }

    public static String generateOutput(ArrayList<Item> items){
        int numberOfBread = 0;
        int numberOfMilk = 0;
        int numberOfApples = 0;
        int numberOfCookies = 0;
        ArrayList<Double> milkPrices = new ArrayList<>();
        ArrayList<Double> breadPrices = new ArrayList<>();
        ArrayList<Double> applePrices = new ArrayList<>();
        ArrayList<Double> cookiePrices = new ArrayList<>();
        ArrayList<String> typesOfFood = new ArrayList<>();

        for (Item item : items){
            switch (item.getName()){
                case "Milk":
                    if (!(milkPrices.contains(item.getPrice()))){
                        milkPrices.add(item.getPrice());
                    }
                    numberOfMilk++;
                    break;
                case "Bread":
                    if (!(breadPrices.contains(item.getPrice()))){
                        breadPrices.add(item.getPrice());
                    }
                    numberOfBread++;
                    break;
                case "Apples":
                    if (!(applePrices.contains(item.getPrice()))){
                        applePrices.add(item.getPrice());
                    }
                    numberOfApples++;
                    break;
                case "Cookies":
                    if (!(cookiePrices.contains(item.getPrice()))){
                        cookiePrices.add(item.getPrice());
                    }
                    numberOfCookies++;
                    break;
            }
            if (!(typesOfFood.contains(item.getName()))){
                typesOfFood.add(item.getName());
            }
            //System.out.println(item.getName() + " = $" + item.getPrice());
        }
        System.out.println(typesOfFood);
        return "testing";
    }
}
