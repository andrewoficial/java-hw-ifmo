package kurs001;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Abonement {
    private static long abonementsCount = 0; //Общий счетчик выданных абонементов (потом удобно будет в б.д как ключ использовать)
    private long number = 0;
    private Date creDateTime = new Date(); //Неявно final ведь есть только геттер
    private Date expDateTime = new Date(creDateTime.getTime() + (1000 * 60 * 60 * 24));

    private LocalTime allowStart = LocalTime.parse( "08:00" );
    private LocalTime allowEnd = LocalTime.parse( "22:00" );
    private final boolean pool;
    private final boolean gym;
    private final boolean groupTrain;

    private final Person owner;

    private final AbonementsType type;


    public Abonement(Person owner){
        if(owner == null){
            throw new IllegalArgumentException("Передан неверный владелец абонемента");
        }
        gym = true;
        groupTrain = false;
        pool = true;
        LocalTime allowStart = LocalTime.parse( "08:00" );
        LocalTime allowEnd = LocalTime.parse( "22:00" );
        Abonement.abonementsCount = Abonement.abonementsCount + 1;
        number = Abonement.abonementsCount;
        this.owner = owner;
        this.type = AbonementsType.ONCE;
    }
    public Abonement(Person owner, Date exp, AbonementsType type){
        if(owner == null){
            throw new IllegalArgumentException("Передан неверный владелец абонемента");
        }
        if(type == null){
            throw new IllegalArgumentException("Передан неверный тип абонемента");
        }
        gym = true;
        groupTrain = true;
        expDateTime = exp;
        allowStart = LocalTime.parse( "08:00" );
        Abonement.abonementsCount = Abonement.abonementsCount + 1;
        number = Abonement.abonementsCount;

        if(type == AbonementsType.FULL){
            pool = true;
            allowEnd = LocalTime.parse( "22:00" ); //22:00
        }else if(type == AbonementsType.DAY){//Вторая проверка на случай увеличения видов абонементов
            pool = false;
            allowEnd = LocalTime.parse( "16:00" ); //16:00
        }else{
            pool = false;
        }

        this.owner = owner;
        this.type = type;
    }


    public Date getExpDateTime() {
        return expDateTime;
    }

    public LocalTime getAllowStart() {
        return allowStart;
    }

    public LocalTime getAllowEnd() {
        return allowEnd;
    }

    public boolean isPool() {
        return pool;
    }

    public boolean isGym() {
        return gym;
    }

    public boolean isGroupTrain() {
        return groupTrain;
    }

    public long getNumber() {
        return number;
    }

    public Person getOwner() {
        return owner;
    }

    //функция вывода информации об абонементе (нет в задании использую для отлабки)
    public void printInfo(){
        System.out.println("Информация об абонементе №" + this.number);
        System.out.println("Тип абонемента:" + this.type);
        System.out.println("Разрешено посещение с:" + this.allowStart + " по " + this.allowEnd);
        System.out.println("Данные о владельце:");
        System.out.println("    " + this.owner.getName() + " " + this.owner.getSurname());
        System.out.println("    Год рождения:" + this.owner.getBirthYear());
        System.out.println("Разрешено посещать бассейн:" + this.pool);
        System.out.println("Разрешено посещать зал:" + this.gym);
        System.out.println("Разрешено посещать групповые занятия:" + this.groupTrain);
        System.out.println("Дата создания абонемента:" + this.creDateTime);
        System.out.println("Дата окончания абонемента:" + this.expDateTime);
    }
}
