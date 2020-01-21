package com.example.model;

public interface Pair<S, T> {

  /**
   * Returns the first element of the Pair.
   *
   * @return the first element of the Pair
   */
  S getFirst();

  /**
   * Returns the second element of the Pair.
   *
   * @return the second element of the Pair
   */
  T getSecond();
}
