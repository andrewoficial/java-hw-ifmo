package hw008;

import hw008.transport.*;

public class Application {
    public static void main(String[] args) {

    Transport tomas = new Train("A5544", "red", 2);
    Transport bmw = new Car("A234BC", "black");
    Transport bus01 = new Bus("AA12", "red");

    Car volvo = new Car("4HKL04", "Black");
    Car bently = new Car("4NNL04", "white");
    Train leny = new Train("ABC00", "red", 5);
    leny.addCarriage(3);

    Car moskvith = new Car("4HKL55", "red");
    Car myCar = new Car("1235", "white");
    Car ypurCar = new  Car("74536346", "orange");
    Car ownCar = new  Car("00000", "white");
    Car herCar = new  Car("11111", "white");

    RepairStation rp = new RepairStation();

        System.out.println(bmw.getColor());
    rp.addTransport(tomas);
    rp.addTransport(bmw);
    rp.addTransport(bus01);
    rp.addTransport(volvo);
    rp.addTransport(bently);
    rp.addTransport(leny);
    rp.addTransport(moskvith);
    rp.addTransport(myCar);
    rp.addTransport(ypurCar);
    rp.addTransport(ownCar);
    rp.addTransport(herCar);

    rp.reapirAll();
        System.out.println(bmw.getColor());



    }
}
