/* Taken help by KnockKnock server and client from oracle link : https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html */

package Networks;
public class ReverseProtocol {
    // take the input from client reverse it and broadcast.
    public String processInput(String theInput) {
        String theOutput = null;
        if (theInput != null && !theInput.equals("q")) {
            String head  = theInput.toString();
            int ih = head.indexOf(":");
            head = head.substring(0, ih);
            theInput = theInput.substring(ih+1);
            theOutput = new StringBuilder(theInput).reverse().toString();
            theOutput = head + " : " + theOutput;
        } else if (theInput != null && theInput.equals("q")) {
            theOutput = "Quit.";
        }
        else {
            theOutput = "Hello, I am Reverse Server. Please enter a string. press q to quit.";
        } 
        return theOutput;
    }
}
