package hw018.task;

import hw014.task01.Fruit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class University {
    private List<Course> courses = new ArrayList<>();

    public boolean addCourse(Course course) {
        return courses.add(Objects.requireNonNull(course));
    }
    public boolean addCourses(List<Course> courses) {
        return this.courses.addAll(Objects.requireNonNull(courses));
    }

    public void upPrice(double newPrice, double currentPrice) {
        // метод увеличивает стоимость курсов (courses) на newPrice,
        // если текущая стоимость меньше currentPrice
        // использовать метод foreach коллекций
        Consumer<Course> changePrice = courses -> {
            courses.setPrice(courses.getPrice()<currentPrice ? courses.getPrice() * 2 : courses.getPrice());
        };
        courses.forEach(changePrice);
    }

    public void sortByDurationAndPrice(Comparator<Course> comparator) {
        // метод сортирует коллекцию courses по стоимости,
        // если курсы одинаковы по стоимости, сортирует по продолжительности

        //ToDo узнать как сделать
        Comparator<Course> compareByPrice = (course1, course2) -> (int) (course1.getPrice() - course2.getPrice());
        courses.sort(compareByPrice); // или Collections.sort(fruits, compareByPrice);
    }

    public void upDuration(double currentPrice) {
        // метод увеличивает продолжительность курсов (courses) на 1,
        // если их стоимость больше currentPrice
        // использовать метод foreach коллекций
        Consumer<Course> changeDuration = courses -> {
            courses.setDuration(courses.getPrice()<currentPrice ? courses.getDuration() + 1 : courses.getDuration());
        };
        courses.forEach(changeDuration);
    }
}