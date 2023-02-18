package eu.boxwork.dhbw.examhelpers.trafficcontrol;

import java.util.Random;

/**
 * example application to show how the access is done
 * */
public class ExampleApplication {

    public static void main(String[] args)  {
            // for random target positioning and random moves
            Random r = new Random();
            // create area
            short maxX = 10;
            short maxY = 10;
            TrafficArea area = new TrafficArea((short) 2, maxX, maxY);
            // create logic
            TrafficControlLogic logic = new TrafficControlLogic(area);
            // create 20 clients
            int maxClients = 20;
            Coordinate[] targetPostions = new Coordinate[maxClients];
            for (int i = 0; i < maxClients ; i++) {
                try
                {
                    short x = (short)r.nextInt(maxX);
                    short y = (short)r.nextInt(maxX);

                    targetPostions[i] = new Coordinate(x,y);
                    logic.start((short) i);
                } catch (Exception e)
                {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            // print the current situation
            System.out.println("############### INITIAL POSITION ################");
            area.print();
            // move randomly the ids and then exit after 60 moves
            for (int i = 0; i < 60; i++) {
                System.out.println("############### STEP "+i+" ################");
                try
                {
                    int idToMove = r.nextInt(maxClients);
                    logic.move((short) idToMove, targetPostions[idToMove]);
                    area.print();
                    Thread.sleep(100);
                } catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }
            }
            // all movement done, check which ids reached destination
            System.out.println("following client id reached the target positions:");
            for (int i = 0; i < maxClients ; i++) {
                try
                {
                    Coordinate finalPosition = area.getPosition((short) i);
                    if (finalPosition.getX() == targetPostions[i].getX()
                            && finalPosition.getY() == targetPostions[i].getY()
                    )
                    {
                        System.out.println(" -> "+i);
                    }
                } catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }
            }

    }
}
