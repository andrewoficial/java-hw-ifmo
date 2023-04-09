package kurs001;

import java.time.LocalTime;
import java.util.Date;

public class Fitnes {
    private Abonement[] bassAbonements = new Abonement [20];
    private Abonement[] gymmAbonements = new Abonement [20];
    private Abonement[] groupTrainAbonements = new Abonement [20];

    private int bassCount = 0;
    private int gymmCount = 0;
    private int groupCount = 0;



    public void closeFitnes(){
        for (int i = 0; i < bassAbonements.length; i++) {
            bassAbonements [i] = null;
            gymmAbonements [i] = null;
            groupTrainAbonements [i] = null;
        }
    }

    public void visit(Abonement abonement, VisitTarget target){
        if(abonement == null){
            System.out.println("Передан неверный абонемент (null)");
            return;
        }
        if(target == null){
            System.out.println("Передана неверная зона посещения (null)");
            return;
        }
        Date currDateTime = new Date();
        if(currDateTime.toInstant().isAfter(abonement.getExpDateTime().toInstant())){
            System.out.println("Срок действия вашего абонемента истёк");
            return;
        }

        LocalTime currentTime = LocalTime.now();
        if(abonement.getAllowStart().isAfter(currentTime) || currentTime.isAfter(abonement.getAllowEnd())){
            System.out.println("Посещение в это время недоступно. Вам возможно посещение с " + abonement.getAllowStart() +
                    " по " + abonement.getAllowEnd() + " текущее время:" + currentTime);
            return;
        }

        if(! abonement.isAllow(target)){
            System.out.println("Проход в выбранную вами зону недоступен по данному абонементу");
            return;
        }


        if(target == VisitTarget.GYM && (gymmCount >= 20)){
            System.out.println("В выбранной Вами зоне достигнут лимит одновременных посещений");
            return;
        }
        if(target == VisitTarget.POOL && (bassCount >= 20)){
            System.out.println("В выбранной Вами зоне достигнут лимит одновременных посещений");
            return;
        }
        if(target == VisitTarget.GROUP && (groupCount >= 20)){
            System.out.println("В выбранной Вами зоне достигнут лимит одновременных посещений");
            return;
        }


        if(target == VisitTarget.GYM){
            gymmAbonements[gymmCount] = abonement;
            gymmCount++;
            System.out.println(abonement.getOwner().getName() + " " + abonement.getOwner().getSurname() + ". Посещаемая зона: зал. Текущее время:" + currDateTime);
        }
        if(target == VisitTarget.POOL){
            bassAbonements[bassCount] = abonement;
            bassCount ++;
            System.out.println(abonement.getOwner().getName() + " " + abonement.getOwner().getSurname() + ". Посещаемая зона: бассейн. Текущее время:" + currDateTime);
        }
        if(target == VisitTarget.GROUP){
            groupTrainAbonements[groupCount] = abonement;
            groupCount ++;
            System.out.println(abonement.getOwner().getName() + " " + abonement.getOwner().getSurname() + ". Посещаемая зона: групповые занятия. Текущее время:" + currDateTime);
        }
        //  Реализовать возможность выводить информацию в консоль каждый раз, когда абонемент регистрируется
        //  в одной из зон ( добавляется в массив). Информация для вывода:
        //    Фамилия Имя Посещаемая зона (бассейн/тренажерный зал/групповые занятия)
        //    Дата и время посещения
        // т.е не всегда должна быть эта надпись? Какой критерий её появления?
    }

    public void printHumans(){
        //сначала о посетителях тренажерного зала, потом бассейна, потом групповых занятий
        System.out.println("Информация о посетителях тренажерного зала");
        for (Abonement gymmAbonement : gymmAbonements) {
            if(gymmAbonement == null){
                break;
            }
            System.out.println(gymmAbonement.getOwner().getName() + " " + gymmAbonement.getOwner().getSurname());
        }

        System.out.println("Информация о посетителях бассейна");
        for (Abonement bassAbonement : bassAbonements) {
            if(bassAbonement == null){
                break;
            }
            System.out.println(bassAbonement.getOwner().getName() + " " + bassAbonement.getOwner().getSurname());
        }

        System.out.println("Информация о посетителях групповых занятий");
        for (Abonement groupTrainAbonement : groupTrainAbonements) {
            if(groupTrainAbonement == null){
                break;
            }
            System.out.println(groupTrainAbonement.getOwner().getName() + " " + groupTrainAbonement.getOwner().getSurname());
        }
    }
}
