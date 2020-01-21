package com.example.service;

import com.example.model.Pair;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FibonacciPalindromeImplTest {

  private FibonacciPalindromeImpl fibonacciPalindrome;

  @BeforeTest
  public void setUp() {
    fibonacciPalindrome = new FibonacciPalindromeImpl();
  }

  @Test(
      dataProvider = "getInvalidInput",
      expectedExceptions = RuntimeException.class,
      expectedExceptionsMessageRegExp = "The sequence length is too short to find a pair")
  public void testFindFibonacciPalindromeWithInvalidInput(List<Integer> list) {
    fibonacciPalindrome.findFibonacciPalindrome(list);
  }

  @DataProvider
  public Object[][] getInvalidInput() {
    return new Object[][] {{null}, {Collections.emptyList()}, {Collections.singletonList(1)}};
  }

  @Test(dataProvider = "getMismatchInput")
  public void testFindFibonacciPalindromeWithMismatchInput(List<Integer> list) {
    Assert.assertNull(fibonacciPalindrome.findFibonacciPalindrome(list));
  }

  @DataProvider
  public Object[][] getMismatchInput() {
    return new Object[][] {
      {Arrays.asList(1, 2)}, {Arrays.asList(1, 2, 3)}, {Arrays.asList(1, 3, 5, 1)}
    };
  }

  @Test(dataProvider = "getInput")
  public void testFindFibonacciPalindrome(List<Integer> list, Integer first, Integer second) {
    Pair<Integer, Integer> pair = fibonacciPalindrome.findFibonacciPalindrome(list);
    Assert.assertNotNull(pair);
    Assert.assertEquals(first, pair.getFirst());
    Assert.assertEquals(second, pair.getSecond());
  }

  @DataProvider
  public Object[][] getInput() {
    return new Object[][] {
      {Arrays.asList(1, 1), 0, 2},
      {Arrays.asList(1, 2, 1), 0, 3},
      {Arrays.asList(0, 1, 2, 3, 2, 1), 1, 5},
      {Arrays.asList(0, 1, 2, 3, 2, 1, 1, 4, 1, 2, 1, 2, 1), 1, 5},
      {Arrays.asList(0, 1, 2, 3, 2, 1, 4, 1, 2, 1, 2, 1, 2, 1), 7, 7},
    };
  }
}
