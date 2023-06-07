package kurs003.apps;

import kurs003.common.Message;
import kurs003.common.ReadWrite;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ClientApp {

    private InetSocketAddress remote;
    public ClientApp(InetSocketAddress remote){
        this.remote = remote;
    }
    
    public void run (){
        Scanner scanner = new Scanner(System.in);

        while(true){
            logMsgC("Введите текст или '/exit' для выхода");
            String input = scanner.nextLine();
            if("/exit".equalsIgnoreCase(input)) return; //Если после цикла while нет ничего полезного то сразу прервать метод run

            try(Socket socket = new Socket (remote.getHostString(), remote.getPort());
                ReadWrite readWrite = new ReadWrite(socket);
                ){

                Message message = new Message(input, "defKey");
                readWrite.writeMessage(message);
                Message fromServer = readWrite.readMessage();
                logMsgC(" readWrite.readMessage" + fromServer.getText());
            } catch(UnknownHostException e){
                logMsgC(" Ошибка адреса или порта");
            }
            catch (IOException e){
                logMsgC(" Сервер не отвечает");
            }
        }
    }
    public static void main(String[] args) {
        String ip = "127.0.0.1"; //Локальный адрес
        int port = 2222;
        InetSocketAddress remote = new InetSocketAddress(ip, port);
        InetSocketAddress local = new InetSocketAddress(ip, 1111);

        Socket socket = new Socket();
        // Socket socket = new Socket(ip, port); // соединение с удаленным сервером
        OutputStream output = null;
        InputStream input = null;
        try {
            socket.bind(local);
            logMsgC(" socket.isBound? " + socket.isBound());

            socket.connect(remote /*, 10000 */); // соединение с удаленным сервером
            //socket.connect(local /*, 10000 */); // соединение с удаленным сервером
            logMsgC(" socket.isConnected? " + socket.isConnected());

            logMsgC(" ReceiveBufferSize? " + socket.getReceiveBufferSize());
            logMsgC(" SendBufferSize? " + socket.getSendBufferSize());
            logMsgC(" LocalSocketAddress? " + socket.getLocalSocketAddress());
            logMsgC(" RemoteSocketAddress? " + socket.getRemoteSocketAddress());

            output = socket.getOutputStream();
            input = socket.getInputStream();
            logMsgC(" Записываю " + 11);
            output.write(11);
            logMsgC(" Считываю " + input.read());
        } catch (SocketException e) {
            logMsgC("Произошло исключение SocketException");
            e.printStackTrace();
        } catch (IOException e) {
            logMsgC("Произошло исключение IOException");
            e.printStackTrace();
        }
        finally {
            logMsgC("Закрываю ресурсы");
            try {
                if (input != null) input.close();
                logMsgC("   Закрываю input");
                if (output != null) output.close();
                logMsgC("   Закрываю output");
                if (socket != null) socket.close();
                logMsgC("   Закрываю serverSocket");
            } catch (IOException e){
                logMsgC("   Произошло исключение при закрытии ресурсов");
                e.printStackTrace();
            }
        }

    }

    private static void logMsgC(String msg){
        System.out.println("CLT:" + msg);
    }
}
