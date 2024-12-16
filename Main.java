import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ObjectMapper objectMapper = new ObjectMapper();
        MovieCollection movies = objectMapper.readValue(new File("movies.json"), MovieCollection.class);

        for (Movie movie : movies.getMovies()) {
            System.out.println("Название: " + movie.getName());
            System.out.println("Год выпуска: " + movie.getYear());
            System.out.println("Описание: " + movie.getDescription());
            System.out.println(movie.getDirector());
            System.out.println("Актеры:");
            for (Cast cast : movie.getCast()) {
                System.out.println("     Актер: " + cast.getFullName());
                System.out.println("     В роли: " + cast.getRole());
            }
            System.out.println("");
            System.out.println("----------------------------------------");
        }

        while (true) {
            System.out.println("Выберите следущее действие");
            System.out.println("1 - Поиск фильма.");
            System.out.println("2 - Сортированные фильмы по году выпуска.");
            System.out.println("3 - Сортированные фильмы по названию.");
            System.out.println("4 - Сортированные фильмы по режиссеру.");
            System.out.println("5 - Поиск актеров.");
            System.out.println("0 - Выход.");

            int action = 0;

            try {
                action = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введите цифру, а не букву.");
                scanner.nextLine();
                continue;
            }
            if (action == 0) {
                System.out.println("Выход");
                break;
            }
            if (action == 1) {
                searchFilm();
                break;
            } else if (action == 2) {
                sortMoviesYear();
                break;
            } else if (action == 3) {
                sortMoviesName();
                break;
            } else if (action == 4) {
                sortMoviesDirector();
                break;
            } else if (action == 5) {
                SearchFilm.Main();
            } else {
                System.out.println("Такого выбора нет.");
            }

        }
    }

    public static void searchFilm() throws IOException {
        Scanner scanner = new Scanner(System.in);
        ObjectMapper objectMapper = new ObjectMapper();
        MovieCollection movies = objectMapper.readValue(new File("movies.json"), MovieCollection.class);

        System.out.println("Введите название вашего фильма: ");
        String searchWord = scanner.nextLine();
        List<Movie> result = searchMovieNyName(movies.getMovies(), searchWord);

        if (!result.isEmpty()){
            for (Movie movie : result) {
                System.out.println("Название: " + movie.getName());
                System.out.println("Год выпуска: " + movie.getYear());
                System.out.println("Описание: " + movie.getDescription());
                System.out.println(movie.getDirector());
                System.out.println("Актеры:");
                for (Cast cast : movie.getCast()) {
                    System.out.println("     Актер: " + cast.getFullName());
                    System.out.println("     В роли: " + cast.getRole());
                }
                System.out.println("");
                System.out.println("----------------------------------------");
            }
        } else {
            System.out.println("По вашему запросу не найдено фильмов.");
        }
    }

    public static List<Movie> searchMovieNyName(List<Movie> movies, String searchTerm) {
        return movies.stream()
                .filter(movie -> movie.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static void sortMoviesYear() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MovieCollection movie = objectMapper.readValue(new File("movies.json"), MovieCollection.class);

        List<Movie> movies = movie.getMovies();
        Collections.sort(movies, Comparator.comparingInt(Movie::getYear));

        for (Movie movie1 : movies) {
            System.out.println("");
            System.out.println("Название: " + movie1.getName());
            System.out.println("Год выпуска: " + movie1.getYear());
            System.out.println("Описание: " + movie1.getDescription());
            System.out.println(movie1.getDirector());
            System.out.println("Актеры:");
            for (Cast cast : movie1.getCast()) {
                System.out.println("     Актер: " + cast.getFullName());
                System.out.println("     В роли: " + cast.getRole());
            }
            System.out.println("");
            System.out.println("----------------------------------------");
        }
    }

    public static void sortMoviesName() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MovieCollection movie = objectMapper.readValue(new File("movies.json"), MovieCollection.class);

        List<Movie> movies = movie.getMovies();
        Collections.sort(movies, Comparator.comparing(Movie::getName));

        for (Movie movie1 : movies) {
            System.out.println("");
            System.out.println("Название: " + movie1.getName());
            System.out.println("Год выпуска: " + movie1.getYear());
            System.out.println("Описание: " + movie1.getDescription());
            System.out.println(movie1.getDirector());
            System.out.println("Актеры:");
            for (Cast cast : movie1.getCast()) {
                System.out.println("     Актер: " + cast.getFullName());
                System.out.println("     В роли: " + cast.getRole());
            }
            System.out.println("");
            System.out.println("----------------------------------------");
        }
    }

    public static void sortMoviesDirector() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MovieCollection movie1 = objectMapper.readValue(new File("movies.json"), MovieCollection.class);

        List<Movie> movies = movie1.getMovies();
        Collections.sort(movies, Comparator.comparing(movie -> movie.getDirector().getFullName()));

        for (Movie movie2 : movies) {
            System.out.println("");
            System.out.println("Название: " + movie2.getName());
            System.out.println("Год выпуска: " + movie2.getYear());
            System.out.println("Описание: " + movie2.getDescription());
            System.out.println(movie2.getDirector());
            System.out.println("Актеры:");
            for (Cast cast : movie2.getCast()) {
                System.out.println("     Актер: " + cast.getFullName());
                System.out.println("     В роли: " + cast.getRole());
            }
            System.out.println("");
            System.out.println("----------------------------------------");
        }
    }
}
