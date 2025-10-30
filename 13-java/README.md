# Java

Completed tasks:

![12%](https://progress-bar.xyz/12)

## 1. Private Constructor

In terms of inheritance, what is the effect of keeping a constructor private?

<details>
<summary>Answer</summary>

We can hide the constructor from the outside world, but we can still create an instance of the class using the static class. 
Useful for creating a builder or singleton pattern.

#### Implementation

```java
public class MyClass {
    public int num = 1;
    
    private MyClass(){}
    
    private MyClass(int num){
        this.num = num;
    }

    static class Builder extends MyClass {
        public Builder() {
            super(14);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(new MyClass.Builder().num); // 14
    }
}
```

</details>

<hr/>

## 2. Return from Finally

In Java, does the `finally` block get executed if we insert a return statement inside the try block of a `try-catch-finally`?

<hr/>

## 3. Final, etc.

What is the difference between final, finally, and finalize?

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

There is a class Country that has methods getContinent() and getPopulation(). Write a function int getPopulation(List<Country> countries,
String continent) that computes the total population of a given continent, given a list of all countries and the name of a continent.

<hr/>

## 8. Lambda Random

Using Lambda expressions, write a function `List<Integer> getRandomSubset(List<Integer> list)` that returns a random subset of arbitrary
size. All subsets (including the empty set) should be equally likely to be chosen.

<hr/>
