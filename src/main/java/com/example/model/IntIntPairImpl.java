package com.example.model;

public class IntIntPairImpl implements Pair<Integer, Integer> {

  private Integer first;
  private Integer second;

  public IntIntPairImpl(Integer first, Integer second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public Integer getFirst() {
    return first;
  }

  @Override
  public Integer getSecond() {
    return second;
  }
}
