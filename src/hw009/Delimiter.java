package hw009;

public class Delimiter  extends AppLogger implements Logable{
    Logable currMethot;
    public Delimiter(){

    }
    public Delimiter (Logable obj){
        this.currMethot = obj;
    }

    @Override
    public void log(String str){
        currMethot.log("===" + str + "===");
    }
}
