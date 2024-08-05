/* Taken help by KnockKnock server and client from oracle link : https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html */

package Networks;
import java.net.*;
import java.io.*;

public class ReverseServerThread extends Thread {
    private Socket socket = null;
    public ReverseServerThread(Socket socket) {
        super("ReverseServerThread");
        this.socket = socket;
    }

    public void run() {
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String inputLine, outputLine;
            ReverseProtocol rp = new ReverseProtocol();
            outputLine = rp.processInput(null);
            out.println(outputLine);
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                outputLine = rp.processInput(inputLine);
                outputLine = " Server : " + outputLine;
                out.println(outputLine);
                System.out.println(outputLine);
                if (outputLine.equals("Quit.")) {
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port or listening for a connection");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
