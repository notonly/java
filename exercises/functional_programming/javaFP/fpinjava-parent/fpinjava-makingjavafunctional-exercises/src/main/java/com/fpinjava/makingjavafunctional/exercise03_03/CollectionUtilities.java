package com.fpinjava.makingjavafunctional.exercise03_03;

import java.util.*;


public class CollectionUtilities {

  public static <T> List<T > list() {
    return Collections.emptyList();
  }

  public static <T> List<T > list(T t) {
    // Objects.requireNonNull(t);
    // Java List allows null value
    return Collections.singletonList(t);
  }
  public static <T> List<T > list(List<T> ts) {
    // it may be redundent, Collections.unmodifiableList will check "null" as well.
    Objects.requireNonNull(ts);

    // Java Collections.unmodifiableList is for the "List",  not to modify (add/delete),  can only read elements
    // but, the elements still may be modified,  depends on if the elements are of "final" or not
    return Collections.unmodifiableList(ts);
  }

  @SafeVarargs
  public static <T> List<T > list(T... t) {
    Objects.requireNonNull(t);

    // return Collections.unmodifiableList(Arrays.asList(t));  // the author used Arrays.copy on original varargs

    // why want to use Arrays.copyOf on the original varargs?  (so not to changes the elements of the original varargs
    // "pure function" requirements
    return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(t, t.length)));

  }
}
