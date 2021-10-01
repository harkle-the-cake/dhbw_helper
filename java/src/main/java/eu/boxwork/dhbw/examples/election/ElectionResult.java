package eu.boxwork.dhbw.examples.election;

import eu.boxwork.dhbw.examples.base.Message;
import eu.boxwork.dhbw.examples.base.Node;

/**
 * This class is as an implementation example an
 * election result based on a message
*/
public class ElectionResult extends Message {
    // node that should be the new coordinator
    private Node coordinator;

    public ElectionResult(Node coordinator) {
        this.coordinator = coordinator;
    }

    /* GETTERS / SETTERS */
    public void setCoordinator ( Node in ) {
        this.coordinator=in ;
    }

    public Node getCoordinator () {
        return this.coordinator ;
    }
}
