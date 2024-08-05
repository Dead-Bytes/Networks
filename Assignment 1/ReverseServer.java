/* Taken help by KnockKnock server and client from oracle link : https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html */
package Networks;
import java.net.*;
import java.io.*;

public class ReverseServer{
    public static void main(String[] args) throws IOException {
        
        // Check if the port number is provided
        if (args.length != 1) {
            System.err.println("Usage: java ReverseServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
        ) {
            String iputLine, outputLine;
            Networks.ReverseProtocol rp = new Networks.ReverseProtocol();
            outputLine = rp.processInput(null);
            out.println(outputLine);

            while ((iputLine = in.readLine()) != null) {
                outputLine = rp.processInput(iputLine);
                out.println(outputLine);
                if (outputLine.equals("Quit."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}