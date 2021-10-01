package eu.boxwork.dhbw.examples.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class is used as an example for a Server Socket
 * this could be a own thread
 */
public class MyServer {
    // this max client definition is not available in "old "
    // implementations
    public static final int maxIncomingClients = 100;
    /**
     * this method initialises the server
     * @param dns name like " localhost "
     * @param port port to use
     * @return the created socket after client connected
     */
    public Socket initialise (String dns , int port ) throws IOException {
        ServerSocket serverSocket = new ServerSocket (
                port ,
                maxIncomingClients ,
                InetAddress.getByName( dns ) ) ;
        // no need for an additional bind , but could be done here
        return serverSocket.accept () ;
    }
}
