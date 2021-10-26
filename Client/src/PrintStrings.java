import java.io.DataInputStream;
import java.net.Socket;

public class PrintStrings extends Thread{
    private String message;
    private DataInputStream in;
    private Socket socket;

    //Constructor to create a thread
    public PrintStrings(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            //This loop continues to check for messages sent by the server and print them in the console.
            while(true) {
                in = new DataInputStream(socket.getInputStream());
                message = in.readUTF();
                System.out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
