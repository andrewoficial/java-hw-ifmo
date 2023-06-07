package kurs003.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

public class ReadWrite implements AutoCloseable{
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ReadWrite(Socket socket) throws IOException {
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        this.socket = socket;
    }

    public Message readMessage() throws IOException {
        try {
            return (Message) input.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Класс Message не найден");
        }
        return null;
    }
    public void writeMessage(Message message) throws IOException {
        message.setSent(LocalDateTime.now());
        output.writeObject(message);
        output.flush();
    }

    @Override
    public void close()  {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия ресурсов. " +
                    "Например, обрыв соединения произошел по время закрытия");
        }
    }
}