package eu.boxwork.dhbw.examples.threading;

import java.time.Instant;

/**
 * This class is used as an example for Threading
 */
public class MyRunnableClass implements Runnable {
    /**
     3
     * the run method will be called by the thread
     * when we use the start () -Method
     */
    public void run ()
    {
        System.out.println ("Am I a threaded method ?... lets wait for a second: "+ Instant.now()) ;

        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.err.println("ERROR WHILE WAITING: "+e.toString());
        }

        System.out.println ("Waiting completed at "+Instant.now()) ;
    }
}
