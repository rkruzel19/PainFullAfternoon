package io.zipcoder;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static org.junit.Assert.*;

public class ItemParserTest {

    private String rawSingleItem =    "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";

    private String rawSingleItemIrregularSeperatorSample = "naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##";

    private String rawBrokenSingleItem =    "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";

    private String rawMultipleItems = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##"
                                      +"naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##"
                                      +"NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##";
    private ItemParser itemParser;

    @Before
    public void setUp(){
        itemParser = new ItemParser();
    }

    @Test
    public void parseRawDataIntoStringArrayTest(){
        Integer expectedArraySize = 3;
        ArrayList<String> items = itemParser.parseRawDataIntoStringArray(rawMultipleItems);
        Integer actualArraySize = items.size();
        assertEquals(expectedArraySize, actualArraySize);
    }

//    @Test
//    public void parseStringIntoItemTest() throws ItemParseException{
//        Item expected = new Item("milk", 3.23, "food","1/25/2016");
//        Item actual = itemParser.parseStringIntoItem(rawSingleItem);
//        assertEquals(expected.toString(), actual.toString());
//    }

//    @Test(expected = ItemParseException.class)
//    public void parseBrokenStringIntoItemTest() throws ItemParseException{
//        itemParser.parseStringIntoItem(rawBrokenSingleItem);
//    }

    @Test
    public void findKeyValuePairsInRawItemDataTest(){
        Integer expected = 4;
        Integer actual = itemParser.findKeyValuePairsInRawItemData(rawSingleItem).size();
        assertEquals(expected, actual);
    }

    @Test
    public void findKeyValuePairsInRawItemDataTestIrregular(){
        Integer expected = 4;
        Integer actual = itemParser.findKeyValuePairsInRawItemData(rawSingleItemIrregularSeperatorSample).size();
        assertEquals(expected, actual);
    }

    @Test
    public void findPairsInItemStringTest(){

        ArrayList<String> expected = new ArrayList<String>();
        ArrayList<String> actual = itemParser.findPairsInItemString(rawSingleItem);

        assertEquals(expected, actual);
    }

    @Test
    public void myParseIntoItemTest() throws ItemParseException{
        String item = "nAme:COokieS;Price:3.23;type:Food;expiration:1/25/2016";
        //ArrayList<String> parsedString = itemParser.findPairsInItemString(item);

        Item expected = null;
        Item actual = itemParser.parseStringIntoItem(item);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseNameTest(){
        String item = "nAme:Mihk;price:3.23;type:Food;expiration:1/25/2016";

        String expected = "";
        String actual = itemParser.parseName(item);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseExpirationTest(){
        String item = "nAme:Mihk;price:3.23;type:Food;expiration:1/25/2016";

        String expected = "";
        String actual = itemParser.parseExpiration(item);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parsePriceTest(){
        String item = "nAme:Mihk;price:3.23;type:Food;expiration:1/25/2016";

        Double expected = 8.00;
        Double actual = itemParser.parsePrice(item);

        Assert.assertEquals(expected, actual, 0);
    }
}
