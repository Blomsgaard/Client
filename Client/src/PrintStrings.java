import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class PrintStrings extends Thread{
    private String message;
    private DataInputStream in;
    private Socket socket;
    private boolean running = true;
    private Client client;

    //Constructor to create a thread
    public PrintStrings(Socket socket, Client client){
        this.socket = socket;
        this.client = client;
    }

    @Override
    public synchronized void run() {
        try{
            //This loop continues to check for messages sent by the server and print them in the console.
                while (running) {
                    in = new DataInputStream(socket.getInputStream());
                    message = in.readUTF();

                    //Statements that check if a certain message is send to the client.
                    // In that case the method should do a different task than printing
                    if(message.equals("PLAYER_LIST")){
                        //In this case is adds players received from the server in a list, so they can be displayed in FX
                        int numberOfPLayer = in.readInt();
                        ArrayList<String> names = new ArrayList<>();
                        for(int i = 0; i < numberOfPLayer; i++){
                            String name = in.readUTF();
                            names.add(name);
                        }
                        client.makePlayerList(names);
                    }
                    else{
                        System.out.println(message);

                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //A method to stop the printing at certain times
    public void exit(){
        running = false;
    }


}
