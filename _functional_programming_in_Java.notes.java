

These books are goo:

1) Functional Programming in Java -- Pierre-Yves 
    Saumont 
2) Java Performance Companion -- Charlie Hunt ...  
3) Java By Comparison -- Simon Harrer



Pure Functional Programming: comparing Haskell and
Lisp
(https://markkarpov.com/post/lisp-and-haskell.html)



Functional Programming:

1) First-class function

2) annoymonous function

3) closures

4) carrying

5) lazy evaluation

6) parametic polymophism


============= some concepts

1) what is a function ? 

   mathematical object, 

   in real world, a relation between as "source
   set" -- called "function domain" to a "target
   set" -- called function "co-domain".

2) Inverse Fucntions

   f(x) is a function from domain A to codomain B;

   and then, f'(x) from B to A  is called
   "inverse"


3)  Partial Functions

   A relation that "isn't defined" for "all
   elements of the domain" but that fulfills the
   "rest of the requirements -- no element of the
   domain can have the relationship with more than
   one element of the co-domain"  is often called
   "partial function".


   Partial functions are important in programming,
   because many bugs are the result of using a
   partial function "as if" it were a "total" one.  


   ***  By adding an element to the codomain (the
   error condition -- similar to the switch
   statement's "default" case),  and then we can
   transform "partial func" into "total function"


   Returning "partial functions" into total ones
   is an important part of functional programming.
   <== to "avoid bugs"


4) Function composition

   building blocks for other functions.

   math defn :  F o G  -- f found g

   F o G (x) = f(g(x))


   note the functions are applied in inverse of
   writing order.

   Java 8 defines compose() method, the andThe()
   method to represent the composition, 

   f.andThen(g)  is SAME as g.compose(f)


5) Functions with several arguments

   f(x, y) ...


   function with single argument
     f : x -> x + 2
     g : x -> x * 2

     // composing
     f o g (5) = f(g(5)) = f(5 * s) = 10 + 2 = 12



   Multiple-argument, made in "Tuple"

    f(x, y) = x + y

    f((3, 5)) = 3 + 5 = 8


    by convention, the parenthese on tuple can be 
    removed


    f(3, 5) = 3 + 5 = 8



6) Function currying 

   f(x)(y) = g(y)

  using the above example,  
  where 
     g(y) = x + y


    in such a case, we can write f(x)(y) to

     f(x) = g


   ^^^  f(x) = g  

     which means that the result of applying the 
     function f to the argument x, is a new 
     function g.  Applying this g function to y
     gives the following:

     g(y) = x + y


  when applying function g,  x is no longer a 
  variable.  It's a constant.


  so, currying

    f(3)(5) = g(5) = 3 + 5 = 8


  The only new thing here is that the "codomain"
  of f is a set of functions instead a set of
  numbers.  The "result" of applying f to an
  integer is a "function".  
  The "result" of applying this function -- g,  to
  an integer is an integer.

       


  **********************
  f(x)g(y) is the "curried" form of the function 
  f(x, y)
  **********************

  Applying this tranformation to a function of a
  "tuple" (which we can call a function of several
  arguments if we prefer), is called currying.



   Note, there is Haskell programming language, a
   Functional programming.



7) Partially applied functions

   example:

   f(rate, price) = price / 100 * (100 + rate)

   Seems equivalent to 

   g(price, rate) = price / 100 * (100 + rate)


   Note, f, g function have swapped order of
   arguments


Now, consider the "curried" version of these 2
functions:

   f(rate)(price) g(price)(rate)


   saying, f(rate) is a function of a price to a
   price.  If rate = 9, this function applies a
   tax of 9% to a price, giving a new price.
   

   But, g(price) is a function of a rate to a
   price.  If price = 100, it gives a new function
   applying a price of 100 to a variable tax.
   What would you call this function"?  It may be
   useless function, depending on what you're
   solving.


   Functions like f(rate) and g(price) are
   sometimes called "partially applied functions",
   in reference to the forms f(rate, price) and
   g(price, rate).


   Partially applying functions can have huge
   consequences regarding argument evaluation.



8) Understanding "currying"  -- the author using
the example to stress that sometimes, it's good to
make currying (hence saving the curried
intermediate values in memory)

   imaging travelling in foreign country, using
   calculator to convert currencies, would you
   prefer having to type conversion rate each time
   you want to compute a price, or rather put the
   rate in memory (intermediate result from the
   f(rate))?

   Which solution is less error-prone?


9) Functions in Java

   A method can be functional if it respects the
   requirements of "pure function"

   a) must not mutate anything outside the
   function.  (no internal mutation may be visible
   from the outside)
  
   b) must not mutate its arguments

   c) must not throw errors or exceptions (WOW,
   was this the reason not easy to handle
   exceptions in/from Lambda?

   d) must always return a value  (well, Java 8
   Consumer is not such cache, Runnable either)

   e) when called with same arguments, must always
   return same result.


 
===== public int add(int a, int b) { return a + b;
}

 ^^ is a Function


===== public int div(int a, int b) { return a / b;
} ^^ Is NOT function, because it throws div/0
exception.

 To make it a function, we could test the 2nd
 parameter and return a value if it's null it
 would have to be an int.  






1. Recursive Functions

  public int factorial (int n) { return n == 0 ? 1
  : n * factorial(n -1); }


  Java 8 way

  *** add "final" to this member method, and use
  "this" to access it

  public final Function<Integer, Integer>
  factorial = n -> n <= 1 ? n : n *
  this.factorial.apply(n -1);


  *** using "static",  and "class name" replacing
  "this"

  public static final Function<Integer, Interger>
  factorial = n -> n <=1 ? n : n *
  MyClass.factorial.apply(n - 1);




2. Identity function

  static <T> Function<T, T> identity() { return t
  -> t; }



2.0 Java Functional Interface, and Anonymous
classes, lambda expression (anonymous
function/method)


public interface Function { int apply(int arg); }

Function myFuncTriple = new Function() { @Override
public int apply(int arg) { return arg * 3; } }

(my notes:  Java 8 way, using lambda) Function
myFuncTriple2 = x -> x * 3;


^^ this function can be applied to an argument:

System.out.println(myFuncTriple(7));  // 21

System.out.println(myFuncTriple2(7));  // 21





2.1 "Composing Function"

  Function composition is a binary operation on
  functions ...


  Function compose(final Function f1, final
  Function f2) { return new Function() { @Override
  public int apply(int arg) { return
  f1.apply(f2.apply(arg)); } } }


  Function triple = new Function { @Override
  public int apply(int arg) { return arg * 3; } }

  Function square = new Function { @Override
  public int apply(int arg) { return arg * 2; } }


^^ Now calling the method

System.out.println(compse(x -> x + 1,  x -> x *
2).apply(5));  //  (5+1) * 2

System.out.println(compose(triple,
square).apply(5));  //  (5 * 5) * 3 = 75


  Or in Java 8 way (my try)

  Function compose2 = (Function f1, Function f2)
  -> { x -> f1.apply(f2.apply(x)) };

  or

  Function compose3 = (Function f1, Function f2)
  -> x -> f1.apply(f2.apply(x));



2.1.2 Polymorphic functions


public interface Function<T, R> { R apply(T arg);
}


Function<Integer, Integer> triple = new
Function<>() { @Override public Integer
apply(Integer arg) { return arg * 3; } }



2.1.3  Do NOT compose too many functions (where
curried is saved in memory), otherwise Stack
Overflow will happen


Overflow example:

int fnbr = 10_000; Function<Integer, Integer> g =
x -> x;   // Identity function Function<Integer,
Integer> f = x -> x + 1;

// may be no compiling, due to g is not final, and
is changed inside lambda //
IntStream.rangeClosed(1, fnbr).forEach(i -> g =
Function.compose(f, g));

for (int i = 0; i < fnbr; i++) { g =
Function.compose(f, g); }

System.out.println(g.apply(0));

^^ the author said, overflow when looping around
7,500


2.2.5  Simplying the code using lambda


Type inference:   where type not needed for
variable in lambda sometimes, because javac can
infer the types from the left handle side


Specifying Function Types:

   the author prefers 
    
          Integer -> Integer squareFunc = x -> x *
          x;

     ^^ the above is not allowed in Java yet,
     instead, it's 

     Function<Integer, Integer> squareFn = x -> x
     * x;


===================================================

(Sections here are so important for understanding
 the following contents of at least this Chapter 2
 -- using Functions,  the complex combined types
 and functions as argument, and the currying)


2.3 Advanced function features


 2.3.1 function with multiple arguments


   *** Function of one tuple of arguments.  

   Tuple2, tuple3, tuple4, etc ... 



   Let's try to define a function for adding 2
   integers; you will apply a function to the 1st
   argument, and this will return a function!
   (curring).  The type will be as of following:
  


   Function<Integer, Function<Integer, Integer>> 


        Note, here,  the argument is "Integer",
        the return is a function,
        Function<Integer, Integer> that is, a func
        with Integer input and returns an Integer

   ====>  

   Integer -> Integer -> Integer;   // reader from
   right to left,  a Function<T, R> 


   due to associativity,  it's equivalent to 

   Integer -> (Integer -> Integer);




   Example:  


   Write a function to add two Integers

   Function<Integer, Function<Integer, Integer>>
   add = x -> (y -> x + y);


   ^^ the function takes an Integer as its
   argument and Return "a function" from Integer
   to Integer, so the type wil lbe
   Function<Integer, Function<Integer, Integer>>




   Java 8 simplfying this:  by inheritance


   public interface BinaryOperator extends
   Function<Integer, Function<Integer, Integer>> {
   }

   BinaryOperator add = x -> y -> x + y;

   BinaryOperator mult = x -> y -> x * y;

 

2.3.2  Applying curried functions

   continuing with the above example on "add"
   function;

   System.out.println(add.apply(3).apply(5));


   1) add.apply(3)  returns a function,  y -> 3 +
   y  (it's Function<Integer, Integer>)

   2)  now,  add.apply(3).apply(5) goest to apply
   (y -> 3 + y) function on parameter 5

       so, the result is  8




   So, in Scala:

      add(3)(5)


   In Haskell:

      add 3 5




         Note, previously, in many of our code in
         current projects, we do

            Function<Integer, Integer> addMethod =
            (x, y) -> x + y;

            ^^ Note, that did NOT use the
            "currying",  using the author's
            currency conversion example,  we
            always "enter rate"... and that is
            some function-application,  
         
                     System.out.println(addMethod(3,
                     5));



2.3.3  Higher-order functions

   higher-order functions:  take "functions" as
   its arguments and returning functions !




      think of exmaple  (2 * 3) * (2 * 3)    // 

        a.  a function  f =
        compose.apply(square).apply(triple);   //
        <== triple first, 


               Now, think of  (2 * 3) triple
               function;  and then  (x -> x * x)
               double function


   Example, write a function to compose two
   function square and triple used in previous
   exs..


   Function<Function<Integer, Integer>,
   Function<Function<Integer, Integer>,
   Function<Integer, Integer>>>

      compose = x -> y -> z ->
      x.apply(y.apply(z));



        ^^^  Note, this "compose" function, having
        "x" being Function<Integer, Integer> "y"
        being Function<Integer, Integer>




      compose2 = (Function<Integer, Integer> x) ->
      ((Function<Integer, Integer> y) -> ((z) ->
      x.apply(y.apply(z)))) ;


    


   here, compose function's argument is a function
   (Integer -> Integer),  and returns a function
   with argument of func (Integer -> Integer), and
   returning a function of (Integer -> Integer)



   ^^ honest, not quite get this composing ...

   (updates, yea, had to do the Exercises, and
    then we can gradually follow high-order FP
    programming in Java)



   ^^^ The author stated on P35,  

       Function<T, Function<T, T>>


       Now, T is Function<Integer, Integer>;
       replacing T with Function<Integer, Integer>

       so,  

       Function< Function<Integer, Integer>,  

                      Function< Function<Integer,
                      Integer>, Function<Integer,
                      Integer>>>






   Function<Integer, Integer> triple = x -> x * 3;

   Function<Integer, Integer> square = x -> x * x;

   Function<Integer, Integer> f =
   compose.apply(square).apply(triple);


   ^^ the code starts by applying 1st argment,
   gives a new function to apply to the 2nd
   argument.   

      The result is a function, which is the
      compisition of two function argments.




   what is that fumular looks like?   


   system.out.println(f.apply(2));

     ==>   (2 * 3) * (2 * 3)



     Note, the triple is apply first compose
     definition;




2.3.4  Polymorphic higher-order functions 

       (Generics:  
        
        static <T, U, V>
          Function<Function<U, V>, 
                 Function<Function<T, U>,
                          Function<T, V>>>
          higherCompose()

             = f -> g -> x -> f.apply(g.apply(x));


Exercise 2.5 (HARD)
  write a polymorphic version of "compose"
  function.

  Java does not have "polymorphic properties", the
  solution is to store function in a method,
  interface, instead of in a property.


  Java does not handle variance, finding trying to
  cast Function<Integer, Integer> to
  Function<Object, Object> which will generate
  compile error.  Solution is to specify the type
  for variables.



"Variance" - describes how parameterized types
behave in relation to subtyping.  

"covariance" means Matcher<Red> is considered a
subtype of Matcher<Color>, if Red is subtype of
Color. 

But, in Java,  all parameterized types are
"invariant" on their parameters.  That is,
Matcher<Red> has nothing to do with
Matcher<Color>, even though Red is subtype of
Color.    This may be due to "type erasure". 



Only class, interface, method can define type
parameter; most practical is a static method:

static <T,U,V> 
  Function<Function<U,V>,
           Function<Function<T,U>,
                    Function<T,V>>> 
higherCompose() {

    return f -> g -> x -> f.apply(g.apply(x));
}


^^ now my question, f, g, x,  what are their types?

 is g of Function<T,V>, and x of V?


 f : Function<U,V>
 g : Function<T,U>
 x : T


 ? what has type V?  the result of g.apply , that
 is, the "partial applied function"


   return 
     ( (f) -> ( (g) -> 
                      ( (x) -> f.apply(g.apply(x)) 
                      )
               )
     )



 Better yet, just put types to arguments of those
 passed-in functions


 static <T, U, V> 
  Function<Function<U,V>,
           Function<Function<T,U>,
                    Function<T,V>>>
   higherCompose() 
     =  (Function<U,V> f)
          -> (Function<T,U> g)
            -> (T x)
             -> f.apply(g.apply(x));

      



Now, for high-order andThen() function


static <T,U,V>
Function<Function<T,U>,
         Function<Function<U,V>,
                  Function<T,V>>>
andThen() 
  = (Function<T,U> f)
    -> (Function<U,V> g)
     -> (T x) 
      -> g.apply(f.apply(x));










(type of Function not used before in projects)

<A, B, C, D> Function<A, Function<B, Function<C,
      Function<D, String>>>> f() { return a -> b
      -> c -> d -> String.format("%s, %s, %s, %s",
      a, b, c, d); }



================= examples to be executed in
JShell 

import java.util.function.Function;


public class Fnt {

    public <A, B, C, D> String f1(A a, B b, C c, D
      d) { return String.format("%s, %s, %s, %s",
      a, b, c, d); }

    public <A, B, C, D> Function<A, Function<B,
          Function<C, Function<D, String>>>> f2()
          { return a -> b -> c -> d ->
          String.format("%s, %s, %s, %s", a, b, c,
          d); } }

Fnt fnt = new Fnt();

String r1 = fnt.f1("hello", "world!", "it's",
"me");

System.out.println("calling function f1
(traditional way): " + r1);

String rslt =
fnt.f2().apply("hello").apply("world!")
        .apply("its").apply("me");

System.out.println("calling function f2, new way:
" + rslt);



^^ The results:  

calling function f1 (traditional way): hello,
world!, it's, me

calling function f2, new way: hello, world!, its,
me


=======================











3. "Function" interface


  public interface Function<T, U> {

    U apply (T arg);

    default <V> Function<V, U> compose(Function<V,
    T> f) { return x -> apply(f.apply(x)); }

    default <V> Function<T, V> andThen(Function<U,
      V> f) { return x -> f.apply(apply(x)); }

    static <T> Function<T, T> identity() { return
    t -> t; }

    static <T, U, V> Function<V, U>
    compose(Function<T, U> f, Function<V, T> g) {
    return x -> f.apply(g.apply(x); }

    static <T, U, V> Function<T, V>
    andThen(Function<T, U> f, Function<U, V> g) {

      // here f <T, U> , so x is of T,  and
      f.apply(x) is of U;   and then g <U, // V>
        having input of U,  and returns type of V

      return x -> g.apply(f.apply(x));   
      
    }

    
    // higher order comp...  static <T, U, V>
    Function<Function<T, U>, Function<Function<U,
      V>, Function<T, V>>> compose() {

         return x -> y -> y.andThen(x); }






