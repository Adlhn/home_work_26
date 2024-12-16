import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SearchFilm {

    private static List<Movie> movies;

    public static void Main() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        movies = objectMapper.readValue(new File("movies.json"), MovieCollection.class).getMovies();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие: ");
            System.out.println("1 - Найти фильмы по имени актера.");
            System.out.println("2 - Найти фильмы по имени режиссера.");
            System.out.println("3 - Найти фильмы по году выпуска.");
            System.out.println("4 - Найти фильмы и роль актера.");
            System.out.println("5 - Показать всех актеров с их ролями.");
            System.out.println("0 - Выход.");
            int action = scanner.nextInt();
            scanner.nextLine();

            if (action == 1) {
                System.out.println("Введите имя актера:");
                String actorName = scanner.nextLine();
                searchFilmsActor(actorName);
            } else if (action == 2) {
                System.out.println("Введите имя режиссера:");
                String directorName = scanner.nextLine();
                searchFilmsDirector(directorName);
            } else if (action == 3) {
                System.out.println("Введите год выпуска:");
                int year = scanner.nextInt();
                searchFilmsYear(year);
            } else if (action == 4) {
                System.out.println("Введите имя актера:");
                String actorName = scanner.nextLine();
                searchFilmsRoles(actorName);
            } else if (action == 5) {
                searchActorsRoles();
            } else {
                System.out.println("Такого выбора нет.");
            }
            if (action == 0) {
                break;
            }
        }
    }

    public static void searchFilmsActor(String actorName) {
        List<Movie> result = movies.stream()
                .filter(movie -> movie.getCast().stream()
                        .anyMatch(cast -> cast.getFullName().equalsIgnoreCase(actorName)))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("Фильмы с вашим актером не найдены.");
        } else {
            System.out.println("Фильмы с вашим актером:");
            result.forEach(movie -> System.out.println(movie.getName()));
        }
    }

    public static void searchFilmsDirector(String directorName) {
        List<Movie> result = movies.stream()
                .filter(movie -> movie.getDirector().getFullName().equalsIgnoreCase(directorName))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            System.out.println("Фильмы с вашим режиссером не найдены.");
        } else {
            System.out.println("Фильмы с вашим режиссером:");
            result.forEach(movie -> System.out.println(movie.getName()));
        }
    }

    public static void searchFilmsYear(int year) {
        List<Movie> result = movies.stream()
                .filter(movie -> movie.getYear() == year)
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            System.out.println("Фильмы выпущенные в " + year + " не найдены.");
        } else {
            System.out.println("Фильмы выпущенные в " + year + ":");
            result.forEach(movie -> System.out.println(movie.getName()));
        }
    }

    public static void searchFilmsRoles(String actorName) {
        Map<String, String> result = movies.stream()
                .flatMap(movie -> movie.getCast().stream()
                        .filter(cast -> cast.getFullName().equalsIgnoreCase(actorName))
                        .map(cast -> Map.entry(movie.getName(), cast.getRole())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a));
        if (result.isEmpty()) {
            System.out.println("Ваш актер не снимался в фильмах.");
        } else {
            System.out.println("Ваш актер снимался в :");
            result.forEach((movie, role ) -> System.out.println(movie + " в роли " + role));
        }
    }

    public static void searchActorsRoles() {
        Set<String> result = movies.stream()
                .flatMap(movie -> movie.getCast().stream()
                        .map(cast -> cast.getFullName() + " - "+ cast.getRole()))
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Все актеры и их роли:");
        result.forEach(System.out::println);
    }
}
