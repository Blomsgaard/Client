import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    static String host = "192.168.43.6";
    static int port = 6969;
    static DataInputStream in;
    static DataOutputStream out;
    static Socket socket;

    public static void main(String[] args) {

        // Scanner method
        Scanner input = new Scanner(System.in);
        boolean connect = true;

        // Username input
        System.out.println("Enter username: ");
        String username = input.nextLine();

        // Establishing Server Connection
        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            // Write username, and send it to the server
            out.writeUTF(username);

            //while (connect) {
                /*System.out.println("Enter annual interest rate");
                double annualInterestRate = input.nextDouble();

                System.out.println("Enter number of years");
                int numOfYears = input.nextInt();

                System.out.println("Enter loan amount");
                double loanAmount = input.nextDouble();

                out.writeDouble(annualInterestRate);
                out.writeInt(numOfYears);
                out.writeDouble(loanAmount);

                 */
            //out.flush();

            // }

            // Connection loop
            while(connect){
                //Boolean lobby = true;
                //out.writeBoolean(lobby);
                //String lobbyName = in.readUTF();
                //System.out.println(lobbyName);


                // Press 1 to tell server to start the game
                int start = input.nextInt();
                System.out.println(start);
                if(start == 1){
                    boolean test = true;
                    System.out.println(test);
                    out.writeBoolean(test);

                }


            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

