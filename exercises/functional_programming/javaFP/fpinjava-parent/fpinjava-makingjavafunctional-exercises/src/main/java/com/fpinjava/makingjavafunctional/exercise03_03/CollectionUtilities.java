package com.fpinjava.makingjavafunctional.exercise03_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class CollectionUtilities {

  public static <T> List<T > list() {
    // throw new RuntimeException("To be implemented");
    return new ArrayList<>();
  }

  public static <T> List<T > list(T t) {
    List<T> rslt = new ArrayList<>();
    rslt.add(t);
    return rslt;
  }
  public static <T> List<T > list(List<T> ts) {
    Objects.requireNonNull(ts);
    List<T> rslt = new ArrayList<>(ts.size());
    rslt.addAll(ts);
    return rslt;
  }

  @SafeVarargs
  public static <T> List<T > list(T... t) {
    Objects.requireNonNull(t);
    List<T> rslt = new ArrayList<>(t.length);
    rslt.addAll(Arrays.asList(t));
    return rslt;
  }
}
