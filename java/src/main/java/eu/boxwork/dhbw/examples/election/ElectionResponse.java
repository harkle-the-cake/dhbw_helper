package eu.boxwork.dhbw.examples.election;

import eu.boxwork.dhbw.examples.base.Message;
import eu.boxwork.dhbw.examples.base.Node;

public class ElectionResponse extends Message {
    // node that takes over
    private Node node = null;
    private boolean ok = true;

    public ElectionResponse ()
    {
        super () ;
    }
    /* GETTERS / SETTERS */
    public void setNode ( Node in ) { this . node = in ; }
    public Node getNode () { return this . node ; }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}