package pro.atabakov.lambda.random;

import java.util.List;
import java.util.Random;

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
