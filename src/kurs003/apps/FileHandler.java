package kurs003.apps;

import kurs003.common.Message;
import kurs003.common.TextFile;

import java.io.*;
import java.util.HashMap;

public class FileHandler {

    public boolean hasSavedFiles(){
        File file = new File("hashmap2.ser");
        if(file.exists()) {
            if (file.length() < 1) {
                return false;
            }else{
                return true;
            }
        }
        return false;

    }

    public HashMap<String, TextFile> getFileMap(){
        HashMap<String, TextFile> forReturn = new HashMap<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("hashmap2.ser"))) {
            forReturn = (HashMap<String, TextFile>) inputStream.readObject();
        } catch (IOException e) {
            System.out.println("Сохранений не найдено (ERR-01)");
        } catch (ClassNotFoundException e) {
            System.out.println("Сохранений не найдено (ERR-02)");
        }
        return forReturn;
    }

    public Message getFileMapList(){
        String list = "";
        HashMap<String, TextFile> forReturn = getFileMap();
        for (String s : forReturn.keySet()) {
            list += s + "\n";

        }
        return new Message(list, "SERVER");
    }

    public TextFile getFile(String name){
        HashMap<String, TextFile> forReturn = getFileMap();
        for (String s : forReturn.keySet()) {
            if(name.equalsIgnoreCase(s)){
                return forReturn.get(s);
            }
        }
        return null;
    }

    public Message getFileAsMessage(String name){
        TextFile file = getFile(name);
        if(file == null){
            return new Message("Ошибка получения файла FileHandler ERR", "SERVER");
        }
        Message forReturn;
        String forMsg = "";
        forMsg += file.getName() + "\n";
        forMsg += file.getDescription() + "\n";
        forMsg += file.getContent() + "\n";
        forMsg += "Uploaded by:"+file.getIdentify() + "\n";
        forReturn = new Message(forMsg, "SERVER");
        return forReturn;
    }

    public void saveFile(TextFile file){
        HashMap<String, TextFile> forReturn = getFileMap();
        forReturn.put(file.getName(), file);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("hashmap2.ser"))) {
            outputStream.writeObject(forReturn);
            //System.out.println("HashMap has been written to the file.");
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Возникла ошибка при сохранении :(" + e.getMessage());
        }
    }
}