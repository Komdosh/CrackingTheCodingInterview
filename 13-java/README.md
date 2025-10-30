# Java

Completed tasks:

![25%](https://progress-bar.xyz/25)

## 1. Private Constructor

In terms of inheritance, what is the effect of keeping a constructor private?

<details>
<summary>Answer</summary>

We can hide the constructor from the outside world, but we can still create an instance of the class using the internal
static class, for example.
Useful for creating a builder or singleton pattern.

#### Implementation

```java
public class MyClass {
    public int num = 1;

    private MyClass() {
    }

    private MyClass(int num) {
        this.num = num;
    }

    static class Builder extends MyClass {
        public Builder() {
            super(14);
        }
    }
}

public class Main {
    static void main(String[] args) {
        System.out.println(new MyClass.Builder().num); // 14
    }
}
```

</details>

<hr/>

## 2. Return from Finally

In Java, does the `finally` block get executed if we insert a return statement inside the try block of a
`try-catch-finally`?

<details>
<summary>Answer</summary>

Yes, the `finally` block will be executed anyway.
It is better shown in compiled bytecode.
If we have a return statement inside `finally` block, then we execute every branch,
store the result values, but always will return a statement in finally. If finally block doesn't have a return
statement,
then we will return the value from try or catch block.

#### Implementation

Original code:

```java
public class FinallyBlock {
    @SuppressWarnings("ConstantValue")
    int someExceptionMethod() {
        if (true) {
            throw new RuntimeException("Some exception");
        }
        return 42;
    }

    int someMethod() {
        return 42;
    }

    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    int withFinallyBlock() {
        try {
            System.out.println("Executing someMethod");
            return someExceptionMethod();
        } catch (Exception exception) {
            System.out.println("Exception caught: " + exception.getMessage());
            return someMethod();
        } finally {
            System.out.println("Finally executed");
            return 0;
        }
    }

    static void main() {
        FinallyBlock finallyBlock = new FinallyBlock();
        System.out.println(finallyBlock.withFinallyBlock());
    }
}
```

Compiled bytecode:

```java
public class FinallyBlock {
    int someExceptionMethod() {
        throw new RuntimeException("Some exception");
    }

    int someMethod() {
        return 42;
    }

    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    int withFinallyBlock() {
        try {
            System.out.println("Executing someMethod");
            int var1 = this.someExceptionMethod(); // return is erased
            // return this.someExceptionMethod();; // if finally block doesn't have a return statement
        } catch (Exception exception) {
            System.out.println("Exception caught: " + exception.getMessage());
            int var2 = this.someMethod(); // return is erased
            // return this.someMethod(); // if finally block doesn't have a return statement
        } finally {
            System.out.println("Finally executed");
            return 0; // always use finally return statement if present
        }
    }

    static void main() {
        FinallyBlock finallyBlock = new FinallyBlock();
        System.out.println(finallyBlock.withFinallyBlock());
    }
}
```

</details>

<hr/>

## 3. Final, etc.

What is the difference between final, finally, and finalize?

<details>
<summary>Answer</summary>

`final` - is a marker of a class or a method that can't be overridden.

`finally` - is a block of code that will be executed after `try-catch-finally` block.

`finalize` - is a method called when an object is garbage collected. Shall be used as a cleanup point. BUT it is marked
for removal in Java 9, it actually creates a number of issues with gc. So java has moved to another approach using `java.lang.ref.Cleaner` and `java.lang.ref.PhantomReference`.

#### Implementation

```java
public class FinalDifference {
    @SuppressWarnings({"removal", "FinalizeCalledExplicitly"})
    final void tryToFinalize() {
        System.out.println("Call to finalize");
        try {
            this.finalize();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Finalize finished");
        }
    }

    static void main() {
        FinalDifference finalDifference = new FinalDifference();
        finalDifference.tryToFinalize();
    }
}
```

</details>

<hr/>

## 4. Generics vs. Templates

Explain the difference between templates in C ++ and generics in Java.

<hr/>

## 5. TreeMap, HashMap, LinkedHashMap

Explain the differences between TreeMap, HashMap, and LinkedHashMap. Provide an example of when each one would be best.

<hr/>

## 6. Object Reflection

Explain what object reflection is in Java and why it is useful

<hr/>

## 7. Lambda Expressions

There is a class Country that has methods getContinent() and getPopulation(). Write a function int getPopulation(
List<Country> countries,
String continent) that computes the total population of a given continent, given a list of all countries and the name of
a continent.

<hr/>

## 8. Lambda Random

Using Lambda expressions, write a function `List<Integer> getRandomSubset(List<Integer> list)` that returns a random
subset of arbitrary
size. All subsets (including the empty set) should be equally likely to be chosen.

<hr/>
