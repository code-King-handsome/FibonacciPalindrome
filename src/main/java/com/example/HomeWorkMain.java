package com.example;

import com.example.model.Pair;
import com.example.service.FibonacciPalindrome;
import com.example.service.FibonacciPalindromeImpl;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeWorkMain {
  private static final Logger log = LoggerFactory.getLogger(HomeWorkMain.class);

  public static void main(String[] args) {
    FibonacciPalindrome fibonacciPalindrome = new FibonacciPalindromeImpl();
    List<Integer> input;
    if (args.length == 0) {
      input = Arrays.asList(0, 1, 2, 3, 2, 1, 4, 1, 2, 1, 2, 1, 2, 1);
    } else {
      input =
          Arrays.stream(args[0].split(","))
              .mapToInt(Integer::parseInt)
              .boxed()
              .collect(Collectors.toList());
    }
    log.info("The input list is {}", convertListToString(input));
    Pair<Integer, Integer> pair = fibonacciPalindrome.findFibonacciPalindrome(input);
    int start = pair.getFirst();
    int pairLen = pair.getSecond();
    log.info("result pair first: {}, second: {}", start, pairLen);
    log.info("result list is {}", convertListToString(input.subList(start, start + pairLen - 1)));
  }

  private static String convertListToString(List<Integer> list) {
    return list.stream().map(String::valueOf).collect(Collectors.joining(","));
  }
}
