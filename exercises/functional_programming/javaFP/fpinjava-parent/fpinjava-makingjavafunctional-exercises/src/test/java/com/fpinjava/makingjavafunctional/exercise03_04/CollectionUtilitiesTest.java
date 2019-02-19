package com.fpinjava.makingjavafunctional.exercise03_04;

import static com.fpinjava.makingjavafunctional.exercise03_03.CollectionUtilities.list;
import static com.fpinjava.makingjavafunctional.exercise03_04.CollectionUtilities.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Ignore;
import org.junit.Test;

public class CollectionUtilitiesTest {

  /**
   * same as tail(Collections.emptyList()),
   * head(Collections.
   */
  @Ignore
  @Test(expected=IllegalStateException.class)
  public void testHeadEmpty() {
    List<String> list = list();
    head(list);
  }

  @Test
  public void headEmpty() {
    MatcherAssert.assertThat(head(Collections.<String>emptyList()).isPresent(), Is.is(false));
  }

  @Test
  public void testHead() {
    List<String> list = list("1");
    assertEquals("1", head(list).orElse(""));
    List<String> list2 = list("1", "2", "3", "4");
    assertEquals("1", head(list2).orElse(""));
  }

  /**
   * I have updated "tail" method to return Optional
   * hence, the tail(Collections.emptyList())  returns Optional.empty(),  will not have Exception
   */
  @Ignore
  @Test(expected=IllegalStateException.class)
  public void testTailEmpty() {
    List<String> list = list();
    tail(list);
  }

  @Test
  public void tailEmpty() {
    MatcherAssert.assertThat(tail(Collections.<String>emptyList()).isPresent(), Is.is(false));
  }

  @Test
  public void testTail() {
    List<String> list0 = list("1", "2", "3", "4");
    Optional<List<String>> list = tail(list0);
    assertEquals(3, list.orElse(Collections.emptyList()).size());
    assertEquals("2", list.orElse(Collections.emptyList()).get(0));
    assertEquals("3", list.orElse(Collections.emptyList()).get(1));
    assertEquals("4", list.orElse(Collections.emptyList()).get(2));
  }

  @Test
  public void tailListElementsStillModifiable() {
    List<StringBuilder> orig =  list(new StringBuilder("a"), new StringBuilder("b"), new StringBuilder("c"));
    Optional<List<StringBuilder>> tailLst = tail(orig);
    tailLst.ifPresent(l -> System.out.println("before changing tailLst.get(1) " + l.get(1).toString()));

    // Now updating tailLst element at position 1
    tailLst.ifPresent(l -> l.get(1).append("_UPDATED"));

    tailLst.ifPresent(l -> System.out.println("before changing tailLst.get(1) " + l.get(1).toString()));

    tailLst.ifPresent(l -> MatcherAssert.assertThat(l.get(1).toString().contains("_UPDATED"), Is.is(true)));
  }

  @Test
  public void createListFromVarargThatNull() {
    List<String> created = list(null, null);
    System.out.println(created);
    List<String> expected = new ArrayList<>();
    expected.add(null);
    expected.add(null);
    MatcherAssert.assertThat(created, Is.is(expected));
  }

}
