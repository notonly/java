package com.fpinjava.functions.exercise02_08;

public class FunctionExamples {

  public static <A, B, C> Function<A, C> partialB(B b, Function<A, Function<B, C>> f) {
    // throw new RuntimeException("To be implemented.");
    return a -> f.apply(a).apply(b);
  }
}
