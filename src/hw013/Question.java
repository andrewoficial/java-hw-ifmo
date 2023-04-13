package hw013;

import java.io.Serializable;

public class Question {
}

interface Eatable extends Serializable{}
interface Runnable {}
class Animal implements Runnable{}
class Cat extends Animal implements Eatable{}
class Kitten extends Cat{}
class Dog extends Animal implements Serializable{}
class Task {
    public static <T extends Cat & Eatable> void void01(T object) {
        // ВОПРОС: методы каких типов можно вызвать у object ???
        // методы класса Cat и его родителей, имплементирующих интерфейс Eatable (т.е. только Cat)
    }

    public static <T extends Animal & Serializable> void void02(T object) {
        // ВОПРОС: методы каких типов можно вызвать у object ???
        // методы класса Animal и его родителей, имплементирующих интерфейс Serializable (т.е. никакие? о.о)
    }

    public static <T extends Serializable & Runnable> void void03(T object) {
        // ВОПРОС: методы каких типов можно вызвать у object ???
        // методы, имплементирующих интерфейсы Serializable и  Runnable(т.е. Dog)
    }

    public static void main(String[] args) {
        Task.</* ВОПРОС: какие типы можно указать ??? */>void01(/* ВОПРОС: какие типы можно передать ??? */);
        //              класс Cat                                       класс Cat и Kitten


        Task.</* ВОПРОС: какие типы можно указать ??? */>void02(/* ВОПРОС: какие типы можно передать ??? */);
        //               ??? никакие ???                                ???


        Task.<Dog>void03(/* ВОПРОС: какие типы можно передать ??? */);
        //                класс Dog и возможно всех кто  Serializable
    }
}
