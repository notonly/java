package com.fpinjava.makingjavafunctional.exercise03_08;

import static org.junit.Assert.*;
import static com.fpinjava.makingjavafunctional.exercise03_08.CollectionUtilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import com.fpinjava.common.Function;

public class CollectionUtilitiesTest {

  /**
   * Formula expression, the naming "IS" for Integer-String, not clear ...
   *
   * @param i
   * @param s
   * @return
   */
  private static String addIS(Integer i, String s) {
    return "(" + i + " + " + s + ")";
  }

  @Test
  public void recursiveFoldRightBookVersion() {
    List<Integer> list = list(1, 2, 3, 4, 5);
    String identity = "0";
    Function<Integer, Function<String, String>> f = x -> y -> addIS(x, y);
    assertEquals("(1 + (2 + (3 + (4 + (5 + 0)))))", foldRightBookVersion(list, identity, f));
  }

  @Test(expected = StackOverflowError.class)
  public void recursiveFoldRightBookVersionThatOverflowStack() {
    List<Integer> lst = new ArrayList<>(8_000);

    IntStream.rangeClosed(1, 8_000).forEach(i -> lst.add(i));

    String identity = "0";

    Function<Integer, Function<String, String>> f = integer -> String -> addIS(integer, String);

    System.out.println(foldRightBookVersion(lst, identity, f));
  }

  @Test
  public void recursiveFoldRight() {
    List<Integer> list = list(1, 2, 3, 4, 5);
    String identity = "0";
    Function<Integer, Function<String, String>> f = x -> y -> addIS(x, y);
    assertEquals("(1 + (2 + (3 + (4 + (5 + 0)))))", foldRightRecursively(list, identity, f));
  }

  @Test(expected = StackOverflowError.class)
  public void recursiveFoldRightThatOverflowStack() {
    List<Integer> lst = new ArrayList<>(8_000);

    IntStream.rangeClosed(1, 8_000).forEach(i -> lst.add(i));

    String identity = "0";

    Function<Integer, Function<String, String>> f = integer -> String -> addIS(integer, String);

    System.out.println(foldRightRecursively(lst, identity, f));
  }

}
