package animals.naive;

import java.util.LinkedList;
import java.util.Optional;

public class AnimalShelter {

    enum AnimalType {
        Cat, Dog
    }

    static class Animal {
        AnimalType type;

        int age = 0;

        public Animal(AnimalType type, int age) {
            this.type = type;
            this.age = age;
        }
    }


    private final LinkedList<Animal> animals = new LinkedList<>();

    public void enqueue(Animal animal) {

        Optional<Animal> animalToIndex = animals.stream().filter(a -> animal.age > a.age).findFirst();

        if (animalToIndex.isEmpty()) {
            animals.addLast(animal);
            return;
        }

        animals.add(animals.indexOf(animalToIndex.get()), animal);
    }

    public Animal dequeueAny() {
        return animals.pollFirst();
    }

    public Animal dequeueDog() {
        return dequeueAnimal(AnimalType.Dog);
    }

    private Animal dequeueAnimal(AnimalType animal) {
        Optional<Animal> optionalAnimal = animals.stream().filter(i -> i.type == animal).findFirst();
        if (optionalAnimal.isPresent()) {
            Animal a = optionalAnimal.get();
            animals.remove(a);
            return a;
        } else {
            return null;
        }
    }

    public Animal dequeueCat() {
        return dequeueAnimal(AnimalType.Cat);
    }
}
