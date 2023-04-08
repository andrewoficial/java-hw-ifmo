package kurs001;

public class Person {
    private String name;
    private String surname;

    private int birthYear;

    public Person(String name, String surname, int birthYear) {
        if(name == null || name.length() < 2){
            throw new IllegalArgumentException("Слишком короткое имя");
        }

        if(surname == null || surname.length() < 2){
            throw new IllegalArgumentException("Слишком короткая фамилия");
        }

        if(birthYear > 3000 || birthYear < 1800){
            throw new IllegalArgumentException("Неверный год рождения");
        }
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getBirthYear() {
        return birthYear;
    }
}
