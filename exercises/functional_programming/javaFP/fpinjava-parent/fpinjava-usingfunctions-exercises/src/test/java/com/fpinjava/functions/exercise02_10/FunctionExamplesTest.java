package com.fpinjava.functions.exercise02_10;

import static org.junit.Assert.*;

import org.junit.Test;

public class FunctionExamplesTest {
  
  @Test
  public void testCurry() {

    // function from Tuple<Integer, Double> to Double
    Function<Tuple<Integer, Double>, Double> f = x -> x._1 * (1 + x._2 / 100);

    // function from Integer to Function<Double, Double>
    Function<Integer, Function<Double, Double>> g = FunctionExamples.curry(f);

    System.out.println("f.apply(new Tuple<>(89, 7.0)) == " + f.apply(new Tuple<>(89, 7.0)));
    
    assertEquals(f.apply(new Tuple<>(89, 7.0)), g.apply(89).apply(7.0));
    assertEquals(f.apply(new Tuple<>(27, 0.0)), g.apply(27).apply(0.0));
    assertEquals(f.apply(new Tuple<>(1623, 16.65)), g.apply(1623).apply(16.65));
  }

}
