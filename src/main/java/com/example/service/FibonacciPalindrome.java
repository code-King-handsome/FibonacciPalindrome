package com.example.service;

import com.example.model.Pair;
import java.util.List;

public interface FibonacciPalindrome {

  /**
   * Takes a non-empty sequence of unsigned integers and efficiently finds the longest Fibonacci
   * palindrome in that sequence.
   *
   * <p>If there are multiple Fibonacci palindromes of the largest length, finding any one of them
   * is sufficient.
   *
   * @param sequence A non-empty sequence of unsigned integers.
   * @return A Pair(startIndex, length), where startIndex is the index at which the largest
   *     Fibonacci Palindrome starts and length its length of the palindrome.
   */
  Pair findFibonacciPalindrome(List<Integer> sequence);
}
