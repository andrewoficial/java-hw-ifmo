package hw009;

public class ConsoleWriter extends AppLogger implements Logable{

    public ConsoleWriter(){

    }

    public ConsoleWriter(Logable obj){

    }
    public void log(String str){
        System.out.println(str);
    }
}
