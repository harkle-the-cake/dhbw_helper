package eu.boxwork.dhbw.examples.threading;

/**
 * This class is used as an example to all a Runnable
 */
public class Application {
    /**
     * we simply start the main
     */
    public static void main (String [] args ) throws InterruptedException {
        // create our runnable class object
        MyRunnableClass runnableObject = new MyRunnableClass () ;
        // create a Thread
        Thread runner = new Thread ( runnableObject ) ;
        // we can setup additional settings , like e.g. name
        //        and priority
        // ..
        // now we start the runner
        runner.start() ; // this calls the run () Method in the runnable class object
        // as a main , we wait for the thread to finish


        runner.join() ;
    }
}