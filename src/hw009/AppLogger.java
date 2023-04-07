package hw009;

public class AppLogger implements Logable{

    Logable currMethot;
    public AppLogger(){

    }

    public AppLogger(Logable obj){
        this.currMethot = obj;
    }

    @Override
    public void log(String str){
        currMethot.log(str);
    }
}
