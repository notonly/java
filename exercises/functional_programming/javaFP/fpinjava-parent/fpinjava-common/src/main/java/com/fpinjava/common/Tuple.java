package com.fpinjava.common;

import java.util.Objects;
import java.util.Optional;


public class Tuple<T, U> {

  public final T _1;
  public final U _2;

  /**
   * constructor throws NPE, need to test, if NPE thrown, is the object instantiated?
   *
   * This may need improved, since Java 8, prefer "Optional" to "null";  Java 9 has more improvemtn on "Optional"
   *
   * ^^ If not use NPE (, and then, what other ways
   *
   * @param t
   * @param u
   */
  public Tuple(T t, U u) {
    this._1 = Objects.requireNonNull(t);
    this._2 = Objects.requireNonNull(u);
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", _1,  _2);
  }

  @Override
  public boolean equals(Object o) {

    // no need to check null, because constructor already verifies it.

    if (!(o.getClass() == this.getClass()))
      return false;
    else {
      @SuppressWarnings("rawtypes")
      Tuple that = (Tuple) o;
      return _1.equals(that._1) && _2.equals(that._2);
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _1.hashCode();
    result = prime * result + _2.hashCode();
    return result;
  }

  /**
   * not changing this object, creating a new object.
   *
   * @return
   */
  public Tuple<U, T> swap() {
    return new Tuple<>(_2, _1);
  }

  // The author created this swapIf, but never used anywhere.
  // this design requires same type in the Tuple;  but Tuple<T, U> allows two types.
  public static <T> Tuple<T, T> swapIf(Tuple<T, T> t, Function<T, Function<T, Boolean>> p) {

    //  a little tricky stuff here,  t.swap() returns a new Tuple (a new swapped Tuple of the passed-in t)
    //  but, if not swapped, the passed in parameter t
    return p.apply(t._1).apply(t._2) ? t.swap() : t;
  }

  /**
   * This can be a improvement for the original swapIf which requires uniform-type in Tuple, due to the delimar
   * of if swapped, vs if not-swapped,  the parameter can be re-odered
   * <p></p>
   * By using Optional&lt;Tuple&lt;U, T&gt;&gt;, we solved two things:
   * 1) allowing two-types in the swapped Tuple
   * 2) If not need to swap,  we just returns Optional.empty(), instead of origin Tuple.
   *
   * When using optionalSwapIf,  clients will check if Optional.ifPresent;  if there is a value, it means swapped,
   * otherwise, no swapping was done.
   *
   * @param t
   * @param predicateFunction
   * @param <T>
   * @param <U>
   * @return
   */
  public static <T, U> Optional<Tuple<U, T>> optionalSwapIf(Tuple<T, U> t,
                                                            Function<T, Function<U, Boolean>> predicateFunction) {
    return predicateFunction.apply(t._1).apply(t._2) ? Optional.ofNullable(t.swap()) : Optional.empty();
  }

  /**
   * This improves on the above optionalSwapIf, because
   *
   * 1) not static method
   * 2) just requires Function&lt;T, Function&lt;U, Boolean&gt;&gt;
   *
   *
   * @param predicateFunction
   * @return
   */
  public Optional<Tuple<U, T>> optionalSwapIf(Function<T, Function<U, Boolean>> predicateFunction) {
    return predicateFunction.apply(_1).apply(_2) ? Optional.of(swap()) : Optional.empty();
  }

}
