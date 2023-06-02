package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SortingApp {
    public static void main(String[] args) {
        int[] numbers = new int[args.length];

        IntStream.range(0,args.length)
                .forEach(it->numbers[it]=Integer.parseInt(args[it]));



        // Sort the numbers in ascending order
        Arrays.sort(numbers);


        // Print the sorted numbers
        System.out.println(Arrays.toString(numbers));

    }
}
