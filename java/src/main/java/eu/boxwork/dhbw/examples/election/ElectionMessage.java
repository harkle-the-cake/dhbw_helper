package eu.boxwork.dhbw.examples.election;

import eu.boxwork.dhbw.examples.base.Message;
import eu.boxwork.dhbw.examples.base.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is as an implementation example for a
 *election message ( ring algorithm )
 * based on a message
 */
public class ElectionMessage extends Message {
   // node that starts the election
    private Node startingNode = null ;
    // list of nodes to be sent
    private List< Node > electionNodes = new ArrayList<>() ;
    // default constructor
    public ElectionMessage ()
    {
        super () ;
    }
    /* GETTERS / SETTERS */

    public void setStartingNode ( Node in ) {
        this.startingNode = in ;
    }

    public Node getStartingNode () {
        return this.startingNode ;
    }

    public void setElectionNodes ( List < Node > in ) {
        this.electionNodes = in ;
    }

    public List < Node > getElectionNodes () {
        return this.electionNodes ;
    }
    /* methods that make life easier */
    public void addNode ( Node in )
    {
        electionNodes . add ( in ) ;
    }
}