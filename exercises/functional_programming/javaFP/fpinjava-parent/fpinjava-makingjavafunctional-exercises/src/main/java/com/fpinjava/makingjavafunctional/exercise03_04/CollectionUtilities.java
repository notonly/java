package com.fpinjava.makingjavafunctional.exercise03_04;

import com.fpinjava.common.Option;

import java.util.*;


public class CollectionUtilities {

  /**
   * I still think Collections.<T>empty()  is better, from the name per-se,
   * here list(), who knows it is "empty" ? <== not able to tell from name, neither from type
   *
   * @param <T>
   * @return
   */
  public static <T> List<T > list() {
    return Collections.emptyList();
  }

  public static <T> List<T > list(T t) {
    return Collections.singletonList(t);
  }

  public static <T> List<T > list(List<T> ts) {
    return Collections.unmodifiableList(new ArrayList<>(ts));
  }

  /**
   * Going to test the edge case, what if vararg is null?
   * (the Java vararg - array like stuff, cannot be null itself;
   *  of course its elements can be null)
   *
   * @param t
   * @param <T>
   * @return
   */
  @SafeVarargs
  public static <T> List<T > list(T... t) {
    return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(t, t.length)));
  }

  public static <T> Optional<T> head(List<T> list) {
    return (list == null || list.isEmpty()) ? Optional.empty() : Optional.ofNullable(list.get(0));
  }

  private static <T> Optional<List<T >> copy(List<T> ts) {
    // this is shallow-copy, the copied list still have same element-references
    return (ts == null) ? Optional.empty() : Optional.of(new ArrayList<>(ts));
  }

  /**
   * The copied tail list, only has read/retrieval methods, no add/delete methods
   * that is, Collections.unmodifiableList applied to the tail list return.
   *
   * But the elements are still modifiable;
   * if want to achieve unmodifiable elements, Arrays.copy will need to apply
   *
   * @param list
   * @param <T>
   * @return
   */
  public static <T> Optional<List<T>> tail(List<T> list) {
    if (list == null || list.isEmpty() || list.size() <= 1) {
      return Optional.empty();
    }
    List<T> copies = copy(list).orElse(Collections.emptyList());
    copies.remove(0);

    return Optional.of(Collections.unmodifiableList(copies));
  }

}
