package hw014.task03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Employee implements Comparable{
    private String name;
    private String company;
    private int salary;
    private int age;

    // TODO: добавить необходимые конструкторы, геттеры, сеттеры и другие методы
    public Employee(String name, String company, int salary, int age){
        if(name == null || name.length() < 2){
            throw new IllegalArgumentException("Wrong name. Got:" + name);
        }
        if(company == null || company.length() < 2){
            throw new IllegalArgumentException("Wrong company. Got:" + company);
        }
        if(salary < 14500 || salary > 99999999){
            throw new IllegalArgumentException("Wrong salary. Got:" + salary);
        }
        if(age < 16 || age > 150){
            throw new IllegalArgumentException("Wrong age. Got:" + age);
        }
        this.age=age;
        this.name=name;
        this.salary=salary;
        this.company=company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // TODO: дописать реализацию метода для создания списка объектов класса Employee.
    //  Объекты Employee создавать со случайными значениями:
    //  возраст от 21 до 60;
    //  диапазон ЗП на Ваш выбор.
    //  name - случайное значения из массива names
    //  company - случайное значения из массива companies
    public static List<Employee> employeeGenerator(int num) { //  num - количество объектов в списке
        String[] names = {"Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max", "Jenifer", "Linda", "Elizabeth"}; // массив с именами
        String[] companies = {"Microsoft", "IBM", "Google", "General Electric", "Siemens", "Samsung", "Apple"}; // массив с названиями компаний

        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //ChatGPT помог с (int) (Math.random() * (300000 - 14500 + 1) + 14500)
            employees.add(new Employee(names[(int) (Math.random() * 11)], companies[(int) (Math.random() * 7)], (int) (Math.random() * (300000 - 14500 + 1) + 14500), (int) (Math.random() * (150 - 16 + 1) + 16)));
        }
        // создание объектов, наполнение списка

        return employees;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && age == employee.age && Objects.equals(name, employee.name) && Objects.equals(company, employee.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, company, salary, age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object other) {
        if (other == null) {
            return -1;
        }
        if (!(other instanceof Employee)) {
            return -1;
        }

        // Compare by name
        int result = this.name.compareTo(((Employee) other).getName());
        if (result != 0) {
            return result;
        }

        // Compare by company
        result = this.company.compareTo(((Employee) other).getCompany());
        if (result != 0) {
            return result;
        }

        // Compare by salary
        result = Integer.compare(this.salary, ((Employee) other).getSalary());
        if (result != 0) {
            return result;
        }

        // Compare by age
        return Integer.compare(this.age, ((Employee) other).getAge());
    }


    public static class NameComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }

    public static class SalaryComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return (int) (o2.getSalary() - o1.getSalary());
        }
    }

    public static class AgeComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return (int) (o2.getAge() - o1.getAge());
        }
    }

    public static class CompanyComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getCompany().compareToIgnoreCase(o2.getCompany());
        }
    }
}