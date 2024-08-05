/* Taken help by KnockKnock server and client from oracle link : https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html */

package Networks;
import java.net.*;
import java.io.*;

public class ReverseServerMulti {
    public static void main(String[]  args) throws  IOException {
        // check if the port number is provided
        if (args.length != 1) {
            System.err.println("Usage: java ReverseServerMulti <port number>");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening){
                new ReverseServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
