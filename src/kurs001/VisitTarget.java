package kurs001;


public enum VisitTarget {
    GYM("спортивный зал"), POOL("бассейн"), GROUP("групповые занятия");

    private final String name;

    VisitTarget(String name){ //модификатор private не нужен он и так private потому что ENUMS
        if(name == null || name.length() < 1){
            throw new IllegalArgumentException("Неверное название категории (строка или null или слишком коротка");
        }
        this.name = name;
    }

}
