package hw009;

public class Upper  extends AppLogger implements Logable{


    public Upper(ConsoleWriter consoleWriter) {

    }

    public void log(String str){
        String arg =str.toUpperCase();
        super.log(arg);
    }
}
