package hw009;

public class Delimiter  extends AppLogger implements Logable{

    public Delimiter(){

    }
    public Delimiter (Logable obj){

    }

    public void log(String str){
        super.log("===" + str + "===");
    }
}
