import java.io.*;
import java.net.Socket;

public class SocketHandler implements  Runnable {

    private Socket socket;
    private static int connCount;

    public SocketHandler(Socket socket){ this.socket = socket; }

    public void run(){
        try {   
            InputStream inStream = socket.getInputStream();//Входной поток
            OutputStream outStream = socket.getOutputStream();//Выходной поток
            BufferedReader in =  new BufferedReader(new InputStreamReader(inStream));
            PrintWriter out = new PrintWriter(outStream, true);
            connCount++;
            System.out.println("User " + connCount + " connected and works in " + Thread.currentThread().getName());
            //out.println("Hello! You number is " + connCount);
            while (true) {//Dialog
                String line = in.readLine();
                out.println("Echo: " + line);
                if(line.equals("bye")) break;
            }
            out.print("Connection Lost");
            System.out.println("User " + connCount + " disconnected in " + Thread.currentThread().getName());
            out.close();
            in.close();
            socket.close();
            connCount--;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
