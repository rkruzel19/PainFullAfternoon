package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {


    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawData);
        return response;
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException {
        String name = null;
        Double price = null;
        String type = null;
        String expiration = null;

        String nameRegex = "[nN]..[eE].*";
        String priceRegex = ".*[pP]...e.*";
        String typeRegex = ".*type.*";
        String expirationRegex = ".*expiration.*";

        ArrayList<String> itemDetails = findPairsInItemString(rawItem);
        for (String detail : itemDetails){
            if (detail.matches(nameRegex)){
                name = this.parseName(detail);
            }
            if (detail.matches(priceRegex)){
                price = this.parsePrice(detail);
            }
            if (detail.matches(typeRegex)){
                type = this.parseType(detail);
            }
            if (detail.matches(expirationRegex)){
                expiration = this.parseExpiration(detail);
            }
        }

        if (name == null || price == null || type == null || expiration == null){
            throw new ItemParseException();
            //return null;
        } else {
            return new Item(name, price, type, expiration);
        }
    }

    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[;|^]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawItem);
        return response;
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }

    public ArrayList<String> findPairsInItemString(String item){
        String stringPattern = "[;%!@^*]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern, item);
        return response;
    }

    public String parseName(String rawItem){
        String cookieRegEx = ".*[cC]..k.e[sS].*";
        String breadRegEx = ".*Br..D.*";
        String applesRegEx = ".*apPles.*";
        String milkRegEx = ".*Mi.[kK].*";

        String name = null;
        if(rawItem.matches(cookieRegEx)){
            name = "Cookies";
        }
        if(rawItem.matches(milkRegEx)){
            name = "Milk";
        }
        if(rawItem.matches(breadRegEx)){
            name = "Bread";
        }
        if(rawItem.matches(applesRegEx)){
            name = "Apples";
        }
        return name;
    }

    public Double parsePrice(String rawItem){
        String priceRegEx = "\\d\\.\\d+";
        Double price = null;
        Pattern pattern = Pattern.compile(priceRegEx);
        Matcher matcher = pattern.matcher(rawItem);
        if (matcher.find()){
            price = Double.parseDouble(matcher.group(0));
        }
        return price;
    }

    public String parseType(String rawItem){
        String typeRegEx = ".*type.*";
        String type = null;

        if (rawItem.matches(typeRegEx)){
            type = "Food";
        }
        return type;
    }

    public String parseExpiration(String rawItem){

        String expirationRegEx = "\\d/\\d+/\\d+";
        String expiration = null;
        Pattern pattern = Pattern.compile(expirationRegEx);
        Matcher matcher = pattern.matcher(rawItem);
        if (matcher.find()){
            expiration = matcher.group(0);
        }
        return expiration;
    }



}
