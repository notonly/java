package com.fpinjava.common;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class TupleTest {

    @Test( expected = NullPointerException.class )
    public void constructorThrowsNpe() {
        Tuple<Boolean, Integer> t = new Tuple<>(Boolean.TRUE, null);

        // not checked here, already exception.
        assertNull("constructor caught null parameter ", t);
    }

    @Test
    public void constructorThrowsNpe2() {
        Tuple<Boolean, Integer> t = null;
        try {
            t = new Tuple<>(Boolean.TRUE, null);
        } catch (NullPointerException ignored) {
        }

        assertNull("constructor caught null parameter ", t);
    }

    @Test
    public void swapIf() {
        Tuple<String, String> before = new Tuple<>("need swapping", "swapped");

        Function<String, Function<String, Boolean>> curried =
                u -> v -> u.contains("swapping") && v.contains("swapped");

        Tuple<String, String> after = Tuple.swapIf(before, curried);

        System.out.println("before swapping: " + before + "\n after swapping: " + after);
    }

    @Test
    public void swapIf2() {
        // The semantic of Tuple is outside the constructor,  Tuple just stores two types: T, U in such order
        // Tuple<T, U> has no way to know the semantic, in following example, (Age, Weight)
        // (Age, Weight)
        Tuple<Integer, Integer> before = new Tuple<>(35, 175);


        // saying, if older than 18 and weighs more than 150 lb;
        Function<Integer, Function<Integer, Boolean>> curried = a -> w -> a > 18 && w > 150;

        Tuple<Integer, Integer> after = Tuple.swapIf(before, curried);

        System.out.println("before swapping: " + before + "\n after swapping: " + after);
    }

    @Test
    public void optionalSwapIfThatSwapped() {
        // if some people weight more than 180 lb,  we are going to make weight in the first position
        // in the tuple to emphasize

        Tuple<String, Integer> nameWeight = new Tuple<>("John Doe", 375);

        // if people with surname Doe weigh more than 180 pounds?
        Function<String, Function<Integer, Boolean>> predictFunc = name -> weight -> name.contains("Doe") && weight > 180;

        Optional<Tuple<Integer, String>> weightName = Tuple.optionalSwapIf(nameWeight, predictFunc);

        weightName.ifPresent(t -> {
            MatcherAssert.assertThat(t._2, Is.is("John Doe"));
            MatcherAssert.assertThat(t._1, Is.is(375));
        });
    }

    @Test
    public void optionalSwapIfThatNoSwapping() {

        // if some people weight more than 180 lb,  we are going to make weight in the first position
        // in the tuple to emphasize

        Tuple<String, Integer> nameWeight = new Tuple<>("Jack Doe", 115);

        // if people with surname Doe weigh more than 180 pounds?
        Function<String, Function<Integer, Boolean>> predictFunc = name -> weight -> name.contains("Doe") && weight > 180;

        Optional<Tuple<Integer, String>> weightName = Tuple.optionalSwapIf(nameWeight, predictFunc);

        MatcherAssert.assertThat(weightName.isPresent(), Is.is(false));
    }

    @Test
    public void memberMethod_optionalSwapIfThatSwapped() {
        // if some people weight more than 180 lb,  we are going to make weight in the first position
        // in the tuple to emphasize

        Tuple<String, Integer> nameWeight = new Tuple<>("John Doe", 375);

        // if people with surname Doe weigh more than 180 pounds?
        Function<String, Function<Integer, Boolean>> predictFunc = name -> weight -> name.contains("Doe") && weight > 180;

        Optional<Tuple<Integer, String>> weightName = nameWeight.optionalSwapIf(nameWeight, predictFunc);

        weightName.ifPresent(t -> {
            MatcherAssert.assertThat(t._2, Is.is("John Doe"));
            MatcherAssert.assertThat(t._1, Is.is(375));
        });
    }

    @Test
    public void memberMethod_optionalSwapIfThatNotSwapped() {
        // if some people weight more than 180 lb,  we are going to make weight in the first position
        // in the tuple to emphasize

        Tuple<String, Integer> nameWeight = new Tuple<>("Jack Doe", 115);

        // if people with surname Doe weigh more than 180 pounds?
        Function<String, Function<Integer, Boolean>> predictFunc = name -> weight -> name.contains("Doe") && weight > 180;

        Optional<Tuple<Integer, String>> weightName = nameWeight.optionalSwapIf(nameWeight, predictFunc);
        
        MatcherAssert.assertThat(weightName.isPresent(), Is.is(false));
    }

}