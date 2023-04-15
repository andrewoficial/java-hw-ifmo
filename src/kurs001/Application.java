package kurs001;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {
    public static void main(String[] args) {
        /* Настройка какие типы абонементов куда пропускают что бы не было хардкода */

        System.out.println(AbonementsType.DAY.isAllow(VisitTarget.GYM));
        //AbonementsType.DAY.addTarget(VisitTarget.GYM);
        //AbonementsType.DAY.addTarget(VisitTarget.GROUP);

        //AbonementsType.FULL.addTarget(VisitTarget.POOL);
        //AbonementsType.FULL.addTarget(VisitTarget.GYM);
        //AbonementsType.FULL.addTarget(VisitTarget.GROUP);

        /* Создание дат для тестовых данных */
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String dateInString = "15-09-2023";
        Date date = new Date();
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String dateInString2 = "01-01-2023";
        Date date2 = new Date();
        try {
            date2 = formatter.parse(dateInString2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        /* Создание "посетителей" для тестовых данных */
        Person human01 = new Person("Andrew", "Ivanov", 1995);
        Person human02 = new Person("Ivan", "Смирнов", 1992);
        Person human03 = new Person("Nikolay", "Кузнецов", 2000);
        Person human04 = new Person("Юлия", "Крат", 1995);
        Person human05 = new Person("Георгий", "Попов", 1993);
        Person human06 = new Person("Сергей", "Васильев", 1995);
        Person human07 = new Person("Стас", "Михайлов", 1987);
        Person human08 = new Person("Ингнат", "Коновалов ", 1999);
        Person human09 = new Person("Денис", "Мухин", 1998);
        Person human10 = new Person("Ян", "Сидоров", 1997);
        Person human11 = new Person("Лев", "Исаев", 1996);
        Person human12 = new Person("Георгий", "Русаков", 1994);
        Person human13 = new Person("Сергей", "Новиков", 1994);
        Person human14 = new Person("Лазарь", "Стрелков", 1993);
        Person human15 = new Person("Мстислав", "Жданов", 2004);
        Person human16 = new Person("Варлам", "Прохоров", 2007);
        Person human17 = new Person("Ярослав", "Юргель", 2002);
        Person human18 = new Person("Иван", "Рябов", 2002);
        Person human19 = new Person("Иосиф", "Шаков", 1987);
        Person human20 = new Person("Галина", "Вишнякова", 2001);
        Person human21 = new Person("Авигея", "Большакова", 2000);
        Person human22 = new Person("Марина", "Романова", 1990);
        Person human23 = new Person("Татьяна", "Макарова", 1989);
        Person human24 = new Person("Луиза", "Громова", 1988);
        Person human25 = new Person("Майя", "Галкина", 1999);
        Person human26 = new Person("Жанна", "Кононова", 2000);
        Person human27 = new Person("Ольга", "Жданова", 2001);
        Person human28 = new Person("Александра", "Зимина", 2002);
        Person human29 = new Person("Варвара", "Русакова", 2003);
        Person human30 = new Person("София", "Брагина", 2004);
        Person human31 = new Person("Алеся", "Михеева", 2005);
        Person human32 = new Person("Марина", "Давыдова", 1998);
        Person human33 = new Person("Стелла", "Молчанова", 1999);
        Person human34 = new Person("Александр", "Сорокин", 1997);
        Person human35 = new Person("Игорь", "Воронцов", 1988);
        Person human36 = new Person("Емельян", "Туров", 2003);

        /* Создание абонементов для тестовых данных */
        Abonement abn01 = new Abonement (human01);
        Abonement abn02 = new Abonement (human02, date, AbonementsType.DAY);
        Abonement abn03 = new Abonement (human03, date, AbonementsType.DAY);
        Abonement abn04 = new Abonement (human04, date, AbonementsType.DAY);
        Abonement abn05 = new Abonement (human05, date, AbonementsType.DAY);
        Abonement abn06 = new Abonement (human06, date, AbonementsType.DAY);
        Abonement abn07 = new Abonement (human07, date2, AbonementsType.DAY);
        Abonement abn08 = new Abonement (human08, date2, AbonementsType.DAY);
        Abonement abn09 = new Abonement (human09, date2, AbonementsType.DAY);
        Abonement abn10 = new Abonement (human10, date2, AbonementsType.DAY);
        Abonement abn11 = new Abonement (human11, date2, AbonementsType.DAY);
        Abonement abn12 = new Abonement (human12, date, AbonementsType.FULL);
        Abonement abn13 = new Abonement (human13, date, AbonementsType.FULL);
        Abonement abn14 = new Abonement (human14, date, AbonementsType.FULL);
        Abonement abn15 = new Abonement (human15, date, AbonementsType.FULL);
        Abonement abn16 = new Abonement (human16, date, AbonementsType.FULL);
        Abonement abn17 = new Abonement (human17, date2, AbonementsType.FULL);
        Abonement abn18 = new Abonement (human18, date2, AbonementsType.FULL);
        Abonement abn19 = new Abonement (human19, date, AbonementsType.FULL);
        Abonement abn20 = new Abonement (human20);
        Abonement abn21 = new Abonement (human21);
        Abonement abn22 = new Abonement (human22);
        Abonement abn23 = new Abonement (human23);
        Abonement abn24 = new Abonement (human24);
        Abonement abn25 = new Abonement (human25, date, AbonementsType.FULL);
        Abonement abn26 = new Abonement (human26, date, AbonementsType.FULL);
        Abonement abn27 = new Abonement (human27, date, AbonementsType.FULL);
        Abonement abn28 = new Abonement (human28, date, AbonementsType.DAY);
        Abonement abn29 = new Abonement (human29, date, AbonementsType.FULL);
        Abonement abn30 = new Abonement (human30, date, AbonementsType.FULL);
        Abonement abn31 = new Abonement (human31, date, AbonementsType.DAY);
        Abonement abn32 = new Abonement (human32, date, AbonementsType.DAY); //Марина
        Abonement abn33 = new Abonement (human33, date, AbonementsType.FULL);
        Abonement abn34 = new Abonement (human34, date, AbonementsType.DAY);
        Abonement abn35 = new Abonement (human35, date, AbonementsType.FULL);
        Abonement abn36 = new Abonement (human36, date, AbonementsType.DAY);

        /* Создание объекта финтнес */
        Fitnes zdanie01 = new Fitnes();

        /* Запуск процессов */
        zdanie01.visit(abn01, VisitTarget.GYM);
        zdanie01.visit(abn02, VisitTarget.GYM);
        zdanie01.visit(abn03, VisitTarget.GYM);
        zdanie01.visit(abn04, VisitTarget.GYM);
        zdanie01.visit(abn05, VisitTarget.GYM);
        zdanie01.visit(abn06, VisitTarget.GYM);
        zdanie01.visit(abn07, VisitTarget.GYM);
        zdanie01.visit(abn08, VisitTarget.GYM);
        zdanie01.visit(abn09, VisitTarget.GYM);
        zdanie01.visit(abn10, VisitTarget.GYM);
        zdanie01.visit(abn11, VisitTarget.GYM);
        zdanie01.visit(abn12, VisitTarget.GYM);
        zdanie01.visit(abn13, VisitTarget.GYM);
        zdanie01.visit(abn14, VisitTarget.GYM);
        zdanie01.visit(abn15, VisitTarget.GYM);
        zdanie01.visit(abn16, VisitTarget.GYM);
        zdanie01.visit(abn17, VisitTarget.GYM);
        zdanie01.visit(abn18, VisitTarget.GYM);
        zdanie01.visit(abn19, VisitTarget.GYM);
        zdanie01.visit(abn20, VisitTarget.GYM);
        zdanie01.visit(abn21, VisitTarget.GYM);
        zdanie01.visit(abn22, VisitTarget.GYM);
        zdanie01.visit(abn23, VisitTarget.GYM);
        zdanie01.visit(abn24, VisitTarget.GYM);

        /* Вывод информации */
        zdanie01.printHumans();
    }
}
