import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements java.io.Serializable {

    static String host = "localhost";
    static int port = 6969;

    static DataInputStream in;
    static DataOutputStream out;

    static Socket socket;
    private PrintStrings print;

    ArrayList<SolutionCard> userHand = new ArrayList<SolutionCard>(5);
    ArrayList<String> playerNames = new ArrayList<>();
    ArrayList<Integer> playerPoints = new ArrayList<>();

    public static void main(String[] args) {
        Client client = new Client();
        client.runClient();
    }


        public void runClient () {
            // Scanner method
            Scanner input = new Scanner(System.in);
            boolean connect = true;


            // Telling the user to input a name and then using the scanner to get the input
            System.out.println("Enter username: ");
            String username = input.nextLine();

            // Establishing Server Connection
            try {
                socket = new Socket(host, port);
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                // Write username, and send it to the server
                out.writeUTF(username);

                //Starts the thread, which waits for messages from the server
                printStart();

                //Starts a thread for the server connection
                Game game = new Game(socket, this);
                game.start();






            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    //Adds solutions cards to userhands by receiving a String value
    public void getUserHand(Socket socket) throws IOException, ClassNotFoundException {
        in = new DataInputStream(socket.getInputStream());
        for (int i = 0; i < 5; i++) {
            userHand.add(new SolutionCard(in.readUTF()));
        }

    }

    public void printStop(){
        this.print.exit();
    }

    public void printStart(){
        print = new PrintStrings(socket, this);
        print.start();
    }

    public void makePlayerList(ArrayList<String> names){
        playerNames = new ArrayList<>();
        for(int i = 0; i < names.size(); i++){
            playerNames.add(names.get(i));
        }
    }

    public void makeScoreboard(ArrayList<Integer> points){
        playerPoints = new ArrayList<>();
        for(int i = 0; i < points.size(); i++){
            playerPoints.add(points.get(i));
        }
        System.out.println(playerPoints);
    }
}

