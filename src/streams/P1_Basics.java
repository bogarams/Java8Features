package streams;

/*
Stream API [ package java.util.Stream ] supports bulk data operations.

Streams leave source collection intact i.e. operations on streams doesn't affect the collection from which streams are obtained.Streams can be
obtained from collections, FileInputStream and other sources like Random number generator.

This stream provides elegant lazy evaluation of expression, and it also support intermediate and terminal operations.Some of the most common
things we do with Streams are filtering a collection, applying map and reduce function on all elements of the collection and taking advantage
of lazy evaluation, built-in parallelism via parallelStream().

Streams support aggregate operations like distinct()??, avg(), count()?? etc..

*/

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class P1_Basics {
    public static void main(String... args){
        List<String> strList = Arrays.asList("abc","","bcd","","defg","jk");

        //Count the empty Strings
        long count = strList.stream().filter(x -> x.isEmpty()).count();
        //stream() returns a Stream object.
        // filter() accepts a Predicate functional interface java.util.function.Predicate. This has a method
        // "boolean	test(T t)" which evaluates given condition and returns true or false. filter() will return the elements for which condition is
        //evaluated to true.filter returns a stream of elements back.
        //count() will count the number of elements in the resulting stream provided to it. It is considered terminal operation here.
        System.out.println("Number of empty strings are: "+count);

        //Even after the above operation, the original still remains intact
        for(String str:strList) System.out.println(str);

        //Counting strings whose length is more than 3
        long num = strList.stream().filter(x -> x.length() > 3 ).count();
        System.out.println("Number of strings whose length is greater than 3 are: "+num);

        //Remove all empty Strings
        List<String> nonEmptyList = strList.stream().filter(x -> x.length()>0).collect(Collectors.toList());
        //Collectors is a static utility class that wraps filtered elements into a Set or List using toList or toSet functions.
        System.out.println("Non empty strings are: ");
        for(String str:nonEmptyList) System.out.println(str);

        List<Integer> numbers = Arrays.asList(1,2,2,3,4);
        //Square of each distinct number
        List<Integer> distinctSquares = numbers.stream().distinct().map(x -> x * x).collect(Collectors.toList());

        System.out.println("Square of each distinct number is: ");
        for(int n:distinctSquares) System.out.println(n);

        //to get aggregate statistics
        IntSummaryStatistics is = distinctSquares.stream().mapToInt(x -> x).summaryStatistics();
        //mapToInt() returns IntStream.
        //summaryStatistics() returns an object of IntSummaryStatistics which has aggregate methods like getMin(), getMax(),
        // getSum() or getAverage()

        System.out.println("Min:"+is.getMin()+" and Max:"+is.getMax());

    }
}
