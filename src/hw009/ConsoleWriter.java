package hw009;

public class ConsoleWriter extends AppLogger implements Logable{

    public ConsoleWriter(){

    }


    @Override
    public void log(String str){
        System.out.println(str);
    }
}
