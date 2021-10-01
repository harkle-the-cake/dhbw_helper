package eu.boxwork.dhbw.examples.base;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * This class is used as an example for reading Object
 * from a socket
 */
public class ObjectMessageReader {
    /**
     * this method reads objects from a given socket
     * @param socket socket to read an object from
     * @return the message object or null , in case of an
     * error
     */
    public Message read ( Socket socket )
    {
        Message ret = null ;
        try {
            InputStream is = socket.getInputStream () ;
            ObjectInputStream ois = new ObjectInputStream ( is ) ;
            ret = ( Message ) ois.readObject () ;
        } catch ( Exception e )
        {
            System.err.println ( e.toString() ) ;
        }
        return ret ;
    }

}
