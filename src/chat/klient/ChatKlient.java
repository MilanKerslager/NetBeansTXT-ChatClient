/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.klient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatKlient {

    public static void main(String[] args) {
        String serverName = "localhost";
        int serverPort = 3333;
        try {
            System.out.println("Navazuji spojení se serverem...");
            Socket socket = new Socket(serverName, serverPort);
            System.out.println("Spojení navázáno: "+socket);
            DataInputStream console = new DataInputStream(System.in);
            DataOutputStream streamOut = 
                    new DataOutputStream(socket.getOutputStream());
            while (true) {
                String line = console.readUTF();
                streamOut.writeUTF(line);
                streamOut.flush();
                if (line.equals("QUIT")) break;
            }
            console.close();
            streamOut.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ChatKlient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
