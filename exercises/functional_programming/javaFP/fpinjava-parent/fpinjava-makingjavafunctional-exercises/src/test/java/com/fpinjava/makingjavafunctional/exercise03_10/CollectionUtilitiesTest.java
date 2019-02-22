package com.fpinjava.makingjavafunctional.exercise03_10;

import static org.junit.Assert.*;
import static com.fpinjava.makingjavafunctional.exercise03_10.CollectionUtilities.*;

import org.junit.Test;

public class CollectionUtilitiesTest {

  @Test
  public void testMapViaFoldLeft() {
    assertEquals("[1, 4, 9]", mapViaFoldLeft(list(1, 2, 3), x -> x * x).toString());
  }

  @Test
  public void testMapViaFoldRight() {
    assertEquals("[1, 4, 9]", mapViaFoldRight(list(1, 2, 3), x -> x * x).toString());
  }

  @Test
  public void testMapViaFoldLeftAutor() {
    assertEquals("[1, 4, 9]", mapFoldLeftAuthors(list(1, 2, 3), x -> x * x).toString());
  }

  @Test
  public void testMapViaFoldRightAuthor() {
    assertEquals("[1, 4, 9]", mapFoldRightAuthors(list(1, 2, 3), x -> x * x).toString());
  }

}
