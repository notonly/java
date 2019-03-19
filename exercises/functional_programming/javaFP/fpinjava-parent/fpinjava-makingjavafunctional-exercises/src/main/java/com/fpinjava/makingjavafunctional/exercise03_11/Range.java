package com.fpinjava.makingjavafunctional.exercise03_11;

import com.fpinjava.makingjavafunctional.exercise03_10.CollectionUtilities;

import java.util.ArrayList;
import java.util.List;


public class Range {

  public static List<Integer> range(int start, int end) {
    if (start > end) {
      return CollectionUtilities.list();
    }

    List<Integer> result = new ArrayList<>(end - start + 1);

    int idx = start;
    while (idx < end) {
      CollectionUtilities.append(result, idx);

      idx++;
    }

    return result;
  }
}
