package com.fpinjava.functions.exercise02_01;

import static com.fpinjava.functions.exercise02_01.FunctionExamples.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class FunctionExamplesTest {

  @Test
  public void testCompose() {
    assertEquals(Integer.valueOf(6), triple.apply(2));
    assertEquals(Integer.valueOf(4), square.apply(2));
    assertEquals(Integer.valueOf(36), square.apply(triple.apply(2)));
    assertEquals(Integer.valueOf(27), compose(triple, square).apply(3));
    MatcherAssert.assertThat(compose(square, triple).apply(2), is(36));
    MatcherAssert.assertThat(composeUsingAnonymousClass(square, triple).apply(2), is(36));
  }
}
