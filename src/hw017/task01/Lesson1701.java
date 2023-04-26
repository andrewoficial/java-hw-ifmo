package hw017.task01;

import ru.itmo.lessons.lesson13.bounded.Bus;
import ru.itmo.lessons.lesson13.bounded.Car;
import ru.itmo.lessons.lesson13.bounded.Repaintable;
import ru.itmo.lessons.lesson13.bounded.Vehicle;

import java.util.HashMap;

public class Lesson1701 {

    public static void main(String[] args) {
        Unit unit01 = new Unit();
        Unit unit02 = new Unit();

        unit01.setEnemy(unit02);
        unit02.setEnemy(unit01);

        unit01.move(new GoAction()) // метод принимает на вход действия движения
                .move(new StopAction())
                .move(new RunAction())
                .move(new StopAction());

        unit01.attack(new HeadAttack()) // метод принимает на вход действия атаки
                .attack(new LegAttack())
                .attack(new HeadAttack());

        unit01.save("headattack", new HeadAttack()) // сохранение действий в map юнита
                .save("stopaction", new StopAction())
                .save("runaction", new RunAction());

        // в методах move и attack вызывается основное действие переданного объекта,
        // при этом у GoAction, StopAction, RunAction должен быть доступ к текущему юниту
        // при этом у HeadAttack, LegAttack быть доступ к противнику

        // в программе предполагается появление типов, использующих аналогичный механизм,
        // но не являющихся типом Unit
        House house = new House();
        house.act(new Construction()).act(new Repair());


        // в качестве значений можно передать Action<Unit> и его подтипы
        HashMap<String, Action<Unit>> actions = new HashMap<>();
        actions.put("headattack", new HeadAttack());
        actions.put("runaction", new RunAction());
        actions.get("headattack").action(unit01); // при извлечении из Map будут доступны методы Action<Unit>,
        // но использоваться реализация конкретного класса (в данном случае реализация HeadAttack)


        // в качестве значений можно передать Vehicle и его подтипы
        HashMap<String, Vehicle> vehicleHashMap = new HashMap<>();
        vehicleHashMap.put("car01", new Car(Repaintable.Color.RED, "car01"));
        vehicleHashMap.put("bus01", new Bus("bus01"));
        vehicleHashMap.get("bus01").repair(); // при извлечении из Map будут доступны методы Vehicle,
        // но использоваться реализация конкретного класса (в данном случае реализация Bus)





    }
}
