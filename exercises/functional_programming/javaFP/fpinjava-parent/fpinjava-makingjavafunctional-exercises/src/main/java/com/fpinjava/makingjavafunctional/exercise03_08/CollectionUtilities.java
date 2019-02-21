package com.fpinjava.makingjavafunctional.exercise03_08;

import com.fpinjava.common.Function;

import java.util.*;

public class CollectionUtilities {

  public static <T> List<T > list() {
    return Collections.emptyList();
  }

  public static <T> List<T > list(T t) {
    return Collections.singletonList(t);
  }

  public static <T> List<T > list(List<T> ts) {
    return Collections.unmodifiableList(new ArrayList<>(ts));
  }

  @SafeVarargs
  public static <T> List<T > list(T... t) {
    return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(t, t.length)));
  }

  public static <T> T head(List<T> list) {
    if (list.size() == 0) {
      throw new IllegalStateException("head of empty list");
    }
    return list.get(0);
  }

  private static <T> List<T > copy(List<T> ts) {
    return new ArrayList<>(ts);
  }

  public static <T> List<T> tail(List<T> list) {
    if (list.size() == 0) {
      throw new IllegalStateException("tail of empty list");
    }
    List<T> workList = copy(list);
    workList.remove(0);
    return Collections.unmodifiableList(workList);
  }

  public static <T, U> U foldLeft(List<T> ts,
                                  U identity,
                                  Function<U, Function<T, U>> f) {
    U result = identity;
    for (T t : ts) {
      result = f.apply(result).apply(t);
    }
    return result;
  }

  public static <T, U> U foldRightRecursively(List<T> ts, U identity,
                                              Function<T, Function<U, U>> f) {
    // this throws NPE, not good functional programming
    // Objects.requireNonNull(ts);

    // terminator
    if (ts == null || ts.isEmpty()) {
      return identity;
    }

    // applying function on the list, from the last element
    U newIdentity = f.apply(ts.get(ts.size() - 1)).apply(identity);

    // the Optional here, need some
    List<T> withoutLastElement = new ArrayList<>(ts);
    withoutLastElement.remove(withoutLastElement.size() - 1);
    return foldRightRecursively(withoutLastElement, newIdentity, f);
  }

  public static <T, U> U foldRightBookVersion(List<T> ts, U identity, Function<T, Function<U, U>> f) {
    return (ts == null || ts.isEmpty())
            ? identity
            : f.apply(head(ts)).apply(foldRightBookVersion(tail(ts), identity, f));
  }

  public static <T> List<T> append(List<T> list, T t) {
    List<T> ts = copy(list);
    ts.add(t);
    return Collections.unmodifiableList(ts);
  }
}
