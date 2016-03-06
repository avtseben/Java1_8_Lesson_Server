import java.net.ServerSocket;
import java.net.Socket;

public class MainClass {
    public static void main (String[] args) {
        try(ServerSocket s = new ServerSocket(8189)) {//Вариант при котороу после окончания блока try закроется сервер сокет
            System.out.println("Server Started");
            while (true) {//Множественные соединения
                Socket incoming = s.accept();
                new Thread(new SocketHandler(incoming)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
