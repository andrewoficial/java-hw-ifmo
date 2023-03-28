package hw06;

import hw06.travel.Human;
import hw06.travel.Mountain;
import hw06.travel.TravelGroup;

public class Application {
    public static void main(String[] args) {
        Mountain goraOne = new Mountain("SuperGora", "SuperStrana", 800.5);
        Mountain goraTwo = new Mountain("justMountain", "justCountry", 555.1);
        Mountain goraThree = new Mountain("gora", "strana", 100.1);

        Human [] array1 = new Human [3];
        array1[0] = new Human("Ivan", "Ufa, Gagarin Street, 104-1-90");
        array1[1] = new Human("Maksim", "London, Some street, 105-2-91");
        array1[2] = new Human("Olga", "Berlin, Dunno Street, 000-1-1");

        TravelGroup groupOne = new TravelGroup(false, array1, goraOne);
        groupOne.printGroupInfo();

        TravelGroup groupTwo = new TravelGroup(true, goraTwo);
        groupTwo.addMember(new Human("Oleg", "Krasnoznamensk, Lenina Street, 1"));
        groupTwo.addMember(array1[1]);
        groupTwo.printGroupInfo();

        TravelGroup groupThree = new TravelGroup(true, goraThree);
        groupThree.addMember(new Human("Masha", "Pekin, Lenina Street, 2"));
        groupThree.addMember(array1[0]);
        groupThree.printGroupInfo();

    }

}
