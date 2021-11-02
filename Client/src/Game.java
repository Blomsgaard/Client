import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game extends Thread {
    private Client client;
    private Socket socket;
    private boolean readyWait = true;
    private DataInputStream in;
    private DataOutputStream out;
    private ArrayList<SolutionCard> userHand = new ArrayList<SolutionCard>(5);

    public Game(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }

    @Override
    public synchronized void run() {
        Scanner input = new Scanner(System.in);
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            // Press 1 to tell server to start the game

            while(readyWait) {
                System.out.println("Press 1 when you are ready");
                int start = input.nextInt();
                if (start == 1) {
                    boolean test = true;
                    out.writeBoolean(test);
                    readyWait = false;
                }
            }


            while (true) {
                //Choose the solution by the index value and sent it to server
                int sentInt = input.nextInt();
                System.out.println(sentInt);
                out.writeInt(sentInt);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Adds solutions cards to a userhands by receiving a String value
    public void getUserHand(Socket socket) throws IOException, ClassNotFoundException {
        in = new DataInputStream(socket.getInputStream());
        for (int i = 0; i < 5; i++) {
            userHand.add(new SolutionCard(in.readUTF()));
        }

    }

    public void getNewCard(Socket socket) throws IOException, ClassNotFoundException {
        in = new DataInputStream(socket.getInputStream());
        userHand.add(new SolutionCard(in.readUTF()));
    }
}
