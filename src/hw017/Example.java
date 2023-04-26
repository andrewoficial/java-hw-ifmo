package hw017;

interface D{ void dvoid();}

class A implements D {
    public int plus(int a, int b){ // перегруженный plus
        return a + b;
    }

    public int plus(int a){ // перегруженный plus
        return a + a;
    }

    public int plus(){ // перегруженный plus
        return 300;
    }

    @Override
    public void dvoid() {
        System.out.println("dvoid in A");
    }
}

class B extends A {
    @Override
    public void dvoid() { // переопределение метода
        System.out.println("dvoid in B");
    }
}
public class Example {
    public static void main(String[] args) {
        // полиморфизм:
        // 1. перегруженные методы (plus)
        // 2. полиморфизм наследования (dvoid)

        // раннее связывание (этап компиляции)
        // перегруженные методы,
        // методы без переопределения
        // static методы

        // позднее связывание (этап выполнения)
        // полиморфизм наследования

        A a = new A();
        System.out.println(a.plus(12));
        System.out.println(a.plus(12, 56));

        D d01 = new A();
        d01.dvoid(); // реализация A

        D d02 = new B();
        d02.dvoid(); // реализация B
    }
}
