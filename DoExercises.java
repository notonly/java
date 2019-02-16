
public DoExercises {


  /**
   * Chapter 2 Exercises
   *
   */

  // exercise 2-1: composing functions
  public static Function<Integer, Integer> compose(Function<Integer, Integer> f, 
      Function<Integer, Integer> g) {

    return new Function<Integer, Integer> {
      @Override
      public Integer apply(Integer x) {
        // return x -> f.apply(g.apply(x));
        // ^^ Wrong

        return f.apply(g.apply(x));
      }
    }
  }


  // Exercise 2-2 (writing compose function in
  // Java Lambda)
  public static Function<Integer, Integer> composeUsingJava8(Function<Integer, Integer> f, 
      Function<Integer, Integer> g) {

    return x -> f.apply(g.apply(x));

  }


  // testing 

  @Test
  public void testCompose() {
    assertThat(DoExercises.compose(x -> x + 2,  x -> x * 2).apply(5), is(5*2+2));
    assertThat(DoExercises.composeUsingJava8(x -> x + 2,  x -> x * 2).apply(5), is(5*2+2));
  }

}



/**
f((x, y)) = f(x, y) 

  = f(x)(y)   // currying
  = g(y)
  = f o g  // (read as:  function f round g)
*/


  // Exercise 2-3
  // write a function to add 2 integers
  // function(x, y) = f(x)(y) , where g = f(x)
  // f(x, y) = g(y), where g = f(x)
i // 
  // 3 + 5 
  //
  public Function<Integer, Function<Integer, Integer>>
    addTwoIntegers(Integer x, Integer y) {

      return x -> ( y -> x + y );
  }

  // reading the above code
  // it saying, applying "currying", 
  // concentrate the 1st argument, and then
  // we get the a Function,  which is 
  // ( y -> x + y )
  // now
  // for each x, its co-domain (return) is a set
  // of functions g (ie, y -> x + y, where x is a
  // constant;  
  // such denoted function g, is result of f(x),
  // will apply to value of "y";  
  //
  // Yeah, right, f(x) returns a Function !
  // 





