package kurs001;

import java.time.LocalTime;
import java.util.Date;

public class Abonement {
    private static long abonementsCount = 0; //Общий счетчик выданных абонементов (потом удобно будет в б.д как ключ использовать)
    private long number;
    private Date creDateTime = new Date(); //Неявно final ведь есть только геттер
    private Date expDateTime = new Date(creDateTime.getTime() + (1000 * 60 * 60 * 24));

    private final Person owner;
    private final AbonementsType type;

    public Abonement(Person owner){
        if(owner == null){
            throw new IllegalArgumentException("Передан неверный владелец абонемента");
        }
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
        if(exp == null){
            throw new IllegalArgumentException("Передана неверная дата");
        }
        expDateTime = exp;
        Abonement.abonementsCount = Abonement.abonementsCount + 1;
        number = Abonement.abonementsCount;
        this.owner = owner;
        this.type = type;
    }


    public Date getExpDateTime() {
        return expDateTime;
    }

    public LocalTime getAllowStart() {
        return this.type.getAllowStart();
    }

    public LocalTime getAllowEnd() {
        return this.type.getAllowEnd();
    }

    public boolean isAllow(VisitTarget tg) {
        if(tg == null){
            throw new IllegalArgumentException("Передана неверная цель визита");
        }
        return this.type.isAllow(tg);
    }

    public Person getOwner() {
        return owner;
    }

    /*
    //функция вывода информации об абонементе (нет в задании использую для отладки)
    public void printInfo(){
        System.out.println("Информация об абонементе №" + this.number);
        System.out.println("Тип абонемента:" + this.type);
        System.out.println("Разрешено посещение с:" + this.getAllowStart() + " по " + this.getAllowEnd());
        System.out.println("Данные о владельце:");
        System.out.println("    " + this.owner.getName() + " " + this.owner.getSurname());
        System.out.println("    Год рождения:" + this.owner.getBirthYear());
        System.out.println("Разрешено посещать бассейн:" + isAllow(VisitTarget.POOL));
        System.out.println("Разрешено посещать зал:" + isAllow(VisitTarget.GYM));
        System.out.println("Разрешено посещать групповые занятия:" + isAllow(VisitTarget.GROUP));
        System.out.println("Дата создания абонемента:" + this.creDateTime);
        System.out.println("Дата окончания абонемента:" + this.expDateTime);
    }
    */
}
