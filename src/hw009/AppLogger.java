package hw009;

public class AppLogger implements Logable{

    public AppLogger(){

    }

    public AppLogger(Logable obj){

    }
    public void log(String str){
        System.out.println(str);
    }
}
