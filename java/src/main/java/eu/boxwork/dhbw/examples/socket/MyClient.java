package eu.boxwork.dhbw.examples.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * This class is used as an example for a Client Socket
 * this could be a own thread
 */
public class MyClient {
    /**
     * this method initialises the client
     * @param dns distination like " localhost "; can also be
    an IP
     * @param port port to connect to
     * @return the created socket after connection is
    established
     */
    public Socket initialise (String dns , int port ) throws IOException {
        Socket clientSocket = new Socket (dns , port ) ;
        // no need for an additional bind , but could be done here
        return clientSocket ;
    }
}