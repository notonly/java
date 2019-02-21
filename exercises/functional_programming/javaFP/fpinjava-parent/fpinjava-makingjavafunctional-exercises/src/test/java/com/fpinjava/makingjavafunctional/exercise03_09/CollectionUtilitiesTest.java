package com.fpinjava.makingjavafunctional.exercise03_09;

import static org.junit.Assert.*;
import static com.fpinjava.makingjavafunctional.exercise03_09.CollectionUtilities.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CollectionUtilitiesTest {

  @Test
  public void testPrepend() {
    assertEquals("[0, 1, 2, 3]", prepend("0", list("1", "2", "3")).toString());
    assertEquals("[0]", prepend("0", list()).toString());
  }

  @Test
  public void testReverse() {
    assertEquals("[]", reverse(list()).toString());
    assertEquals("[1]", reverse(list(1)).toString());
    assertEquals("[3, 2, 1]", reverse(list(1, 2, 3)).toString());
    assertEquals("[6, 5, 4, 3, 2, 1]", reverse(list(1, 2, 3, 4, 5, 6)).toString());
  }

  @Test
  public void testReverseUsingPrependfoldLeft() {
    assertEquals("[]", reverseUsingPrependfoldLeft(list()).toString());
    assertEquals("[1]", reverseUsingPrependfoldLeft(list(1)).toString());
    assertEquals("[3, 2, 1]", reverseUsingPrependfoldLeft(list(1, 2, 3)).toString());
    assertEquals("[6, 5, 4, 3, 2, 1]", reverseUsingPrependfoldLeft(list(1, 2, 3, 4, 5, 6)).toString());
  }



  // the functional way for prepend
  @Test
  public void testPrependAuthors() {
    assertEquals("[0, 1, 2, 3]", prependAuthors("0", list("1", "2", "3")).toString());
    assertEquals("[0]", prependAuthors("0", list()).toString());
  }

  @Test
  public void testReverseAuthors() {
    assertEquals("[]", reverseAuthors(list()).toString());
    assertEquals("[1]", reverseAuthors(list(1)).toString());
    assertEquals("[3, 2, 1]", reverseAuthors(list(1, 2, 3)).toString());
    assertEquals("[6, 5, 4, 3, 2, 1]", reverseAuthors(list(1, 2, 3, 4, 5, 6)).toString());
  }

  @Test
  public void testReverseAuthorsThatOverflow() {
    List<Integer> ints = new ArrayList<>(8_000);
    IntStream.rangeClosed(1, 8_000).forEach(i -> ints.add(i));

    System.out.println(reverseAuthors(ints).toString());
  }


}
