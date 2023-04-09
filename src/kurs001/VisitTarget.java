package kurs001;

import java.time.LocalTime;

public enum VisitTarget {
    GYM("спортивный зал"), POOL("бассейн"), GROUP("групповые занятия");

    private final String name;

    private VisitTarget(String name){
        //ToDO проверки
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
