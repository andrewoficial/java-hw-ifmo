package kurs001;

import java.time.LocalTime;

public enum AbonementsType {


    ONCE("08:00", "22:00", new VisitTarget[ ]{VisitTarget.GYM, VisitTarget.POOL}),
    DAY("08:00", "16:00", new VisitTarget[ ]{VisitTarget.GYM, VisitTarget.GROUP}),
    FULL("08:00", "22:00", new VisitTarget[ ]{VisitTarget.POOL, VisitTarget.GYM, VisitTarget.GROUP});

    //Нет необходимости лишний дергать LocalTime.parse
    //private LocalTime allowStart = LocalTime.parse( "08:00" );
    private final LocalTime allowStart;
    private final LocalTime allowEnd;
    private VisitTarget[] allowedPlaces = new VisitTarget[3];
    private int placeCounter = 0;
    AbonementsType(String start, String end){//Доступен только внутри перечисления
        if(start == null || start.length() != 5){
            throw new IllegalArgumentException("Передана неверная строка с описанием времени начала (null или не равна 5");
        }
        if(end == null || end.length() != 5){
            throw new IllegalArgumentException("Передана неверная строка с описанием времени истечения (null или не равна 5");
        }
        this.allowStart = LocalTime.parse( start );
        this.allowEnd = LocalTime.parse( end );
    }

    AbonementsType(String start, String end, VisitTarget[] arr){//Доступен только внутри перечисления
        if(start == null || start.length() != 5){
            throw new IllegalArgumentException("Передана неверная строка с описанием времени начала (null или не равна 5");
        }
        if(end == null || end.length() != 5){
            throw new IllegalArgumentException("Передана неверная строка с описанием времени истечения (null или не равна 5");
        }
        this.allowStart = LocalTime.parse( start );
        this.allowEnd = LocalTime.parse( end );
        this.allowedPlaces = arr;
    }
    public void addTarget(VisitTarget tg) {
        if((! this.isAllow(tg)) && placeCounter < allowedPlaces.length){
            allowedPlaces[placeCounter] = tg;
            placeCounter++;
        }else{
            System.out.println("Массив разрешенных мест переполнен");
        }
    }

    public boolean isAllow(VisitTarget tg){
        if(tg == null){
            throw new IllegalArgumentException("Передана неверная цель визита");
        }
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


