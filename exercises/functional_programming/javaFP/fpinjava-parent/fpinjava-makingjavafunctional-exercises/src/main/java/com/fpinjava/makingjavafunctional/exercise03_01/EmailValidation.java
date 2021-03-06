package com.fpinjava.makingjavafunctional.exercise03_01;


import java.util.regex.Pattern;

import com.fpinjava.common.Function;
import com.fpinjava.makingjavafunctional.exercise03_01.Effect;
import com.fpinjava.makingjavafunctional.exercise03_01.Result;

public class EmailValidation {

  static Pattern emailPattern =
      Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

  static Function<String, Result<String>> emailChecker = s -> {
    // throw new RuntimeException("To be implemented");
    if (s == null) {
      return Result.failure("null email address");
    } else if (s.length() == 0) {
      return Result.failure("blank email address");
    } else if (emailPattern.matcher(s).matches()) {
      return Result.success("email sent to " + s);
    } else {
      return Result.failure("fail to send email to " + s);
    }
  };

  public static void main(String... args) {
    emailChecker.apply("this.is@my.email").bind(success, failure);
    emailChecker.apply(null).bind(success, failure);
    emailChecker.apply("").bind(success, failure);
    emailChecker.apply("john.doe@acme.com").bind(success, failure);
  }

  static Effect<String> success = s -> System.out.println("Success: email was sent to " + s); // To be implemented
  
  static Effect<String> failure = s -> System.out.println("Error: " + s); // To be implemented
}