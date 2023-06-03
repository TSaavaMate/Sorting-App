import org.example.SortingApp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SortingAppTest {

    @Test
    @DisplayName("throws IllegalArgException when null is passed")
    @Tag("IAE-Exception")
    public  void nullTest(){
        assertThrows(IllegalArgumentException.class,()->SortingApp.main(null));
    }
    @Test
    @DisplayName("throws IllegalArgException when empty array is passed")
    @Tag("IAE-Exception")
    public  void emptyTest(){
        assertThrows(IllegalArgumentException.class,()->SortingApp.main(new String[]{}));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "csvCases.csv")
    @DisplayName("throws IllegalArgException when at least one nonDigit is passed")
    @Tag("IEA-Exception")
    public void testNonDigit(String input){
        assertThrows(IllegalArgumentException.class,()->SortingApp.main(input.split(" ")));
    }


    @Test
    @DisplayName("testSingleValues")
    public  void SingleTest(){
        String[] expected=new String[]{"1"};
        String actual=outPutted(expected);


        assertEquals(Arrays.toString(expected),actual,"output must be same as inputted,expected is: "+ Arrays.toString(expected) +"and actual was: "+ actual);

    }
    @Test
    @DisplayName("testSameNumbers")
    @Tag("same")
    public  void testSames(){
        String[] expected=new String[]{"1","1","1","1"};

        assertEquals(Arrays.toString(expected),outPutted(expected),"expected was : "+Arrays.toString(expected)+"and outputted is " +outPutted(expected));
    }

    @ParameterizedTest
    @MethodSource("testSortedCases")
    @DisplayName("sorting Sorted Array")
    public void testSorted(String[] input){
        assertEquals(Arrays.toString(input),outPutted(input));
    }
    static Stream<Arguments> testSortedCases(){
        return Stream.of(
                Arguments.of((Object) new String[]{"1", "2", "3", "4", "5"}),
                Arguments.of((Object) new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}),
                Arguments.of((Object) new String[]{"10", "20", "30", "40", "50"}),
                Arguments.of((Object) new String[]{"50", "100", "150", "200"})

        );
    }


    @ParameterizedTest
    @MethodSource("testCasesForRegular")
    @DisplayName("testing regular sequences")
    public void testRegulars(String[] actual,String[] expected){

        assertEquals(Arrays.toString(expected),outPutted(actual),"expected was :"+Arrays.toString(expected)+"and actual is :"+outPutted(actual));

    }


    static Stream<Arguments> testCasesForRegular(){
        return Stream.of(
                Arguments.of(new String[]{"5", "4", "3", "2", "1"}, new String[]{"1", "2", "3", "4", "5"}),
                Arguments.of(new String[]{"100", "50", "200", "150"}, new String[]{"50", "100", "150", "200"}),
                Arguments.of(new String[]{"-1", "5", "2", "0", "-10"}, new String[]{"-10", "-1", "0", "2", "5"}),
                Arguments.of(new String[]{"9", "7", "2", "5", "1", "8", "3", "6", "4","-5"},
                        new String[]{"-5","1", "2", "3", "4", "5", "6", "7", "8", "9"}),
                Arguments.of(
                        new String[]{"13", "25", "8", "41", "7", "16", "35", "12", "29", "20", "11", "39", "4", "18", "32"},
                        new String[]{"4", "7", "8", "11", "12", "13", "16", "18", "20", "25", "29", "32", "35", "39", "41"}),
                Arguments.of(
                        new String[]{"-8", "5", "-3", "7", "-1", "4", "-6", "2", "-9", "3", "-5", "1", "-7", "6", "-2", "8", "9"},
                        new String[]{"-9", "-8", "-7", "-6", "-5", "-3", "-2", "-1", "1", "2", "3", "4", "5", "6", "7", "8", "9"})
        );
    }
    private String outPutted(String[] input){

        final ByteArrayOutputStream sink = new ByteArrayOutputStream();
        PrintStream controlledOut = new PrintStream(sink);
        PrintStream defaultOut = System.out;

        System.setOut(controlledOut);


        try {
            SortingApp.main(input);
            controlledOut.flush();
            return sink.toString().trim();

        } finally {
            System.setOut(defaultOut);
        }

    }

}
