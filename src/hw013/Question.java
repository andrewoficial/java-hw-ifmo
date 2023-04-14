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
        // методы класса Cat и его родителей,
        // методы интерфейсов Eatable (потому что он указан) (при этом доступны только эти методы, а не все ...
        //      ...методы класса, который implements Eatable)
        // методы интерфейсов Runnable (потому что он implements у Animal)
        // методы интерфейсов Serializable (потому что его extends Eatable. А Сat implements Eatable)
        // (т.е. Cat, Animal, Object, Runnable, Eatable)
    }

    public static <T extends Animal & Serializable> void void02(T object) {
        // ВОПРОС: методы каких типов можно вызвать у object ???
        // методы класса Animal и его родителей,
        // методы интерфейсов Serializable (потому что он указан)
        // методы интерфейсов Runnable (потому что он implements у Animal)
        // (т.е. Animal, Object, Runnable, Serializable )
    }

    public static <T extends Serializable & Runnable> void void03(T object) {
        // ВОПРОС: методы каких типов можно вызвать у object ???
        // методы класса Object,
        // методы интерфейсов Serializable
        // методы интерфейсов Runnable
        // (т.е. Object, Serializable, Runnable)
    }

    public static void main(String[] args) {
        Task.</* ВОПРОС: какие типы можно указать ??? */>void01(/* ВОПРОС: какие типы можно передать ??? */);
        Task.<Cat>void01(Cat, Kitten);
        //<Cat> потому что он разрешен и implements Eatable
        //(Cat, Kitten) потому что можно передать объекты этого класса и его потомков
        Task.<Kitten>void01(Kitten);
        //<Kitten> потому что он наследует Cat, который разрешен и implements Eatable
        //(Kitten) потому что можно передать объекты этого класса и его потомков
        //Больше ничего не подоходит

        Task.</* ВОПРОС: какие типы можно указать ??? */>void02(/* ВОПРОС: какие типы можно передать ??? */);
        Task.<Cat>void01(Cat, Kitten);
        //<Cat> потому что он наследник Animal и implements Eatable, который implements Serializable
        //(Cat, Kitten) потому что можно передать объекты этого класса и его потомков
        Task.<Kitten>void01(Kitten);
        //<Kitten> потому что он наследник Cat который наследник Animal и implements Eatable, который extends Serializable
        //(Kitten) потому что можно передать объекты этого класса и его потомков
        Task.<Dog>void01(Dog);
        //<Dog> потому что он наследник Animal и implements Serializable & Runnable
        //(Dog) потому что можно передать объекты этого класса и его потомков
        //Больше ничего не подоходит


        Task.<Dog>void03(/* ВОПРОС: какие типы можно передать ??? */);
        Task.<Dog>void01(Dog);
        //<Dog> потому что он implements Serializable & Runnable
        //(Dog) потому что можно передать объекты этого класса и его потомков
        Task.<Cat>void01(Cat, Kitten);
        //<Cat> потому что он наследник Animal, и implements Eatable, который extends Serializable
        //(Cat, Kitten) потому что можно передать объекты этого класса и его потомков
        Task.<Kitten>void01(Kitten);
        //<Kitten> потому что он наследник Cat который наследник Animal, и implements Eatable, который extends Serializable
        //(Kitten) потому что можно передать объекты этого класса и его потомков
        //Больше ничего не подоходит
    }
}
