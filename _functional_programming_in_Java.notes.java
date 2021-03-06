

These books are goo:

1) Functional Programming in Java -- Pierre-Yves 
    Saumont 
2) Java Performance Companion -- Charlie Hunt ...  
3) Java By Comparison -- Simon Harrer



Pure Functional Programming: comparing Haskell and
Lisp
(https://markkarpov.com/post/lisp-and-haskell.html)


the book online "learn you a Haskell" 
http://learnyouahaskell.com/starting-out



*********************

Autho's preface mentioned: 

Chapter 12 (handling state mutation in functional
    way), 
Chapter 13 (function Input/Output) 

are advanced topics, good to study

but may be skipped and straightly go to Chapter
14, 15...

*********************



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



*** Method Reference


Function<Double, Double> mathSinFunc
 = Math::sin;


 same as


 Function<Double, Double> mathSinFuncWithLambda
  = x -> Math.sin(x);


^^^ Now, question:  when to use "anonymous
function", when to use "method-referenc" (ie,
  named function) ?


 ==>  If the function only needs to write "once",
 and then, we can use "anonymous function",  but,
 if want for better code maitenance, and clarity,
 we can use method-reference



2.3.6 Local Functions

1) can define functions locally in methods,
2) but, cannot define methods within methods

3) functions can be defined inside functions
through lambdas.

*** embedded lambdas shown below

public <T> Result<T> ifElse(List<Boolean>
    conditions, List<T> ifTrue) 
{
  return conditions.zip(ifTrue)
            .flatMap(x -> x.first(y -> y._1))
            .map(x -> x._2);
}


^^ flatMap method takes a function as its argument
(in the form of a lambda), and there is a new
lambda in the above,  ie

   x -> x.first(y -> y._1)

  where (y -> y._1) is a lambda in the lambda of
     x -> x.first(....)


 Local functions aren't always anonymous, they are
 generally named when used as "helper functions".



 The above, is equivalent to the following

 public <T> Result<T> ifElse(List<Boolean>
     conditions, List<T> ifTrue) 
{
  Function<Tuple<Boolean, T>, Boolean> f1 
    = y -> y._1;

  Function<List<Tuple<Boolean, T>,
    Result<Tuple<Boolean, T>>> f2

      = x -> x.first(f1);   // note the f1 called

  Function<Tuple<Boolean, T>, T> f3 
     = x -> x._2;

  return conditions.zip(ifTrue)
            .flatMap(f2)
            .map(f3);
}



When using "named" functions, it implioes "writing
types explicitly", it's helpful when compiler
could not infer types.


2.3.7 Closures

Java 8, the variables from enclosing part, can be
accessed in lambda, as long as they are
"implicityly final",  that is,  not changed in the
enclosing part;   not required to explicitly to be
declared as "final"




2.3.8 Partial function application and automatic
currying


currying and partial application are closely
related. 

Currying consists of replacing a function of a
tuple with a new function that you can partially
apply,  one argument after the other.


The main difference between curreied function and
a function of a tuple:  with a function of a
tuple, all arguments are evaluated before the
function is applied.  
With curried version, all arguments must be know
before the function is totally applied, but a
single argument can be evaluated before the
function is partially applied to tit.

You are not obliged to toally curry the function.  

A function of 3 arguments, can be curreited into a
function of a tuple that produces a function of a
single argument.


Example (Exercise 2-7)

<A, B, C> Function<B, C> partialA(A a, 
    Function<A, Function<B, C>> f) 
{
  return f.apply(a);
}


^^^ calling as

Function<Integer, Function<Double, Double>> 
 f = a -> b -> a * (1 + b / 100);

 Function<Double, Double> g = partialA(89, f);

 assertEquals(f.apply(89).apply(33), g.apply(33));



*** Exercise 2.8 (partially apply a curried function
     of 2 arguments to its 2nd argument)

 <A, B, C> Function<A, C> paritalB( B b, 
     Function<A, Function<B, C>> f)
{
  return a -> f.apply(a).apply(b);
}


*** Always look for "types" to think of solutions! 


^^^ Though Process !!!!


1) must return  a function from A to C,  so can
start the implementation by writing following: 

<A,B,C> Function<A,C> partialB(B b, Function<A,
    Function<B, C>> f) 
{
  return a -> 


because of Function<A, C> is a function asking for
argument A ;  

2) now, must write an expression that is composed
  of function "f" and the variables a and b;  and
  it must evaluate to "a function from A to C", so
  can start by applying "f" to A, get

   return a -> f.apply(a)


   ^^ that returns a "function from B to C";  

3) since we need C, and already have B, so just
  from the "function from B to C",  make it apply
  B as :

  return a -> f.apply(a).apply(b) 

  that f.apply(a).apply(b) return C,  now

     A -> C  is the Function<A, C>  as required



*** Exercise 2.9 (convert following method to a
    curried function)

write a curried function for the following method:

<A,B,C,D> String func(A a, B b, C c, D d) 
{
  return String.format("%s, %s, %s, %s", a, b, c,
      d);
}


to following "curried function"



(type of Function not used before in projects)

<A, B, C, D> Function<A, Function<B, Function<C,
      Function<D, String>>>> f() 
{ 
  return a -> b -> c -> d 
          -> String.format("%s, %s, %s, %s",
                                      a, b, c, d); 
}


^^^ thought process:

1. writing the function signature !

1) the method PARAMETERS 

    <A, B, C, D>

2) add return type;  write word Function<
followed by the 1st parameter type and a comma:

   <A,B,C,D> Function<A, 

3) then do the same thing for 2nd parameter

   <A,B,C,D> Function<A, Function<B,


4) continue until no parameters left

  <A,B,C,D> Function<A, Function<B, Function<C,
    Function<D, 

5) Add the "return type" and close all opened
brackets:

  <A,B,C,D> Function<A, Function<B, Function<C,
    Function<D, String>>>>
  

2. Now the implementation : 

1) list as many parameters as needed, separating
them with right arrows (ending with an arrow)

  return a -> b -> c -> d ->

2) finally, the implementation, which is the same
as in the original method: 

  



================= examples to be executed in
JShell   (can use JUnit to test as well)

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



*** Exercise 2-10  (write a method to curry a
    function of a Tuple<A, B> to C


transform 
   Function<Tuple<A, B>, C> 
to
   Function<A, Function<B, C>>


static <A,B,C> Function<A, Function<B, C>>
curry(Function<Tuple<A, B>, C> tf) 
{
  return a -> b -> tf.apply(new Tuple<>(a, b));
}




2.3.9  Switching arguments of partially applied
functions


If a function of 2 arguments, you might want to
apply only the 1st argument to get a "partially
applied function"

Function<Double, Function<Double, Double>> addTax
= x -> y -> y + y / 100 * x;


You might want to first apply tax to get a new
function of 1 arguemnt that you can then apply to
"any price"

Fnction<Double, Double> add9percentTax =
addTax.apply(9.0);


Then, when you want to add tax to a price, you can

  Double priceIncludingTax =
  add9percentTax.apply(price);

  That is fine; but what if the "inital function"
  as as following:

  Function<Double, Function<Double, Double>>
  addTax2 = x -> y -> x + x / 100 * y;


  ^^ the order of "arguments" changed compared to
  the above,

  here, "price" is 1st argument.  Applying the
  price only is probably "useless",  but how can
  you apply the tax only?


  *** Exercise 2-11 (write a method to swap the
      arguments of a curried function)


static <T,U,V> Function<T, Function<U,V>>
  swapingArg(Function<U, Function<T, V>> f) 
{
  return  t -> u -> f.apply(u).apply(t);
}



2.3.10 Recursive function

Function Programming may not like to use recursive
function, comparing it to GoTo statement.

but the author said, as a function programmer,
    must master this recursive function, even not
    to use it.

Java recursion is limited, pushing all
intermediate computation state to "stack", and
until termination, popping stack, to get those
states.  The statck size can be configured, but
"all threads" use the same configured value:  the
default stack size 320kb for 32-bit
implementation, to 1064kb for 64-bit;  

^^ 320kb or 1064kb are small, compared to "heap
size".   Hence, the number of "recursive steps" is
limited.

Usually, Java can handle about 5,000 to 6,000
recursive steps;  but this number depends on "data
size" that is pushed to stack, and also depends on
the state of the "stack" when "recursive process
starts"


^^^ Pushing this limit "artificially" is possible
because Java uses "memoization" internally (some
dynamci-programming mechanism).  This technique
consists of "storing results of functions or
methods in memory to speed up future access".  

In stead of re-evaluating a result, Java can
retrieve it from "memory" if it has previously
been stored.  ==> benefits:  speeding up,  and
also allow to partly avoid recursion by finding a
terminal state much quicker (note, the author
    said, avoid recursion)


*** Chapter 4, create "heap-based" recursion in
Java.


*** Exercise 2.12 (writing recursive factorial
    function)


// Note, the recursive factorial method

static int factorial(int n) {
  if (n <= 1) {
    return 1;
  }

  return n * factorial(n - 1)
}



The recursive "function" --- the "static" field

public static Function<T, R> factorial1;
static {
  factorial1 = n -> n <= 1 ? 1 :
    n * factorial.apply(n - 1);
}


Note that need to put factorial1 definition in the
block, not the direct definition and assignment
(this way causes "Illegal Self Reference")


========
Note, for non-static recursive function, need to
add "this" in function body

public final Function<Integer, Integer> factorial2
= n -> n <= 1 ? n : n * this.factorial2.apply(n -
    1);


For static version, just need to replace "this"
with class name

public static final Function<Integer, Integer>
factorialStatic = n -> n <= 1 ? n : n *
MyClass.factorialStatic.apply(n - 1);




2.3. The author's "Function" interface,  adding
compose, andThen, higherCompose, higherAndThen
functions


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



2.4 Java 8 functional interface

1) SAM type - Single Abstract Method

a) java.util.function.Function -- 

b) java.util.function.Supplier -- equivalent to
function with "no argument"; 

    It can be used to "supply" variable data, such
    as time or random number (the author said, 
    not good to use such non-functional thing)


    It can also be used in "lazy evaluation".

c) java.util.function.Consumer -- the author said,
  it isn't for functions (because Java Consumer does
  not return a value);  but for effect.   


d) java.lang.Runnable -- can also be used for
effects that don't take any parameter.


The author said, Java's other functional
interfaces (from java.util.function package),
most of them are "useless" for functional
programming.  Many of them deal with primitives
and others with functions of 2 arguments, and
there are special versions for operations.


In this book, the author does NOT talke much about
Java 8 functions.  
    

2.5 Debugging Lambdas

The author said, "functional programming"
(specially those pure functions), will not fail
underterminstically, but "imperative programming"
can, because imperative-programming does not take
care of all the cases.




Chapter 3 :  Making Java more functional


3.1 making standard control structures functional 

1) Imperitive programming uses extensively "if ..
else ... switch ... case...  for ... while ..."
  conditonal structures

2) The author said, functional programming can
totally avoid those conditional flows.


2.3 Abstracting control structures 

Page 64, the author mentioned:

   Trust the "types",  not the "names"!


public interface Effect<T> 
{
  void apply(T t);
}


public interface Result<T> 
{
  void bind(Effect<T> success, Effect<String> failure);

  public static <T> Result<T> failure(String msg) {
    return new Failure<>(msg);
  }

  public static <T> Result<T> success(T val) {
    return new Success<>(val);
  }

  public class Success<T> implements Result<T> {
    private final T val;
    private Success(T t) {
      val = t;
    }
    @Override
    public void bind(Effect<T> success,
        Effect<String> failure) {
      success.apply(val);
    }
  }
  public class Failure<T> implements Result<T> {
    .
    .
    .
  }
}


=============== using the above, with Functional
Programming:

public class EmailValidation {
  static Pattern emailPattern =
    Pattern.compile("^[a-z0-9._%+-]+\\.[a-z]{2,4}$");


  static Function<String, Result<String>>
    emailchecker = 
      s -> {
        if (s == null) {
          return Result.failure("email must not be
              null");
        } else if (s.length() == 0) {
          return Result.failure("....");
        } else if
          (emailPattern.matcher(s).matchers()) {
            return Result.success(s);
        } else {
          return Result.failure("email " + s + "
              is invalid");
        }
      };



3.2.2 An alternative to "if...else"

The most basic "if structure" may be seen as the
"implementation of a function"  (note, the
implementation,  not a function itself).

if (x > 0) {
  return x;
} else {
  return -x;
}


We could write this function as:

Function<Integer, Integer> 
  absolute = x -> {
    if (x > 0) {
      return x;
    } else {
      return -x;
    }
  };



*** Exercise 3.2 write a Case class representing a
condition and corresponding result;  the condition
will be represented by Supplier<Boolean>, where
Supplier is a functional interface such as:

interface Supplier<T> {
  T get();
}



3.3 Abstracing Iteration

3.3.2 Creating lists

Exercise 3.3 

 Write methods that create an empty list, a list
 with one element, and a list from a collection of
 elements, as well as vararg method that creates a 
 list from a list of arguments


 3.3.3 using head and tail operations

 Exercise 3.4

 Create 2 methods that return the head and the
 tail of a list, the list passed as an argument
 must not be modified. Define a copy of the list,
 for copying list.  The list resturned by tail
 should be immutable.


 3.3.4 Functionally appending to a list


 3.3.5 Reducing and folding lists

 transforms a list into a single value --
 resulting value may be any type. 

 Folding to a result that is the same type as the
 list elements, is a "specific" case called
 "reducing".  

 Computing the sum of the elements of the list of
 "integers" is a simple case of "reducing".


 "folding" can be done left-to-right, or
 right-to-left.
  -- if operation is "commutative", l-to-r and
  r-to-l are equivalent;
  -- if "not commutative", then not equivalent.


==> basically, the author is talking about Java's
"Collectors.reducing", "Collectors.aggregate"? 
functionality, 


 "folding" requires an-initial value, which is
 called "accumulator".  If this is missing, Java
 returns "Optional", because "Stream" can be null.



* Example:  list (1, 2, 3, 4)
*
  to fold that list to a sum

  1) from left to right

      (((0+1) + 2) + 3) + 4 = 10

  2) from right to left

      1 + (2 + (3 + (4 + 0))) = 10


   ^^ "sum" is "commutative", hence, l-to-r and
   r-to-l are equivalent.


* Example: list ('a', 'b', 'c')

   folding character into String,  "left folding"
   (when accummulator is on left-side)
   is not equivalent to "right folding"


when "left folding" not communitive to "right
folding",  to make results the same, it will
require applying different operations for "left
folding" and "right folding"


foldLeft(list, accumulator, 
    x -> y -> operation1);

foldRight(reverse(list), accumulator,
    y -> x -> operation2);



*** Exercise 3-5 create a method to fold a list of
integers that can be used, to sum the elements.
This methods take a list of integers, and an
integer starting value, and a function as the
parameters.

public static Integer
foldingIntegers(List<Integer> list, Integer
    accumulator, Function<List<Integer>,
    Function<Integer, Integer>> operator) 
{
  Integer rslt = accumulator;

  list.forEach(e -> 
}



*** Exercise 3-6 Generalize the "fold" method to
"foldLeft" so that it can be used to apply a left
fold to a list of elements of arbitrary types.  To
test the method correctness, apply it to the
following parameters,

List<Integer> list = list(1, 2, 3, 4, 5);
String identity = "0";
Function<String, Function<Integer, String>> f = x
-> y -> addSI(x, y);

where method addSI is defined as follows:

String addSI(String s, Integer i) {
  return "(" + s + " + " + i + ")";
}

Verify that you get following output:

((((0 + 1) + 2) + 3) + 4) + 5

Note this addSI helps make sure the correct
folding.  



Right-folding example:



*** Exercise 3-7 write an imperative version of
foldRight method


List<Integer> list = list(1, 2, 3, 4, 5);
String identity = "0";
Function<String, Function<Integer, String>> f = x
-> y -> addSI(x, y);

where method addSI is defined as follows:

String addSI(String s, Integer i) {
  return "(" + s + " + " + i + ")";
}

Verify that you get following output:

(1 + (2 + (3 + (4 + (5 + 0)))))




*** Exercise 3-8 write a recursive version of
foldRight. Beware that a naive recurive version
won't fully work in Java because it uses the
"statck" to accumulate "intermediate"
calculations.  In Chapter 4, will learn how to
make stack-safe recursion available.



*** Heap-based recursion :  exercise 3-8 isn't
"tail recursive", so cannot be optimized to use
the heap instead of stack.  This will be revisited
in Chapter 5.



___ Reversing a List

defining a reverse method with an imperative impl
is easy by iterating "backward" over the list;
must be careful, not to mess with the indexes.



*** Exercise 3-9 (HARD) define reverse method
without-using-loop, instead, use the methods
you've developed to this point.


*** Exercise 3-10 (HARD) define a method to "map"
a list by applying an operation to each element.
Rewrite the "map" method in terms of previous
exercises' "foldLeft" or "foldRight"


3.3.6 Composing mappings and mapping
"compositions" 

==>  to make up


3.3.7 Applying effects to lists


==>  to make up


3.3.8 Approaching functional output


==>  to make up


3.3.9 Building corecusive lists


==>  to make up



*** Exercise 3-11 Write a method to produce a list
using a starting value, a limit, and the function
x -> x + 1;   call this method "range", which will
have the following signature : 

List<Integer> range(int start, int end)



*** Exercise 3-12 write a generic "range" whcih
will work for "any" type and "any" condition. 
Because notion of "range" mainly works for
"numbers", let's call the method "unfold" and
giving following signature : 

List<T> unfold(T seed, Function<T, T> func,
    Function<T, Boolean predicate)


*** Exercise 3-13 Implement "range" method in term
of "unfold"


*** Exercise 3-14 write recursive version of
"range" method (ex 3-13)


___ Danger of statck-based recursion 


==>  to make up


___ Danger of Strictness


==>  to make up



3.4 Using the right types


3.4.1 Problems with standard types



==>  to make up



3.4.2 Defining "value" types


==>  to make up


3.4.3 Future of "value" types in Java


==>  to make up


3.5 Summary





Chapter 4 - Recursion, Corecursion, and
Memoization (Dynamic programming)


4.1 Understanding corecursion and recursion


4.1.1 exploring corecursive and recursive addition
examples


4.1.2 implementing recursion in Java


4.1.3 using tail call elimination


4.1.4 using tail recursive methods and functions


4.1.5 Abstracting recursion


4.1.6 Using a drop-in replacement for
"stack-based" recursive methods


4.2 Wroking with recursive "functions"


4.2.1 Using locally defined "functions"


4.2.2 Making "functions" "tail" recursive


4.2.3 Doubly recursive "functions": the Fibonacci
example 


*** Exercise 4-1 Create a tail recursive version
of Fibonacci "functional" method


*** Exercise 4-2 Turn the function into
"stack-safe" recursive one


4.2.4 Making the list methods "stack-safe" and
recursive


*** Exercise 4-3 Create "stack-safe" recursive
version of "foldLeft" method


*** Exercise 4-4 Create a fully recursive version
of the recursive "range" method


*** Exercise 4-5 (HARD) create stack-safe
recursive version of "foldRight" method


4.3 Composing a huge number of functions (avoid
    stack overflow)

*** Exercise 4-6 Write a function, "composeAll",
  taking as its argument a list of "functions"
  from T to T and returning the result of
  composing all the functions in the list.


*** Exercise 4-7 Fix this problem so you can
compose an (almost) unlimited number of functions.


*** Exercise 4-8 The code has 2 problems, you
fixed only one, can you see another problem and
fix it?



4.4 Using Memoization

4.4.1 Memoization in imperative programming


4.4.2 Memoization in recursive programming


*** Exercise 4-9 write a stack-safe "tail
recursive" "function" taking an integer n as its
argument and returning a string representing the
values of Fibonacci numbers from 0 to n, separated
by a comma and a space.


  Recursive or Corecursive ....


4.4.3 Automatic memoization


___ Memoization of "multiargument" functions


___ Are memoized functions "pure" ? 


4.5 Summary




Chapter 5 Data handling with lists


5.1 How to classify data collections

5.1.1 Different types of lists


 - Access

 - Type of ordering

 - Implementation


5.1.2 Relative expected list performance


5.1.3 Trading time against memory space, and time
against complexity


5.1.4 In-place mutation


5.1.5 Persistent data structures


5.2 An immutable, persistent, singly linked list
implementation


5.3 Data Sharing in list operations


*** Exercise 5-1 Implement the instance functional
method "cons", adding an element at the beginning
of a list (Remember cons stands for construct)


*** Exercise 5-2 Implement "setHead", an instance
method for replacing the first element of a List
with a new value.


*** Exercise 5-3 Write a "toString" method to
display the content of a list. An empty list will
be displayed as "[NIL]", and a list containing the
integer from 1 to 3 will be displayed as "[1, 2,
3, NIL]".  For a list of arbitrary objects, the
toString method will be called to display each
object.


5.31 More list operations


*** Exercise 5-4 The "tail" method, although it
doesn't mutate the list in any way, has the same
effects as removing the first element. Write a
more general method, "drop", that removes the
first "n" elements from a list.  This method eon't
remove the element, but will return a new list
corresponding to the intended result.  This "new"
list won't be anything new, because data sharing
will be used, so nothing will be created. Figure
5.4 shows how should proceed, the signature of
this method will be:

public List<A> drop(int n);



*** Exercise 5-5 Implement a "dropWhile" method to
remove elements from the head of the List as long
as a condition holds true. Signature to add to the
"List" abstract class

public abstract List<A> dropWhile(Function<A,
    Boolean> func);


__ Concatenating lists

__ Dropping from the end of the list

*** Exercise 5-6 Write a method to remove the last
element from a list. It should return the
resulting list. Signature:

List<A> init()


5.4 Using recursion to fold lists with
higher-order functions


*** Exercise 5-7 Write a functional method to
compute the "sum" of all elements of a list of
integers using simple stack-based recursion



*** Exercise 5-8 Write a functional method to
compute the product of all elements of a list of
doubles using simple stack-based recursion



*** Exercise 5-9 Write a method to compute the
length of a list. It will use "foldRight" method



*** Exercise 5-10 The "foldRight" method uses
recurion, but not "tail recursive", so it will
rapidly overflow the stack.  Java "stack size" is
configurable;  but it's shared for "all threads",
hence if configured large size, may waste
memory.


Instead of "foldRight", create a "foldLeft" method
that is "tail recursive" and can be made
stack-safe. the signature:

public abstract<B> B foldLeft(B identity,
    Function<B, Function<A, B>> func) ;


*** Exercise 5-11 Use your new "foldLeft" method
to create a new stack-safe versions of sum,
product, and length


*** Exercise 5-12 Use "foldLeft" to write a static
functional method for reversing a list


*** Exercise 5-13 (HARD) 
  write foldRight in terms of foldLeft


5.4.1 Heap-based recursive version of "foldRight"


*** Exercise 5-14 use what's learned from Chapter
4, to write a heap-based recursive instance
version of the "foldRight" method.


*** Exercise 5-15 Implement "concat" in terms of
either foldLef or foldRight


*** Exercise 5-16 Write a method for flattening a
list of lists into a list containing all elements
of each contained list.

^^^ this can help understand the Java flatMap
method



5.4.2 Mapping and filtering lists


*** Exercise 5-17 write a functional method taking
a list of integers and multiplies each of them by
3.


*** Exercise 5-18 write a function returning each
value in a List<Double> into a String


*** Exercise 5-19 write a general function method
"ma" allowing to modify each element of a list by
applying a specified function to it.  This time,
make it an instance method of List. add
following to book's List class:

  public abstract <B> List<B> map(Function<A, B>
      func);

*** Exercise 5-20 write a filter method removing
from a list the elements that don't satify a given
predicate. Implementing it as an instance method
with signature

  public List<A> filter(Function<A, Boolean> func)

*** Exercise 5-21 Write a "flatMap" method
applying to each melement of List<A> a function
from A to List<B> and returns List<B>,  signature:

public <B> List<B> flatMap(Function<A, List<B>>
    func);


*** Exercise 5-22 create a new version of filter
based on flatMap


5.5 Summary





Chapter 6 Dealing with Optional Data


- developing "option" data type for optional data
- applying functions to optional values
- composing optional values
- option use cases


6.3 The Option data type  


6.3.1 Getting a value from an option

*** Exercise 6-1 Implement a getOrElse method
return either contained value if exists, or
provideed-default value otherwise.  Signature

A getOrElse(A defaultValue);


*** Exercise 6-2 Fix previous problem by using
"lazy evaluation" for "getOrElse" method
parameter.


6.3.2 Applying functions to optional values

*** Exercise 6-3 Create a map method to change
Option<A> into Option<B> by applying a function
from A to B


6.3.3 Dealing with Option composition

*** Exercise 6-4 create a "flatMap" instance
method taking an argument of a function from A to
Option<B> and returns an Option<B>


*** Exercise 6-5 create "orElse" method with
following signature

Option<A> orElse(Supplier<Option<A>> defaultValue)


*** Exercise 6-6 in chapter 5, "filter" method
removing from a list all elements that didn't
satisfy a condition expressed in the form of
predicate (in other words, it was a function
returning a Boolean).  Create same method for
Option, the signature:

Option<A> filter(Function<A, Boolean> f);


6.3.4 Option Use Cases


*** Exercise 6-7 implement "variance" function in
terms of flatMap.  the variance of a series of
values represents how those values are distributed
around the mean.  If all values are very near to
the mean, the variance is low. 
The variance of a series is the mean of Math.pow(x
    - m, 2) for each element x in the seriers, m
being the mean, signature:

Function<List<Double>, Option<Double>> variance =
...;


6.3.5 other ways to combine options


*** Exercise 6-8 Define a "lift" method taking a
function from A to B as its argument and returning
a function from Option<A> to Option<B>. As usual,
use the methods already defined.


*** Exercise 6-9 Such solutions are useless for
methods that throw exceptions. Write a "lift"
method that works with methods that throw
exception.


*** Exercise 6-10 write a method (map2) taking
arguments Option<A>, Option<B>, and a function
from (A, B) to C in curried form, and returning
Option<C>


6.3.6 Composing List with Option

*** Exercise 6-11 write a function "sequence"
combining List<Option<T>> into an Option<List<T>>.
It will be a Some<List<T>> if all values in the
original list were Some instance, or None<List<T>>
otherwise.  Signature:

Option<List<A>> sequence(List<OptionA>> list)


*** Exercise 6-12 define a traverse method
producing the same result but invokes "foldRight"
only once.  signature:

Option<List<B>> traverse(List<A> list, Function<A,
    Option<B>> func);

6.4 Miscellaneous untilities for Option


6.4.1 testing for Some or None


6.4.2 equals and hashcode

6.5 How and when to use Option


___ when to use getOrThrow


6.6 Summary



Chapter 7 Handling Errors and Exceptions


-- holding information about "errors" with
"Either" type
-- easier error handling with biased Result type
-- accessing the data inside a Result
-- applying effects to Result data
-- lifting functions to operate on Result


7.1 problems to solved

7.2 the Either type

7.2.1 Composing Either 

*** Exercise 7-1 define a "map" method to change
an Either<E, A> into Either<E, B>, given a
function from A to B; signature:

public abstract <B> Either<E, B> map(Function<A,
    B> f);


*** Exercise 7-2 define "flatMap" to change
Either<E, A> into Either<E, B>, given a function
from A to Either<E, B>. signature:

public abstract <B> Either<E, B>
flatMap(Function<A, Either<E, B>> f);


*** Exercise 7-3 define methods getOrElse and
orElse with following signatures

public A getOrElse(Supplier<A> defaultVal);

public Either<E, A> orElse(Supplier<Either<E, A>>
    defaultVal);



7.3 the "Result" type


7.3.1 adding methods to Result class

*** Exercise 7-4 define "map", flatMap, getOrElse,
orElse for Result class. For getOrElse, can
define 2 methods: one taking a value as its
argument, the other taking a Supplier.

  Signagures:

abstract V getOrElse(final V defaultVal);

abstract V getOrElse(final Supplier<V>
    defaultVal);

abstract <U> Result<U> map(Function<V, U> f);

abstract <U> Result<U> flatMap(Function<V,
    Result<U>> f);

Result<V> orElse(Supplier<Result<V>> defaultVal);


7.4 Result patterns


7.5 Advanced Result handling

*** Exercise 7-5 write "filter" method taking a
condition that is represented by a function from T
to Boolean, and returning a Result<T>, which will
be Success or Failure depending if condition holds
for the wrapped value, signature:

filter(Function<T, Boolean> f);


*** Exercise 7-6 define an "exists" method taking
a function from T to Boolean and returning true if
the wrapped value matches the condition; false
otherwise, signature:

boolean exists(Function<T, Boolean> predicate);


7.5.2 Mapping failures

*** Exercise 7-7 define "mapFailure" method taking
a String and transform Failure into another
Failure using the string as its error message. If
Result is Empty or Success, this  method does
nothing


7.5.3 adding factory methods


*** Exercise 7-8 define following static method

static <T> Result<T> of(T val);
static <T> Result<T> of(T val, String msg);

static <T> Result<T> or(Function<T, Boolean> pred,
    T val);

static <T> Result<T> or(Function<T, Boolean> pred,
    T val, String msg);


7.5.4 applying effects


*** Exercise 7-9 define "forEach" method taking an
Effect and applyinh it to the wrapped value.


*** Exercise 7-10 define forEachOrThrow to handle
failure, signature:

abstract void forEachOrThrow(Effect<T> eff);



*** Exercise 7-11 more general use case when
applying Effect to Result is applying effect if
it's a success. and handle exception if some way
if failure.  

define forEachOrException emthod to apply effect
if value present, and return Result.   Result will
be Empty if original Result was a Success;  or
Empty and Success<RuntimeException> if it was a
Failure.


7.6.6 advance result composition


*** Exercise 7-12 write "lift" method for Result,
a static method in Result class with signature:

  static <A, B> Function<Result<A>, Result<B>>
  lift(final FUnction<A, B> f);

*** Exercise 7-13 define lift2 and lift3 as
following


static <A, B, C> Function<Result<A>,
  Function<Result<B>, Result<C>>>
  lift2(Function<A, Function<B, C>> f);

static <A, B, C, D> Function<Result<A>,
  Function<Result<B>, Function<Result<C>,  
  Result<D>>> lift3(Function<A, Function<B, C>> f);

*** Exercise 7-14 in chapter 6, defined "map2",
taking Option<A>, Option<B. and a function from
A to B to C, and returing Option<C>

let's define map2 for Result


7.6 Summary



Chapter 8 Advanced list handling


-- speeding list processing with memoization
-- composing List and Result
-- implementing indexed access on lists
-- unfolding lists
-- automatic parallel list processing


8.1 the problem with length

8.1.1 performance issue

8.1.2 benefit of memoization

8.1.3 drawback of memoization

___ Java 7, Java 8 : size of objects references
...   http://mng.bz/TjY9  and http://mng.bz/8X0o


*** Exercise 8-1create memoizedversion of length
method, signature:

public abstract int lengthMemoized();


8.1.4 actual performance

8.2 composing list and result

8.2.1 methods on list returning Result

*** Exercise 8-2 implementing headOption in
List<A> returning Result<A>

*** Exercise 8-3 create lastOption returing Result
of last element in list.

*** Exercise 8-4 replace headOption with a signle
implementation in List class.  What's benefit, and
drawback of such implementation?


8.2.2 converting from List<Result> to Result<List>

*** Exercise 8-5 define flattenResult taking
List<Result<A>> and returning List<A> containing
all success values in the original list, ignoring
failures and empty values.

static <A> List<A> flattenResult(List<Result<A>>
    list);

*** Exercise 8-6 write "sequence" combing a
List<Result<T>> into Result<List<T>>;  it will be
a Success<List<T>> if all instance values in the
original list were Success instances; or
Failure<List<T>> otherwise:  

static <A> Result<List<A>>
sequence(List<Result<A>> list);

*** Exercise 8-7 define more generictraverse
methods traversing a list of A while applying a
function from A to Result<B> and producing a
Result<List<B>>; signature:

static <A,B> Result<List<B>> traverse(List<A>
    list, Function<A, Result<B>> f);


8.3 Abstracting common list use cases

8.3.1 zipping and unzipping lists

*** Exercise 8-8 write zipWith combining elements
of two lists of "different" types to produce a new
list; signature:

static <A,B,C> List<C> zipWith(List<A> lst1,
    List<B> lst2, Function<A, FUnction<B,C>> f);

*** Exercise 8-9 writing "product" of 2 list;  ie,
  combining/combination

list("a", "b"),  list("1", "2")

==> list("a1", "a2", "b1", "b2");


*** Exercise 8-10 write unzip static method

<A,B> Tuple<List<A>, List<B>>
unzip(List<Tuple<A,B>> list);


*** Exercise 8-11 generalize unzip so it can
transform a list of any type into type of lists,
for example, given a list of Payment, should be 
able to produce a type of lists: one containing 
credit cards to make payments, and the other 
containing amounts

<A1,A2> Tuple<List<A1>, List<A2>>
unzip(Function<A, Tuple<A1,A2>> f);


8.3.2 Accessing elements by their index

*** Exercise 8-12 write a getAt method that takes
an index and returns corresponding element.
Should not throw exception when out of bound


*** Exercise 8-13 (Hard)
find a solution making fold-based version
terminate as soon as the result is found.

__ the zero element :

8.3.3 splitting list

*** Exercise 8-14 write splitAt method taking an
int and returning 2 lists by splitting the list at
the given position.  Do not throw
IndexOutOfBoundException.  Instead, index below 0
will be treated as 0, index above max will be
treated as max.

*** Exercise 8-15 (not so hard if done exercise
8-13)

implementing exercise 8-13 using a fold instead of
explicity recursion.


__ when NOT to use fold


8.3.4 search for sublists

*** Exercise 8-16 implement hasSubList to check
whether a list is sublist of another. 

public static <A> boolean hasSubsequence(List<A>
    list, List<A> sub);

8.3.5 Miscllaneous functions for working with
Lists

*** Exercise 8-17 create "groupBy" taking a
function from A to B and returning a Map, where
keys are the result of the function applied to
each element of the list and values are lists of
elements corresponding to each key.

Map<String, List<Payment>> map = list.groupBy(x ->
    x.name);


*** Exercise 8-18 write an unfold method taking a
starting element S and a function f from S to 
Result<Tuple<A, S>> and producing List<A> by
successively applying f to S value as long as the
result is Success.

List.unfold(0, 
    i -> i < 10 
       ? Result.success(new Tuple<>(i, i+1))
       : Result.empty());


*** Exercise 8-19 write "range" taking 2 integers
and producing a list of all integers greater than
or equal to the first and less than the second.

*** Exercise 8-20 write "exists" taking a function
from A to Boolean, returning true if list contains
at least 1 element satisfying that condition; NOT
use explicit recursion, but try to build on
methods already created.


*** Exercise 8-21 create "forAll" taking a
function from A to Boolean, returning true if all
elements satisfy condition


8.4 Automatic parallel processing of lists

8.4.1 not all computations can be parallelized

8.4.2 breaking list into sublists

*** Exercise 8-22 write divide(int depth) to
divide a list into number of sublists. List
divided into 2 sublists, and recursively,
regarding to depth.

List<List<A>> divide(int depth);

8.4.3 processing sublists in parallel

*** Exercise 8-23 write parFoldLeft in List<A>,
taking same parameters as foldLeft plus an
ExecutorService, and a function from B to B, and
returning Result<List<A>>.
The additional function will be used to assemble
results from sublists. 

Result<B> parFoldLeft(ExecutorService es, B
    identity, Function<B, Function<A, B>> func,
    Function<B, Function<B, B>> additionFunc);


*** Exercise 8-24 although mapping can be
implemented through a fold (thus can benefit from
automatic parallelization), it can also be
implemented in parallel without using a fold.  

create parMap to automatically apply a given
function to all elements of a list in parallel.

<B> Result<List<B>> parMap(ExecutorService es,
    Function<A, B>> g);


8.5 Summary



Chapter 9 - working with laziness

- importance of laziness
- implementing laziness in Java
- creating lazy list data structure: the Stream
- Optimizing lazy list by memoizing evaluated
values
- handling infinite streams


9.3 things cannot do without laziness

9.4 why not use Java 8 Stream ?

* defining own structure is far more rewading. In
  doing so, you'll learn and understand many
  things that you wouldn't even have thought of if
  you were using Java 8 Streams.


  ^^^ This has been the major points by the author
  writing this book.  

* Java 8 stream designed for parallelization in
mind; for this, some compromises were made, and
many functional methods are missing because they
would have made automatic parallelization more
difficult.

* Java 8 Streams are "stateful"; 

* Folding in Java 8 streams is a strict operation
* that causes evaluation of all elements.


9.5 creating a lazy list data structure

9.5.1 Memoizing evaluated values

The idea behind laziness is that you can save time
by evaluation data only when it's needed.

It implies you must evaluate data when it's first
accessed. 

"evaluation on demand" == evaluation as needed=
lazy evaluation


*** Exercise 9-1 write headOption to return the
evaluated head of the stream,  will be declared in
Stream parent class with signature:

public abstract Result<A> headOption();

9.5.2 manipulating streams

*** Exercise 9-2 create toList to convert a Stream
into List


*** Exercise 9-3 write take(n) to return first n
elements of a stream, and drop(n) to return
remaining stream after removing the first n
elements.  Have to ensure no evaluation occurs
while calling these methods.

public abstract Stream<A> taken(int n);
public abstract Stream<A> drop(int n);


*** Exercise 9-4 write takeWhile to return a
Stream containing all starting elements if as long
as the condition is met.

public abstract Stream<A> takeWhile(Function<A,
    Boolean> predicate);


*** Exercise 9-5 write dropWhile to return a
stream with front elements removed as long as
condition met.

public abstract dropWhile(Function<A, Boolean>
    predicate);


9.6 the true essence of laziness

*** Exercise 9-6 write exists method for Stream;
should cause elements to be evaluated only until
the condition is met.  If the condition is never
met, all elements will be evaluated.


9.6.1 folding streams

*** Exercise 9-7 create foldRight for streams,
will be similar to List.foldRight method, but
should take care of laziness


*** Exercise 9-8 create takeWhile in terms of
foldRight, verify how it behave on long lists.

*** Exercise 9-9 implement headOption using
foldRight


*** Exercise 9-10 write map in terms of foldRight,
verify this method doesn't evaluate any of
the stream elements.

*** Exercise 9-11 write filter in terms of foldRight,
verify this method doesn't evaluate any of
the stream elements.

*** Exercise 9-12 implement append in terms of
foldRight, should be non-strict in its argument


*** Exercise 9-13 implement flatMap in terms of
foldRight


*** Exercise 9-14 write find method that takes a
predicate (a function from A to Boolean), returns
Result<A>.  This will be a Success if an element
is found to match the predicate; or Empty
otherwise.


9.7 Hanlding infinite streams

*** Exercise 9-15 write repeat method taking an
object as parameter, and retuing an infinite
stream of the same object


*** Exercise 9-16 generalize "from" and "repeat"
methods by writing an "iterate" method that takes
2 params: a seed which will be used for the first
value, and a function that will compute the next
one. 

static <A> Stream<A> iterate(A seed, Function<A,
    A> f);


*** Exercise 9-17 write "fibs" that generates
infinite stream of Fibonacci numbers: 0, 1, 1, 2,
3, 5, 8, ...

*** Exercise 9-18 generalize "iterate" method;
write an "unfold" method taking a starting type S
and a function from S to Result<Tuple<A, S>>,
returning a stream of A.


Returning a Result makes it possible to indicate
whether the stream should stop or continue.


Using a state S means that the source of data
generations doesn't have to be of the same type as
the generated data. To apply this new method,
write new versions of "fibs" and "from" in
terms of "unfold", 

public static <A, S> Stream<A> unfold(S z,
    Function<S, Result<Tuple<A, S>>> f);


9.8 Avoiding null references and mutable fields


*** Exercise 9-19 Using foldRight to implement
various methods is asmart tech. Unfortunately, it
doesn't really work for filter.  If you test this
method with a predicate that is not matched by
more than 1,000 or 2,000 consecutive elements, it
will overflow the statck.

Using the new Stream class without null or mutable
fields, write a stack-safe filter method.


9.9 Summary




Chapter 10 - More data handling with trees

- understanding relationships between size,
  height, and depth in a tree structure
- relationship between insertion order and binary
  search tree structure
- traversing trees in various orders
- implementing binary search tree
- merging, folding, balancing trees.


10.1 The binary tree

- node (including terminal nodes)
- link
- branched
- subtree
- 

10.1.1 Balanced and unbalanced trees

- perfectly balanced tree
- imperfectly balanced tree
- totally unbalanced tree


10.1.2 Size, height, depth


10.1.3 Leafy trees

binary trees are sometimes represented in a
different way; 


terminal nodes are called "leaves", hence, the
name "leafy trees"


10.1.4 Ordered binary trees or binary search trees
(BST)

- ordered binary tree
- all elements in one branch have a lower value
than the root element, while all elements in the
other branch have a higher value than the root.
-by convention, elements with lower values than
root are on "left" branch, 

*** One very important consequence of the
definition of ordered binary tree is that they can
"never" contain "duplicates"

- allowing fast retrieveal or elements.



10.1.5 insertion order



10.1.6 Tree traversal order


__ Recursive traversal orders

__ non-recursive traversal orders


10.2 Implementing the binary search tree


*** Exercise 10-1  define an "insert" method to
insert a value into a tree. 

__ Tree structure is immutable and persistent, so
a new tree with the inserted value must be
constructed, leaving the original tree untouched.  

__ if the value is equal to the root, you must
return a new tree with the inserted value as the
root and the two roginal branches left unchanged.
Otherwise, a value lower than the root is inserted
in the "left" branch, and a value higher than the
root is inserted in the "right" branch.

Declare the method in the parent "Tree" class, and
implement it in both subclasses.  Signature:

public abstract Tree<A> insert(A a);


** Exercise 10-2  implement "member" method
checking if a specific element is in tree


start with T subclass
implemenation. have to compare the parameter with
the tree value (which means the value at the root
    of the tree).  

*** Exercise 10-3 write a static method takes a
vararg and insert all elements into an empty tree

public static <A extends Comparable<A>> Tree<A>
tree(A... elements);


*** Exercise 10-4 wirte "size", "height" method to
check attributes for a tree

public abstract int size();
public abstract int height();


*** Exercise 10-5 write "max", "min" methods for a
BST


10.3 Removing elements from a BST tree

*** Exercise 10-6 wirte "remove" .  this method
will take an element as parameter, if it presents
in tree, will be removed, and method returns a new
Tree without this element.


10.4 Merging arbitrary trees

*** Exercise 10-7 (HARD)
write "merge" signature as;

public abstract Tree<A> merge(Tree<A> a);


^^ the book has 7 pages for this.


10.5 Folding trees

10.5.1 folding with 2 functions:

*** Exercise 10-8 write foldLeft as

public abstract<B> B foldLeft(B identity, 
     Function<B, Function<A, B>> f,
     Function<B, Function<B, B>> g);


10.5.2 folding with 1 function

*** Exercise 10-9 write 3 methods to fold a tree:
foldInOrder, foldPreOrder, foldPostOrder.  Applied
to the tree in figurer 10.18, that is

1, 2, 3, 4, 5, 6, 7

==> 

- In Order:   1 2 3 4 5 6 7 
- pre order:  4 2 1 3 6 5 7
- post order: 1 3 2 5 7 6 4


10.5.3 which fold implementation to choose

*** Exercise 10-10 (HARD)
create a method to combine 2 BST trees and a root
to create a new tree.

Tree<A> tree(Tree<A> left, A a, Tree<A> right);


10.6 Mapping trees

*** Exercise 10-11
define "map" for BSTs; try to preserve the tree
structure if possible. for example, mapping a tree
of integers by squaring values might produce a
tree with a different structure; but mapping by
adding a constrant should not.


10.7 Balancing trees


10.7.1 Rotating trees

*** Exercise 10-12 
write rotateRight and rotateLeft to rotate tree in
both directions.

Be careful to preserve the branch order: left
elements must always be lower than the root; right
branch must always larger than the root.

Declare abstract methods in the parent class, make
them protected, because they will only be used
from inside of Tree class.

protected abstract Tree<A> rotateLeft();
protected abstract Tree<A> rotateRight();


*** Exercise 10-13
to balance the tree, need methods to transform a
tree into an ordered list.  write a method to
change a tree into an in-order list from "right"
to "left" (which means in descending order);  

for more practices, can also try "in-order left to
right), as well as methods for pre-order, and
post-order.

public List<A> toListInOrderRight();


10.7.2 Balancing trees using the Day-Stout-Warren
algorithm

*** Exercise 10-14
implement "balance" method to fully blaance any
tree. will be a static method taking the tree to
be balanced as its parameter.


10.7.3 Automatically blaancing trees


*** Exercise 10-15
transform the tree you've developed to make it
auto-balancing on insertions, merges, and
removals.


10.7.4 Solving the right problem

10.8 Summary




Chapter 11 - Solving real problems with advanced
trees


- avoiding stack overflow with self-balancing
trees
- red-black trees
- creating functional maps
- designing a functional priority queue


Red-black tree is a binary search tree (BST) with
some additions to its structure and a modified
"insertion" algorithm, which also balances the
result.


in 1978, Guibas and Sedgewich invented Red-Black
tree.

in 1999, Chris Okasaki published a "functional"
version of red-black tree algorithm in his book
"Purely Functional Data Structure"   (his Ph.D
thesis with the same title online, but not
programming stuff of course)

in 2014, Kimball Germane and Matthew Might,
   "Functional Pearl, Deletion: the curse of the
   red-black tree"  (online)

Unfortunately, Okasaki didn't describe "removal",
  which happens to be a far more complex process.  


11.1.2 inserting an element into the RB Tree

five pages for this


*** Exercise 11-1 
write "insert", balance, blacken methods for
implementing insertion into the RBT.

Unfortunately, Java doesn't implement "pattern
matching", so you'll have to use "conditional
instructions" instead


___ Removing elements from a Red-Black Tree

The implementation in Java is "too long" to
include in the book;  but it's included in the
accompanying code
(http://github.com/fpinjava/fpinjava), and it will
be used in the next exercise.


11.2 A use case for red-black tree: "maps"

(note, in Java, when pairs are large, the
"TreeMap" may be used for efficiency?  (need to
check hte source code of HashMap, TreeMap, etc)


11.2.1 Implementing Map

*** Exercise 11-2
complete "Map" class by implementing all methods


11.2.2 Extending maps

*** Exercise 11-3 
write "values" method in Map class that returns a
list of the values contained in the map in
"ascending key order"


11.2.3 Using Map with noncomparable keys

*** Exercise 11-4
implment a version of "Map" that works with
noncomparable keys.


11.3 Implementing a functional priority queue


11.3.1 the priority queue access protocol


11.3.2 priority queue use cases


11.3.3 implementaiton requirements


11.3.4 leftist heap data structure


11.3.5 implementing leftist heap


*** Exercise 11-5 
defined "add" method , make it instance method in
Heap class

public Heap<T> add(T element);


11.3.6 implementing queue-like interface

*** Exercise 11-6 
define "tail" method that returns what's left
after removing the head.

Result<Heap<A>> tail();


*** Exercise 11-7
implement "get" method taking an int param and
returing the nth element by priority order.

public abstract Result<A> get(int index);


11.4  a priority queue for noncomparable elements

*** Exercise 11-8
modify Heap class so that it can be used either
with Comparable elements or with a separate
Comparator.


*** Exercise 11-9 
implement "insert" method adding an element
without resorting to merge.

public abstract Heap<A> insert(A a);


*** Exercise 11-10
the above 11-9 author's solution has a bug, find
it and fix it.


11.5 Summary



********************************************

**** Skipping for now: 

Chapter 12 (handling state mutation in a
    functional way)
and

Chapter 13 (functional input/output)

********************************************



Chapter 12 and 13 are good, need to make them up.



Chapter 12 - Handling state mutation in a
functional way


- creating functional random number generator
- designing a generic API for handling state
mutation
- handling and composing state operations
- using recursive state operations
- generic state handling
- building a state machine

12.1 A functional random number generator

12.1.1 the random number generator interface

12.1.2 implementing the generator

*** Exercise 12-1
write a method in "Generator" class to return a
random positive integer lower than a value passed
as a parameter, but greater than or equal to 0.

public static Tuple<Integer, RNG> integer(RNG rng,
    int limit);

*** Exercise 12-2
write a method to return a list of n random
integers, it will also have to return the current
state, which translates to last RNG, so it can
generate the next integer:

Tuple<List<Integer>, RNG> integers(RNG rng, int
    length);


12.2 A generic API for handling state

12.2.1 working with state operations

*** Exercise 12-3
Use the "map" method to generate a random Boolean;
do this by creating a function in Random
interface:


*** Exercise 12-4
Implement a function to return a randomly
generated Double


12.2.2 Composing state operations


*** Exercise 12-5
Implement a function that takes an RNG and returns
a piar of integers.


static<A, B, C> Random<C> map2(Random<A> ra,
    Random<B> rb, Function<A, Function<B, C>> f);


*** Exercise 12-6 
Implement a function taking an RNG and returning a
list randomly generated integers

static <A> Random<List<A>> sequence(List<Random<A>
    rs);


12.2.3 Recursive state operations

*** Exercise 12-7
write "faltMap" method to implement
notMultipleOfFiveRnd function:

static <A, B> Random<B> flatMap(Random<A> s,
    Function<A, Random<B>> f);


*** Exercise 12-8 
implement map and map2 interms of flatMap


12.3 Generic state handling

*** Exercise 12-9
complete the "State" class by re-implementing the
methods of Random interface in a generic way.


12.3.1 State patterns

12.3.2 Building a state machine

*** Exercise 12-10
write an Atm class that simulates an automated
teller machine.  The inputs will be represented by
following interfaces:

public interface Input {
   Type type();
   boolean isDeposit();
   boolean isWithdraw();
   int getAmount();
   enum type { DEPOSIT, WITHDRAW) }
}

2 implementations 

public class Deposit implments Input {
  .
    .
    .
}

and Withdraw implements Input ....


*** Exercise 12-11
Modify the previous program so that errors such as
trying to widthdraw more than the account balance
are reported.


12.3.3 when to use state and the state machine

12.4 Summary



Chapter 13 Functional Input/Output


- applying effects safely from inside contexts
- adding effect application to Result and List 
- combining effects for Success and Failure
- reading data safely from Console, file, memory
with Reader abstraction
- handling input/output with IO type


13.1 Applying effects in context

13.1.1 what are effects?

13.1.2 implementing effects

*** Exercise 13-1
write forEach method in List class to take an
effect and apply it to all the elements of the
list


13.1.3 more-powerful effects for failures


___ Why logging is dangerous?  In FP, won't see
much logging. Because FP makes logging mostly
"useless".  FP are build by "composing" "pure"
functions, meaning fucntions that always return
the same value given the same argument.  So there
can't be any surprise.  

On the other hand, logging is ubiquitous in
"imperative programming" because imperative cannot
predict the output for a given input.  


"Logging" is like saying "I don't know what the
program might produce at this point, so I'll write
it to a log file.  If everything goes well, I
won't need this log file, but if sth goes wrong,
I'll be able to look at the logs to see what the
program's state was at that point."


In FP, logging not needed. If all functions are
correct, whcih can generally be proved, you don't
need to know the intermediate states.  


^^^ Note, the author was talking about those
"debug logging";   but clicks-recording/logging
are business data, not intermediate states, hence
still need :)


*** Exercise 13-2
in chapter 7, wrote forEachOrException method in
Result type that worked like forEach in Empty and
Success;  with the addition that it would return a
worked Result.Empty, and that returned a
Result.Success<Exception> in Failure .

Write forEachOrFail method to return
Result<String> with the exception message, instead
of exception itself.


13.2 Reading data

13.2.1 Reading data from console

*** Exercise 13-3
write a program that keeps asking user to input an
integer ID, first name, last name, and later
displays the list of people to console.

Data input stops as soon as users enter a blank
ID.


*** Exercise 13-4
Write ReadFile program, similar to ReadConsole,
but that reads from a file containing the
entries, each one on separate line.  An
example file from
http://github.com/fpinjava/fpinjava


13.2.3 Testing with input


13.3 Really functional Input/Output


13.3.1 how can input/output be made fully
functions?

13.3.2 implementing purely functional input/output

13.3.3 Combining IO

*** Exercise 13-5
create a method in IO interface to allow group 2
IO instances into one.  This method will be called
"add", and will have a default implementation.

default IO add(IO io);


13.3.4 Handling input with IO interface

*** Exercise 13-6 
define map method in IO<A> to take a function from
A to B and return IO<B>;  make a default
implementation in IO interface.


*** Exercise 13-7
write a flatMap emthod to take a function from A
to IO<B>, and return IO<B>


13.3.5 Extending the IO type

*** Exercise 13-8
implement "repeat" method, in IO interface

static <A> IO<List<A>> repeat(int n, IO<A> io);


13.3.6 making IO type stack-safe

*** Exercise 13-9 
in order to experiment with blowing stack;  create
a forever method taking an IO and returning a new
IO executing the argument in an endless loop

static <A, B> IO<B> forever(IO<A> ioa);


13.4 Summary





Chapter 14 - Sharing mutable state with actors

(no exercises) <-- would these be the author's old
projects that he worked on, years ago? 

- actor model
- asynchronous messaging
- build actor framework
- apply/use the framework
- optimizing actor performance


14.1 Actor model


14.1.1 asynchronous messaging

14.1.2 handling parallelization

14.1.3 handling actor state mutation


14.2 build Actor Framework

14.2.1 limitation of this framework

14.2.2 design the framework interfaces

14.2.3 AbstractActor implementation


14.3  use the actor framework

14.3.1 implementing ping-pong example

14.3.2 more serious exmaple: running a computation
in parallel

14.3.3 reordering the results


14.3.4 fixing the performance problem

14.4 Summary 



Chapter 15 - Solving common problems functionally

- using assertions
- reading property files
- adapting imprerative libraries


15.1 using assertions to validate data


15.2 reading properties from file


15.2.1 loading the property file

15.2.2 reading properties as strings


15.2.3 producing better error message


15.2.4 reading properties as lists


15.2.5 reading enum values


15.2.6 reading properties of arbitrary types


15.3 converting an imperative program: the XML
Reader

^^^^  good projects for practicing FP in Java,
because of the familiar topic


15.3.1 listing the necessary functions


15.3.2 composing the functions and applying an
effect


15.3.3 implementing the functions

15.3.4 making the program even more functional


15.3.5 fixing the argument type problem


15.3.6 make element-processing function a
parameter

15.3.7  handle errors on element name

15.4 Summary




Appendix B: Monads

Explain monads in simple terms.


- do not define it, just learn the concepts


The author gave the following to describe the
concepts in monads (in Java FP context)

  g(x, y) = x / y


Not "pure function", due to Div/0 exception;

how to make it "total function".

2 ways to make g total function:  1) change the
domain, making it a function of (integer, non-null
integer),

or, 2) change the co-domain, making it a function of
(integer, integer), to (integer | exception)


==> to implement 1st option, have to create a new
type: NonNullInteger, which is possible

==> to implement 2nd option, have to create a new
type: IntegerOrException.


Functional programmers prefer the 2nd approach.


But, if you change function g to return
IntegerOrException, you can no longer compose it
with f.  

More precisely, f.g(x), or f(g(x) if you prefer
this notation, will no longer compile because the
types no longer match.

"The solution" is to create a computational
context in which the functions can be safely
executioed.  In metaphors, can think of the
"context" as a safe box.

So, what you need is:

- a safe box
- a way to put the paratmeter value inside the box
- a way to put modified function inside the box so
that it can be applied to the parameter value.


That is it, the result will be a box containing
the result of the function.


Because Java doesn't offer such a safe box, need
slight modify the above requirements.

^^ the type of "safe box" you can use for
returning a result or nothing is "Option" you
developed in Chapter 6, or (better) the "Result"
type from Chapter 7.  

In standard Java 8, it could be "Optional" type.

*******************************************
  In functional programming, the "method" that
  will put a value into the "box" is generally
  named "unit" or "return", but you name it "of"
  for "Option" and "Result", as Java 8 designeers
  did for "Optional".  That doesn't change
  anything.
*******************************************


The method allows you to apply the
"modified function" to the value inside the "box"
is called "flagMap")


For example, normal method could look like
following:

public static char firstChar(String a) {
  if (a == null || a.length() == 0) {
    throw new IllegalArgumentException();
  } else {
    return a.charAt(0);
  }
}


to make that fucntion return either the first
character or nothing, you must change it into
following:

public static Optional<Character> firstChar(String
    a) {
  return a == null || a.length == 0 
    ? Optional.empty()
    : Optional.of(a.charAt(0));
}


To use the tools, you need to put a String in
context:

Optional<String> data = Optional.of("hello");


Optional<Character> character =
data.flatMap(thisClass::firstChar);


**************************************************
*******  The "unit" ("of" method) and "flagMap"
emthods are all that is needed to make Optional a
monad.
**************************************************


The author gave some good examples on why Java
Optional class not good enough for Functional
Programming.


At the end, he said: lots of other methods could
have been added to Optional, and are missing, and
you cannot add them because Optional is "final".
That is why the author develped "Option" "monad"
(the safe box);  

at the same time, Optional is almost useless
because it cannot carry the reason for the
"abscence of data".  This is why the author
created another "monad", "Result", which is
roughly corresponding to Scala's "Try" class.





Appendix C: where to go from here


C.1 Choosing a new language: Haskell,  Scala
(GitHub has more activities on Scala than
Haskell, but Scala seems like losing to Kotlin),
and Kotlin (or possibly a forth, Frege).


^^^ Note, obviously, the autho chose "Kotlin",  in
his book  "The Joy of Kotlin" (he said, the
concept 80% overlapping "Functional Programming
in Java")


C.1.2 Haskell

Using Haskell, you will have to fight against it
to write imperative programs.

Learning Haskell will "train" your mind into
functional thinking like no other languages can
do.

Even if you continue using Java, prototyping
functions with Haskell is really rewarding.


*** The main problem with Haskell (for Java
programmer) is that everything is "new".  You
won't be able to use any of your regular Java
tools or any of the numerious libraries you have
been used to in Java.  

^^^ Time consuming;  also, "no projects on daily
basis, and then no progress on Haskell"!


C.1.2 Scala

Some people already doing these in current
project...


Scala is NOT a strictly functional language. you
can write both imperative and functional styles.  


Writing functional problems in Scala is a
discipline (same for JavaScript, right?  JS was
modeled from Scheme another type of Lisp,
functional programming;  but many people
writing JS imperative ways);


But, Scala may be good for Java programmers to
write FP.


However, the Kotlin is a new black horse.


C.1.3 Kotlin


Kotlin has many "functional-friendly features",
such as "function types" (allowing following): 


    (A) -> (B) -> C  instead of 

    Function<A, Function<B, C>>> (in Java)

data classes (automatically generating
constructors, accessors, and equals, hashCode
methods)

and implicit method calls -- allowing to call
function as f(x) instead of more verbose Java
syntax f.apply(x).

Kotlin is fully compatbile with Java, and possible
to mix Java and Kotlin in the same project.


But, Kotlin does not have functional collections,
it just uses Java collections



C.1.4 Frege

Not matured yet,

    it's "Haskell on JVM",  close to Haskell, can
    mix with Java , like Scala cna.


if you decide to learn Haskell or Kotlin, why not
Frege.





My commits notes:  from the study from the above: 


*********************************

> FP in Java
>
> 2 important Appendex:
>
> 1) Monad -- explained in Java examples
>
>    a unit method ("of") and "flatMap" method --
>
>    the Option type, and the Result type (modeled 
        by Scala'"'"'s Try type), by the author
>    in previous chapters exercises
>
>
> 2) What languages to use for Fucntional Programming?
>
>    Looks like the Kotlin is the one to go
>
>    but still something to learn, Kotlin,  however, 
  >    Android uses Kotlin as well.
>
>
> Anyway, for both Scala, Kotlin,
>
>     it'"'"'s a displine to write Functional 
>     Programming, because both Scala and Kotlin
> allow "imperative" and "functional" style.
>
>     Just like the JavaScript.
>
>
> Now, Haskell is "pure functional language", but 
> a lot "new stuff" to learn, and then,
> totally give up the existing Java libraries and 
> other stuff
>
>
> it says, using Haskell to Model "functions" is 
> really rewarding.
>
>
> so,  all boiled down to
>
> 1) MASTER 1 thing FIRST
>
> 2) Focus, Concentrate
>
>
> 3) LIMITED TIME  (and also limited resource)
>
>
> KNOW the BEST WHAT you Know!
> 
>

*************************************************




^^^ What is the BENEFIT to SCAN the Books !!!!


(sometimes, the author(s) put stuff at the end,
 should you study it or not )



































