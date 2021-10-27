import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements java.io.Serializable {

    static String host = "192.168.43.6";
    static int port = 6969;
    static DataInputStream in;
    static DataOutputStream out;
    static Socket socket;
    static ObjectInputStream objectIn;
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

                //Creates a thread for printing messages that send from the server.
                //This thread continues to run until the program is shut down
                PrintStrings print = new PrintStrings(socket);
                print.start();


                // Connection loop
                while (connect) {
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

                    }


                    // getUserHand(socket);
                    objectIn = new ObjectInputStream(socket.getInputStream());
                    // for (int i = 0; i < 5; i++) {
                    SolutionCard temp = (SolutionCard) objectIn.readObject();
                    userHand.add(temp);

                    //}
                    System.out.println(userHand.get(0).toString());


                }

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    public void getUserHand(Socket socket) throws IOException, ClassNotFoundException {
        objectIn = new ObjectInputStream(socket.getInputStream());
       // for (int i = 0; i < 5; i++) {
            SolutionCard temp = (SolutionCard) objectIn.readObject();
            userHand.add(temp);

        //}

    }
}

