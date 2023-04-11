package hw012;

import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.jar.JarException;

public class Application {
    public static void main(String[] args) {
        Status jar = Status.JAR_ERROR;
        Status fil = Status.FILE_NOT_FOUND;
        Status ace = Status.ACCESS_DENIED;


        try {
            throwException(ace);
        } catch (JarException | FileNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (AccessDeniedException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    static void throwException(Status status) throws JarException, FileNotFoundException, AccessDeniedException{
        switch(status){
            case FILE_NOT_FOUND:
                throw new FileNotFoundException("Исключение про ненайденный файл");
            case ACCESS_DENIED:
                throw new AccessDeniedException("Исключение про ошибку доступа");
            case JAR_ERROR:
                throw new JarException("Исключение про ошибку JAR");
        }
    }
}
