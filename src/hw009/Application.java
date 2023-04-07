package hw009;

public class Application {
    public static void main(String[] args) {
        AppLogger logger01 = new Delimiter(new Upper(new ConsoleWriter()));
        //AppLogger logger01 = new Upper(new ConsoleWriter());
        logger01.log("сообщение"); //В консоль выводит ===сообщение=== игнорируя Upper



        AppLogger logger02 = new Upper(new Delimiter(new AppFileWriter()));
        logger02.log("сообщение");


        ConsoleWriter console = new ConsoleWriter();
        console.log("Строка без изменений"); // данные будут выведены в консоль

        AppFileWriter file = new AppFileWriter();
        file.log("данные"); // данные будут записаны в файл





    }
}
