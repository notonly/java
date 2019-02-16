package com.fpinjava.functions.exercise02_01;

public class FunctionExamples {

  public static final Function<Integer, Integer> triple = new Function<Integer, Integer>() {

    @Override
    public Integer apply(Integer arg) {
      return arg * 3;
    }
  };

  public static final Function<Integer, Integer> square = new Function<Integer, Integer>() {

    @Override
    public Integer apply(Integer arg) {
      return arg * arg;
    }
  };

  public static final Function<Integer, Integer> compose(final Function<Integer, Integer> f1,
                                                         final Function<Integer, Integer> f2) {
    // throw new RuntimeException("To be implemented.");
    return x -> f1.apply(f2.apply(x));
  }

  public static final Function<Integer, Integer> composeUsingAnonymousClass(
          final Function<Integer, Integer> f1, final Function<Integer, Integer> f2) {
    return new Function<Integer, Integer>() {
      @Override
      public Integer apply(Integer arg) {
        return f1.apply(f2.apply(arg));
      }
    };
  }

}
