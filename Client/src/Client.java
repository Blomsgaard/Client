import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements java.io.Serializable {

    static String host = "192.168.0.32";
    static int port = 6969;
    static DataInputStream in;
    static DataOutputStream out;
    static Socket socket;
    private PrintStrings print;
    //static ObjectInputStream objectIn;
    ArrayList<SolutionCard> userHand = new ArrayList<SolutionCard>(5);

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

                printStart();

                //Creates a thread for printing messages that send from the server.
                //This thread continues to run until the program is shut down
                /*PrintStrings print = new PrintStrings(socket);
                print.start();


                // Connection loop

                    //Boolean lobby = true;
                    //out.writeBoolean(lobby);
                    //String lobbyName = in.readUTF();
                    //System.out.println(lobbyName);


                    // Press 1 to tell server to start the game
                    int start = input.nextInt();
                    System.out.println(start);
                    if (start == 1) {
                        boolean test = true;
                        System.out.println(test);
                        out.writeBoolean(test);
                        //Stops the print thread, so the text field can be added to the users hand
                        print.exit();
                    }*/


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
        print = new PrintStrings(socket);
        print.start();
    }

}

