package eu.boxwork.dhbw.examples.tokenring;

import eu.boxwork.dhbw.examples.base.Node;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * This class is an example for a token
 * based on a message
 */
public class Token implements Serializable {
    // node that created the token
    private Node creator ;
    private Instant creation ;
    private List< Node > cluster ;
    private int resource ;

    public Token ()
    {}

    /* GETTERS / SETTERS */
    public void setCreator ( Node in ) { this . creator = in ; }
    public Node getCreator () { return this . creator ; }
    public void setCreation ( Instant in ) { this . creation = in ; }
    public Instant setCreation () { return this . creation ; }
    public void setCluster ( Node in ) { this . creator = in ; }
    public Node getCluster () { return this . creator ; }
    public void setResource ( int in ) { this . resource = in ; }
    public int getResource () { return this . resource ; }
}