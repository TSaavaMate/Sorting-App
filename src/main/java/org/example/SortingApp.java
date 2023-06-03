package org.example;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;


import java.util.Arrays;
import java.util.stream.IntStream;

public class SortingApp {
    private static final Logger logger = LogManager.getLogger(SortingApp.class);
    private static final Marker WARNINGMARKER=MarkerManager.getMarker("Danger");


    public static void main(String[] args) {

        if (args==null || args.length==0 ) {
            logger.error(WARNINGMARKER,"Invalid input: no arguments provided");
            throw new IllegalArgumentException();
        }


        //parse the numbers to int from string and put in number array

        int[] numbers = new int[args.length];
        IntStream.range(0,args.length)
                .forEach(it->{
                    if (!args[it].matches("-?\\d+")) {
                        logger.error(WARNINGMARKER,"invalid nonDigit argument found");
                        throw new IllegalArgumentException();
                    }

                    numbers[it]=Integer.parseInt(args[it]);
                });


        Arrays.sort(numbers);


        System.out.println(Arrays.toString(numbers));

    }
}
