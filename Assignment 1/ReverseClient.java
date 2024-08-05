/* Taken help by KnockKnock server and client from oracle link : https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html */
package Networks;

import java.net.*;
import java.io.*;

public class ReverseClient {
    public static void main(String[] args) throws IOException {
        // check if the host name and port number is provided
        if (args.length != 3) {
            System.err.println("Usage: java ReverseClient <host name> <port number> <client name>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        String clientName = args[2];
         
        try ( 
            Socket revSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(revSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(revSocket.getInputStream()));
        ){
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                if (fromServer.equals("Quit.")) {
                    break;
                }

                fromUser = clientName + " : " + stdIn.readLine();
                if (fromUser != null) {
                    out.println(fromUser);
                }
            }
        }catch (UnknownHostException e) {
            System.err.println("Don't know the host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}