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

        Scanner input = new Scanner(System.in);
        boolean connect = true;

        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

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
        while(true){
        String name = input.nextLine();
        out.writeUTF(name);
        while(true){
            String print = in.readUTF();
            System.out.println(print);
        }

        }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
