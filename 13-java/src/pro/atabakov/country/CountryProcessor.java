package pro.atabakov.country;

import java.util.ArrayList;
import java.util.List;

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
