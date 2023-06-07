package kurs003.common;

import java.nio.channels.SocketChannel;

/* Класс обертка (или типа того) для того что бы к каждому соединению можно было задвать тонет и понимать что это
за пользователь
 */
public class Connection {
        private SocketChannel channel;
        private String name;
        private String token;

        public Connection(SocketChannel channel, String name) {
            this.channel = channel;
            this.name = name;
        }

        public Connection(SocketChannel channel) {
            this.channel = channel;
            this.name = "defName";
        }

        static public Connection buildConnection(SocketChannel channel){
            Connection forReturn = new Connection(channel);
            return forReturn;
        }//Похоже я изобрел фабричный метод
        public SocketChannel getChannel() {
            return channel;
        }

        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
