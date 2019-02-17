package com.fpinjava.functions.exercise02_05;

public interface Function<T, U> {

  U apply(T arg);

  static <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherCompose() {
    //throw new RuntimeException("To be implemented.");


    //return f -> g -> x -> f.apply(g.apply(x));


    // the order is important
    // f : Function<U, V>
    // g : Function<T, U>
    // x : T


    // Better Type inference
    return (Function<U, V> f) -> (Function<T, U> g) -> (T x) -> f.apply(g.apply(x));


    // Calling this higherCompose()
    // (not compiling) Function.higherCompose().apply(x -> x * x).apply(x -> x * 3).apply(2);  //  (2 * 2) * 3 == 12
    //
    // need to specific the types on left-hand-side
    //
    // Function.<Integer, Integer, Integer>apply(x -> x * x).apply(x -> x * 3).apply(2);

  }
}
