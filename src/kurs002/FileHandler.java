package kurs002;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.io.File;

public class FileHandler {

    public boolean hasSavedGame(){
        File file = new File("hashmap.ser");
        if(file.exists()) {
            if (file.length() < 1) {
                return false;
            }else{
                return true;
            }
        }
        return false;

    }

    public HashMap<String, Card> getMap(){
        HashMap<String, Card> forReturn = new HashMap<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("hashmap.ser"))) {
            forReturn = (HashMap<String, Card>) inputStream.readObject();
        } catch (IOException e) {
            System.out.println("Сохранений не найдено (ERR-01)");
        } catch (ClassNotFoundException e) {
            System.out.println("Сохранений не найдено (ERR-02)");
        }
        return forReturn;
    }
}
