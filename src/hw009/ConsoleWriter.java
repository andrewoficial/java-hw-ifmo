package hw009;

public class ConsoleWriter extends AppLogger implements Logable{

    public ConsoleWriter (Logable obj){

    }

    public ConsoleWriter (){
        this(new LogableObj());
    }
    public void log(String str){
        System.out.println(str);
    }
}
