package hw009;

public class Upper  extends AppLogger implements Logable{
    Logable currMethot;

    public Upper (){

    }
    public Upper(Logable obj) {
        currMethot = obj;
    }

    @Override
    public void log(String str){
        currMethot.log(str.toUpperCase());
    }


}
