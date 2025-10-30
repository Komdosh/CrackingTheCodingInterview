# Java

Completed tasks:

![100%](https://progress-bar.xyz/100)

## 1. Private Constructor

In terms of inheritance, what is the effect of keeping a constructor private?

<details>
<summary>Solution</summary>

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
<summary>Solution</summary>

Yes, the `finally` block will be executed anyway.

It is better shown in compiled bytecode.
If we have a return statement inside `finally` block, then we execute every branch,
store the result values, but always will return a statement in finally. If the final block doesn't have a return
statement, then we will return the value from the try or catch block.

The only case when the final block will not be executed is when an unhandled error occurs. Like JVM stops or Thread is
killed.

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
<summary>Solution</summary>

`final` - is a marker of a class, method, or a variable that can't be overridden.

`finally` - is a block of code that will be executed after `try-catch-finally` block.

`finalize` - is a method called when an object is garbage collected. Shall be used as a cleanup point. BUT it is marked
for removal in Java 9, it actually creates a number of issues with gc. So java has moved to another approach using
`java.lang.ref.Cleaner` and `java.lang.ref.PhantomReference`.

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

Explain the difference between templates in C++ and generics in Java.

<details>
<summary>Solution</summary>

In C++, templates are compile-time types, while in Java, generics are runtime types.

In C++, after compilation, templates are replaced with concrete types, which helps ensure type safety. Even at runtime,
we know exactly what type we are working with.

In Java, however, type erasure occurs, so we don’t know the exact type at runtime. We need to perform casting to make
sure we are working with the correct type.

So C++ can and java can't:

- Use primitive types in templates
- Create an instance of a template type
- Use static class in template, because C++ will compile two different versions of class.

</details>
<hr/>

## 5. TreeMap, HashMap, LinkedHashMap

Explain the differences between TreeMap, HashMap, and LinkedHashMap. Provide an example of when each one would be best.

<details>
<summary>Solution</summary>

`TreeMap` - is a sorted map (balanced tree (red-black tree in case of java)), which means that keys are always sorted.
Keys should implement `Comparable` interface.

`HashMap` - store keys in the hash table, so it doesn't have an order, but it uses a tree node in case of collisions (if
a threshold is reached).

`LinkedHashMap` - store keys in the hash table, so it doesn't have an order, but every entry stores a link to the
previous and next entry (double linked list).

#### Implementation

```java

public class MapUsage {
    TreeMap<String, String> treeMap = new TreeMap<>();
    HashMap<String, String> hashMap = new HashMap<>();
    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();

    static void main() {
        MapUsage mapUsage = new MapUsage();
        mapUsage.treeMapUsage();
        mapUsage.hashMapUsage();
        mapUsage.linkedHashMapUsage();
    }

    private void treeMapUsage() {
        for (int i = 0; i < 5; i++) {
            treeMap.put("" + i, "value" + i); // O(log(n))
        }

        for (String key : treeMap.descendingKeySet()) { // keys are always sorted
            System.out.println(key);
        }

        System.out.printf(treeMap.get("1")); // O(log(n)) - this map doesn't use hash methods, so we have to traverse the whole tree
    }

    private void hashMapUsage() {
        for (int i = 0; i < 5; i++) {
            hashMap.put("" + i, "value" + i); // O(1)
        }

        // keys are stored in the hash table, so it doesn't have an order,
        // but it can be transformed in a tree in case of collisions
        for (String key : hashMap.keySet()) {
            System.out.println(key);
        }

        System.out.println(hashMap.get("1")); // O(1) - amortized cost, but O(log(n)) in case of collisions
    }

    private void linkedHashMapUsage() {
        for (int i = 0; i < 5; i++) {
            linkedHashMap.put("" + i, "value" + i); // O(1) - but entry stores a link to the previous entry and next (double linked list)
        }

        // keys are stored in the hash table, so it doesn't have an order,
        // but it can be transformed in a tree in case of collisions
        for (String key : linkedHashMap.sequencedKeySet()) { // Just get the first inserted entry and iterate over
            System.out.println(key);
        }

        System.out.println(linkedHashMap.get("1"));  // O(1) - amortized cost, but O(log(n)) in case of collisions, traditional hashmap
    }
}
```

</details>
<hr/>

## 6. Object Reflection

Explain what object reflection is in Java and why it is useful

<details>
<summary>Solution</summary>

Object reflection is a way to access and manipulate objects at runtime. We can investigate the object metadata and
manipulate it. In some cases it can be used to bypass security restrictions. For example, we can call a private method
by
it's String name or read a private value of the object. But it is often a bad practice.

It can be used for a good reason, for example, to create a dynamic proxy, profiling, logging, testing. It is often used
in Annotation Processing.

#### Implementation

```java
public interface ExampleProcessor {
    String processData(String input);
}

class ExampleProcessorFirst implements ExampleProcessor {
    public String processData(String input) {
        return "Processed: " + input.toUpperCase();
    }

    public String firstName() {
        return "I'm first";
    }
}

class ExampleProcessorSecond implements ExampleProcessor {
    public String processData(String input) {
        return "Processed: " + input.toUpperCase();
    }

    public String secondName() {
        return "I'm second";
    }
}

public class ExampleProcessorFactory {
    public static ExampleProcessor getExampleProcessor(boolean isFirst) {
        if (isFirst) {
            return new ExampleProcessorFirst();
        } else {
            return new ExampleProcessorSecond();
        }
    }
}

public class ReflectionExample {
    static void main() {
        Object someProcessor = ExampleProcessorFactory.getExampleProcessor(false);

        // We don't know the class type at compile time
        Class<?> clazz = someProcessor.getClass();
        System.out.println("Class Name: " + clazz.getName());

        Arrays.stream(clazz.getMethods()).forEach(m -> {

            try {
                Class<?>[] parameterTypes = m.getParameterTypes();

                if (m.getDeclaringClass().getSimpleName().equals(clazz.getSimpleName())) {
                    System.out.println("I have a method: " + m.getName());

                    if (parameterTypes.length == 1 && parameterTypes[0].getName().equals("java.lang.String")) {
                        Object result = m.invoke(someProcessor, "Hello Reflection!");
                        // Print result (if the method returns something)
                        System.out.println("Result: " + result);
                    } else {
                        Object result = m.invoke(someProcessor);
                        System.out.println(result);
                    }
                }

            } catch (Exception e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
        });
    }
}
```

</details>

<hr/>

## 7. Lambda Expressions

There is a class `Country` that has methods `getContinent()` and `getPopulation()`. Write a function
`int getPopulation(List<Country> countries, String continent)` that computes the total population of a given continent,
given a list of all countries and the name of
a continent.

<details>
<summary>Solution</summary>

It's simple, just filter countries by continent, map stream to int stream of population, and sum it.

#### Implementation

```java
public class Country {
    String name;
    String continent;
    public int population;

    public Country(String name, String continent, int population) {
        this.name = name;
        this.continent = continent;
        this.population = population;
    }
}

public class CountryProcessor {
    static void main() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Russia", "Europe", 100_000_000));
        countries.add(new Country("USA", "North America", 300_000_000));
        countries.add(new Country("China", "Asia", 2_000_000_000));
        countries.add(new Country("Japan", "Asia", 120_000_000));
        countries.add(new Country("France", "Europe", 60_000_000));
        countries.add(new Country("Germany", "Europe", 80_000_000));
        countries.add(new Country("Italy", "Europe", 600_000_000));

        System.out.println("Europe: " + getPopulation(countries, "Europe"));
        System.out.println("Asia: " + getPopulation(countries, "Asia"));
    }

    static int getPopulation(List<Country> countries, String continent) {
        return countries.stream().filter(country -> continent.equals(country.continent))
                .mapToInt(country -> country.population)
                .sum();
    }
}
```

</details>

<hr/>

## 8. Lambda Random

Using Lambda expressions, write a function `List<Integer> getRandomSubset(List<Integer> list)` that returns a random
subset of arbitrary size. All subsets (including the empty set) should be equally likely to be chosen.


<details>
<summary>Solution</summary>

It's simple, just filter stream by random boolean value. We assume that Random class provides us a real random boolean
value (it's not, but let's pretend it is).

#### Implementation

```java
public class LambdaRandom {

    static void main() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        getRandomSubset(list).forEach(System.out::println);
    }

    static List<Integer> getRandomSubset(List<Integer> list) {
        Random random = new Random();
        return list.stream()
                .filter(_ -> random.nextBoolean())
                .toList();
    }
}
```

</details>

<hr/>
