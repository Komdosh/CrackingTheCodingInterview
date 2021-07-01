package animals.naive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalShelterTest {

    @Test
    void pushRandom() {
        AnimalShelter animalShelter = new AnimalShelter();

        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.Dog, 15));
        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.Cat, 4));
        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.Dog, 10));
        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.Dog, 23));
        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.Cat, 4));
        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.Cat, 7));
        animalShelter.enqueue(new AnimalShelter.Animal(AnimalShelter.AnimalType.Cat, 1));

        AnimalShelter.Animal animal = animalShelter.dequeueAny();
        assertEquals(AnimalShelter.AnimalType.Dog, animal.type);
        assertEquals(23, animal.order);

        animal = animalShelter.dequeueAny();
        assertEquals(AnimalShelter.AnimalType.Dog, animal.type);
        assertEquals(15, animal.order);

        animal = animalShelter.dequeueCat();
        assertEquals(AnimalShelter.AnimalType.Cat, animal.type);
        assertEquals(7, animal.order);

        animal = animalShelter.dequeueDog();
        assertEquals(AnimalShelter.AnimalType.Dog, animal.type);
        assertEquals(10, animal.order);
    }

}
