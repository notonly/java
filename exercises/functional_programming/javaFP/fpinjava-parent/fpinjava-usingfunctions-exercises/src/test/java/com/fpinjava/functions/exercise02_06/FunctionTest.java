package com.fpinjava.functions.exercise02_06;

import static org.junit.Assert.*;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;

public class FunctionTest {

  public static final Function<Integer, Integer> triple = x -> x * 3;

  public static final Function<Integer, Integer> square = x -> x * x;

  @Test
  public void test() {
    assertEquals(Integer.valueOf(12),
//            Function.<Integer, Integer, Integer>higherAndThen().apply(square).apply(triple).apply(2));

            Function.<Integer, Integer, Integer>higherAndThen().apply(x -> x * x).apply(x -> x * 3).apply(2));
  }


  @Test
  public void testMixedTypes() {
    MatcherAssert.assertThat(
      Function.<Integer, Double, Double>higherAndThen().apply(x -> 1.0 * x * x).apply(x -> x * 3).apply(2),
            Is.is(Double.valueOf("12.0"))
    );
  }

}
