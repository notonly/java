package com.fpinjava.functions.exercise02_06;


public interface Function<T, U> {

  U apply(T arg);

  static <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherCompose() {
    // return f -> g -> x -> f.apply(g.apply(x));   // works, but going to specify the types

    return (Function<U, V> f) -> (Function<T, U> g) -> (T x) -> f.apply(g.apply(x));
  }

  // Note the Type ordering for arguments of passed-in functions,  are different from those of "higherCompose()"
  static <T, U, V> Function<Function<T, U>, Function<Function<U, V>, Function<T, V>>> higherAndThen() {
    //throw new RuntimeException("To be implemented.");
    // return f -> g -> x -> g.apply(f.apply(x));   // should work, but going to specify the types

    return (Function<T, U> f) -> (Function<U, V> g) -> (T x) -> g.apply(f.apply(x));
  }
}
