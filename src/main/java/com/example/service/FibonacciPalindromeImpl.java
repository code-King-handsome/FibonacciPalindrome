package com.example.service;

import com.example.model.IntIntPairImpl;
import com.example.model.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * fibonacci check has less computational complexity than palindrome check, so first call fibonacci
 * check use dynamic programming to reduce duplicate compute
 */
public class FibonacciPalindromeImpl implements FibonacciPalindrome {

  private int[] array;
  private int len;
  private CheckState[] fibonacciStates;
  private Map<Integer, CheckState[]> palindromeStates;

  @Override
  public Pair<Integer, Integer> findFibonacciPalindrome(List<Integer> sequence) {
    if (sequence == null || sequence.isEmpty() || sequence.size() == 1) {
      throw new RuntimeException("The sequence length is too short to find a pair");
    }
    initialize(sequence);

    for (int pairLen = len; pairLen > 1; pairLen--) {
      for (int i = 0; i <= len - pairLen; i++) {
        IntIntPairImpl pair = new IntIntPairImpl(i, pairLen);
        if (validate(pair)) {
          return pair;
        }
      }
    }

    return null;
  }

  private void initialize(List<Integer> sequence) {
    len = sequence.size();
    array = sequence.stream().mapToInt(i -> i).toArray();

    palindromeStates = new HashMap<>(len - 1);
    for (int pairLen = 2; pairLen <= len; pairLen++) {
      CheckState[] checkStates =
          IntStream.range(0, len - pairLen + 1)
              .mapToObj(i -> CheckState.NOT_CALCULATE)
              .toArray(CheckState[]::new);
      palindromeStates.put(pairLen, checkStates);
    }

    if (len > 2) {
      fibonacciStates = new CheckState[len - 2];
      for (int i = 0; i < len - 2; i++) {
        fibonacciStates[i] = CheckState.NOT_CALCULATE;
      }
    }
  }

  private boolean validate(IntIntPairImpl pair) {
    return isFibonacci(pair) && isPalindrome(pair);
  }

  private boolean isFibonacci(IntIntPairImpl pair) {
    int pairLen = pair.getSecond();
    int start = pair.getFirst();
    if (pairLen < 2) {
      throw new RuntimeException(
          String.format("Pair's length(%s) is too short to calculate fibonacci", len));
    } else if (pairLen == 2) {
      return true;
    } else if (pairLen == 3) {
      if (fibonacciStates[start] == CheckState.NOT_CALCULATE) {
        int a = array[start];
        int b = array[start + 1];
        int c = array[start + 2];
        fibonacciStates[start] =
            (a == c) || (a + b == c) || (a == b + c) ? CheckState.TRUE : CheckState.FALSE;
      }
      return fibonacciStates[start].flag;
    } else {
      return IntStream.range(0, pairLen - 2)
          .mapToObj(i -> isFibonacci(new IntIntPairImpl(start + i, 3)))
          .allMatch(Boolean::booleanValue);
    }
  }

  private boolean isPalindrome(IntIntPairImpl pair) {
    int pairLen = pair.getSecond();
    int start = pair.getFirst();
    if (pairLen < 2) {
      throw new RuntimeException(
          String.format("Pair's length(%s) is too short to calculate palindrome", len));
    }
    CheckState[] checkStates = palindromeStates.get(pairLen);
    if (checkStates[start] == CheckState.NOT_CALCULATE) {
      if (pairLen == 2 || pairLen == 3) {
        checkStates[start] =
            (array[start] == array[start + pairLen - 1]) ? CheckState.TRUE : CheckState.FALSE;
      } else {
        checkStates[start] =
            ((array[start] == array[start + pairLen - 1])
                    && (isPalindrome(new IntIntPairImpl(start + 1, pairLen - 2))))
                ? CheckState.TRUE
                : CheckState.FALSE;
      }
    }
    return checkStates[start].flag;
  }

  enum CheckState {
    NOT_CALCULATE(false),
    TRUE(true),
    FALSE(false);

    private boolean flag;

    CheckState(boolean flag) {
      this.flag = flag;
    }
  }
}
