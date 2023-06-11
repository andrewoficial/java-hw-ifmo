package kurs003.common;

public class ParserPackage<T> {
    private T message;

    public boolean isMessage(){
        //System.out.println(message.getClass());
        return message instanceof Message;
    }

    public boolean isTextFile(){
        //System.out.println(message.getClass());
        return message instanceof TextFile;
    }
    public  ParserPackage(T obj){
        this.message = obj;
    }


    public T getMessage() {
        return message;
    }
}
