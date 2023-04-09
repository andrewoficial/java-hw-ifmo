package kurs001;

import java.time.LocalTime;

public enum AbonementsType {
    /* Не смог передать массив с разрешенными местами в конструктор */

    ONCE("08:00", "22:00"),
    DAY("08:00", "16:00"),
    FULL("08:00", "22:00");

    private LocalTime allowStart = LocalTime.parse( "08:00" );
    private LocalTime allowEnd = LocalTime.parse( "22:00" );
    private VisitTarget[] allowedPlaces = new VisitTarget[3];
    private int placeCounter = 0;
    AbonementsType(String start, String end){//Доступен только внутри перечисления
        this.allowStart = LocalTime.parse( start );
        this.allowEnd = LocalTime.parse( end );
    }


    public void addTarget(VisitTarget tg) {
        for (int i = 0; i < allowedPlaces.length; i++) { //ToDO повтор кода убрать но не понял как вызвать isAllow
            if(allowedPlaces[i] == null){
                continue;
            }
            if(allowedPlaces[i] == tg){// == потому что ENUMS
                System.out.println("Такое разрешение уже имеется");
                return;
            }
        }
        if(placeCounter < allowedPlaces.length){
            allowedPlaces[placeCounter] = tg;
            placeCounter++;
        }else{
            System.out.println("Массив разрешенных мест переполнен");
        }
    }

    public boolean isAllow(VisitTarget tg){
        for (int i = 0; i < allowedPlaces.length; i++) {
            if(allowedPlaces[i] == null){
                continue;
            }
            if(allowedPlaces[i] == tg){// == потому что ENUMS
                return true;
            }
        }
        return false;
    }

    public LocalTime getAllowEnd() {
        return allowEnd;
    }

    public LocalTime getAllowStart() {
        return allowStart;
    }


}


