package hw010;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class Application {
    public static void main(String[] args) {
        //Задача 1

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy в HH:mm");

        LocalDateTime first = LocalDateTime.of(2022, Month.JULY, 16, 19, 00);
        ZonedDateTime sdn = first.atZone(ZoneId.of("Australia/Sydney"));
        //System.out.println("Отправление из сиднея в: " + formatter.format(sdn)); // в Москве сейчас

        //Время в пути Сидней - Хьюстон - 15 часов 35 минут
        sdn.plusHours(15);
        sdn.plusMinutes(35);
        //Время ожидания в аэропорту Хьюстона - 1 час
        sdn.plusHours(1);
        LocalDateTime hustonStart = sdn.withZoneSameInstant(ZoneId.of("America/Chicago")).toLocalDateTime();
        //Время в пути Хьюстон - Вашингтон - 2 часа 49 минут
        sdn.plusHours(2);
        sdn.plusMinutes(49);
        //Время ожидания в аэропорту Вашингтона - 1 час 10 минут
        sdn.plusHours(1);
        sdn.plusMinutes(10);
        LocalDateTime vashingtonStart = sdn.withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
        //Время в пути Вашингтон - Оттава - 1 час 40 минут
        sdn.plusHours(1);
        sdn.plusMinutes(40);
        LocalDateTime ottavaArrive = sdn.withZoneSameInstant(ZoneId.of("America/Toronto")).toLocalDateTime();
        /*
            Время прибытия! в аэропорт Оттавы (время местное для Оттавы)
            Время вылета! из аэропорта Хьюстона (время местное для Хьюстона)
            Время вылета! из аэропорта Вашингтона (время местное для Вашингтона)
         */
        System.out.println("Прибытие в Оттаву" + ottavaArrive);
        System.out.println("Вылета из Хьюстона" + hustonStart);
        System.out.println("Вылета из Вашингтона" + vashingtonStart);

        //Задача 2
        LocalTime current = LocalTime.now();
        LocalTime parseTime01 = LocalTime.parse("07:00:00");
        LocalTime parseTime02 = LocalTime.parse("15:00:00");
        LocalTime parseTime03 = LocalTime.parse("23:00:00");
        if(current.isAfter(parseTime01) && parseTime01.isAfter(current)){
            System.out.println("Сейчас больше 7 и меньше 15 - Первая смена");
        } else if (current.isAfter(parseTime02) && parseTime03.isAfter(current)) {
            System.out.println("Сейчас больше 15 и меньше 23 - Вторая смена");
        }else{
            System.out.println("Третья смена");
        }

        //Задача 3



        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date dateS = new Date();

        Date dateE = null;
        try {
            dateE = format.parse("2023-06-16 22:22");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        long delta = dateE.getTime() - dateS.getTime();
        delta = delta / 86400000; //Дней
        delta = delta / 7; //Недель
        delta = delta * 3; //Занятий

        System.out.println(delta);


    }
}
