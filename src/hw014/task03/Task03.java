package hw014.task03;

import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        // 1. Дописать класс Employee
        List<Employee> employeeList = Employee.employeeGenerator(20);

        // 2. Отсортировать employeeList:
        // 2.1. по имени
        // 2.2. по имени и зп
        // 2.3. по имени, зп, возрасту, компании

        // Для сортировки используйте тип Comparator

        // FIXME: после объявления всех необходимых методов классе Employee:
        //  1. Создать класс NameComparator implements Comparator<Employee> (класс определит логику сравнения объектов Employee по имени)
        //  2. Интерфейс обяжет Вас написать реализацию метода public int compare(Employee o1, Employee o2):
        //     2.1. у объектов o1 и o2 получить значения свойства name (метод getName) и сравнить (логику сравнения выбрать самостоятельно)
        //     2.3. вернуть из метода 0, если значения свойств name равны,
        //     вернуть из метода отрицательное число, если значение свойства name объекта o1 меньше значения свойства name объекта o2,
        //     вернуть из метода положительное число, если значение свойства name объекта o1 больше значения свойства name объекта o2
        //  3. Создать класс SalaryComparator implements Comparator<Employee> (класс определит логику сравнения объектов Employee по зарплате)
        //  4. Интерфейс обяжет Вас написать реализацию метода public int compare(Employee o1, Employee o2):
        //     2.1. у объектов o1 и o2 получить значения свойства salary (метод getSalary) и сравнить (логику сравнения выбрать самостоятельно)
        //     2.3. вернуть из метода 0, если значения свойств salary равны,
        //     вернуть из метода отрицательное число, если значение свойства salary объекта o1 меньше значения свойства salary объекта o2,
        //     вернуть из метода положительное число, если значение свойства salary объекта o1 больше значения свойства salary объекта o2,
        //  5. Создать класс AgeComparator implements Comparator<Employee> (класс определит логику сравнения объектов Employee по возрасту).
        //  Реализация метода public int compare(Employee o1, Employee o2) похожа на реализацию метода класса SalaryComparator
        //  6. Создать класс CompanyComparator implements Comparator<Employee> (класс определит логику сравнения объектов Employee по компании).
        //  Реализация метода public int compare(Employee o1, Employee o2) похожа на реализацию метода класса NameComparator
        //  7.  в текущем методе main объявить объекты классов NameComparator, SalaryComparator, AgeComparator и CompanyComparator,
        //  например, Comparator<Employee> comparator01 = new NameComparator();
        //  8. для сортировки по нескольким критериям объединить компараторы,
        //  например, Comparator<Employee> comparator02 = new NameComparator().thenComparing(new SalaryComparator());
        //  9. Вызвать метод Collections.sort(), передать в него список employeeList и нужный (согласно заданию) компаратор (объединенный или нет)
    }
}