package hw009;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AppFileWriter implements Logable{


    public void log(String str){
        try {
            Files.writeString(Paths.get("file-name.txt"), "данные", StandardOpenOption.APPEND);
            // "данные" будут записаны в конец (StandardOpenOption.APPEND) файла "file-name.txt"
        } catch (IOException e) {
            System.out.println("Данные не были записаны");
        }
    }
}
