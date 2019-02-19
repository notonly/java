

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



*** Exercise 5-5 Implement a "dropWhile" method 








