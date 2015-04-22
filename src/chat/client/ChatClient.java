// Simple Chat client for console (no GUI).
// Sends raw lines to the server (does not handle UTF characters).
// If "QUIT" on the line alone is written,
//    client sends it to the server and then ends itself.

package chat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClient {

    public static void main(String[] args) {
        String serverName = "localhost";
        final int SERVER_PORT = 3333;
        Scanner console = new Scanner(System.in);
        try {
            System.out.println("Navazuji spojení se serverem...");
            Socket socket = new Socket(serverName, SERVER_PORT);
            System.out.println("Spojení navázáno: " + socket);
            DataOutputStream streamOut = 
                    new DataOutputStream(socket.getOutputStream());
            while (true) {
                String line = console.nextLine();
                streamOut.writeBytes(line+"\r");
                streamOut.flush();
                if (line.equals("QUIT")) {
                    break;
                }
            }
            System.out.println("Client ends now. Bye.");
            console.close();
            streamOut.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}