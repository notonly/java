package com.fpinjava.makingjavafunctional.exercise03_05;

import com.fpinjava.common.Function;

import java.util.List;

public class Fold {

  public static Integer fold(List<Integer> ilst, Integer identity,
                             Function<Integer, Function<Integer, Integer>> f) {
    Integer rslt = identity;

    for(Integer i : ilst) {
      rslt = f.apply(i).apply(rslt);
    }

    return rslt;
  }

}
