package animals.optimized.naive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnimalShelterTest {

    @Test
    void pushRandom() {
        AnimalShelter animalShelter = new AnimalShelter();

        animalShelter.enqueue(new AnimalShelter.Cat());
        animalShelter.enqueue(new AnimalShelter.Dog());
        animalShelter.enqueue(new AnimalShelter.Dog());
        animalShelter.enqueue(new AnimalShelter.Cat());
        animalShelter.enqueue(new AnimalShelter.Cat());
        animalShelter.enqueue(new AnimalShelter.Cat());
        animalShelter.enqueue(new AnimalShelter.Dog());
        animalShelter.enqueue(new AnimalShelter.Dog());

        Animal animal = animalShelter.dequeueAny();
        assertTrue(animal instanceof AnimalShelter.Cat);
        animal = animalShelter.dequeueAny();
        assertTrue(animal instanceof AnimalShelter.Dog);
        animal = animalShelter.dequeueCat();
        assertTrue(animal instanceof AnimalShelter.Cat);
        animal = animalShelter.dequeueDog();
        assertTrue(animal instanceof AnimalShelter.Dog);
    }

}
