package eu.boxwork.dhbw.examples.concurrent;
/**
 * This class is an example for a state class of a
 * resource
 */
enum State {
    OPEN , AQUIRED , OCCUPIED
}

public class ResourceState {
    // node that created the token
    private State state = State . OPEN ;
    private int resource ;

    public ResourceState ()
    {}

    /* GETTERS / SETTERS */
    public void setState ( State in ) { this . state = in ; }
    public State getState () { return this . state ; }
    public void setResource ( int in ) { this . resource = in ; }
    public int getResource () { return this . resource ; }
}