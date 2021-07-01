package animals.optimized.naive;

import java.util.LinkedList;

public class AnimalShelter {
    static final class Cat extends Animal {

    }
    static final class Dog extends Animal {
    }


    LinkedList<Dog> dogs = new LinkedList<>();
    LinkedList<Cat> cats = new LinkedList<>();
    private int order = 0;

    public void enqueue(Animal a) {
        a.setOrder(order);
        order++;
        if (a instanceof Dog d) {
            dogs.addLast(d);
        } else if (a instanceof Cat c) {
            cats.addLast(c);
        }
    }

    public Animal dequeueAny() {
        if (dogs.size() == 0) {
            return dequeueCat();
        } else if (cats.size() == 0) {
            return dequeueDog();
        }
        Dog dog = dogs.peek();
        Cat cat = cats.peek();
        if (dog.isOlderThan(cat)) {
            return dogs.poll();
        } else {
            return cats.poll();
        }
    }

    public Animal peek() {
        if (dogs.size() == 0) {
            return cats.peek();
        } else if (cats.size() == 0) {
            return dogs.peek();
        }
        Dog dog = dogs.peek();
        Cat cat = cats.peek();
        if (dog.isOlderThan(cat)) {
            return dog;
        } else {
            return cat;
        }
    }

    public int size() {
        return dogs.size() + cats.size();
    }

    public Dog dequeueDog() {
        return dogs.poll();
    }

    public Dog peekDogs() {
        return dogs.peek();
    }

    public Cat dequeueCat() {
        return cats.poll();
    }

    public Cat peekCats() {
        return cats.peek();
    }
}
