package com.fpinjava.makingjavafunctional.exercise03_09;

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
    if (list == null || list.isEmpty()) {
      throw new IllegalStateException("head of empty list");
    }
    return list.get(0);
  }

  /**
   * shallow copy. Elements are still the same in the lists
   *
   * @param ts
   * @param <T>
   * @return
   */
  private static <T> List<T > copy(List<T> ts) {
    return new ArrayList<>(ts);
  }

  public static <T> List<T> tail(List<T> list) {
    if (list == null || list.isEmpty()) {
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

  /**
   * Recursively foldRight
   *
   * @param ts
   * @param identity
   * @param f
   * @param <T>
   * @param <U>
   * @return
   */
  public static <T, U> U foldRight(List<T> ts, U identity,
                                   Function<T, Function<U, U>> f) {
    return ts.isEmpty()
        ? identity
        : f.apply(head(ts)).apply(foldRight(tail(ts), identity, f));
  }

  public static <T> List<T> append(List<T> list, T t) {
    // the reason copying here, is to make unmodifiableList for the returned list
    List<T> ts = copy(list);
    ts.add(t);
    // making unmodifiableList to stick to the immutable.
    return Collections.unmodifiableList(ts);
  }

  public static <T> List<T> prepend(T t, List<T> list) {
    Objects.requireNonNull(list);

    List<T> result = new ArrayList<>(list.size() + 1);
    result.add(t);
    result.addAll(copy(list));

    return Collections.unmodifiableList(result);
  }

  /**
   * reverse a list without using loop
   *
   * @param list
   * @param <T>
   * @return
   */
  public static <T> List<T> reverse(List<T> list) {
    if (list == null || list.size() <= 1) {
      return list;
    }

    T headElem = head(list);
    List<T> tailList = tail(list);

    return append(reverse(tailList), headElem);
  }
}
