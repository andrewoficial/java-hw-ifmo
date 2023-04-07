package hw009;

public class Application {
    public static void main(String[] args) {
        AppLogger logger01 = new Delimiter(new Upper(new ConsoleWriter()));
        logger01.log("сообщение");
        /*
        logger01.log("сообщение");

        AppLogger logger02 = new Upper(new Delimiter(new AppFileWriter()));
        logger02.log("сообщение");

        ConsoleWriter console = new ConsoleWriter();
        console.log("данные"); // данные будут выведены в консоль

        AppFileWriter file = new AppFileWriter();
        file.log("данные"); // данные будут записаны в файл
        */

    }
}
