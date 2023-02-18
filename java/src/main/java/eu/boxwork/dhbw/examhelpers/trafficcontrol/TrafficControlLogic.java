package eu.boxwork.dhbw.examhelpers.trafficcontrol;

/**
 * the logic that can control the traffic in the area
 * */
public class TrafficControlLogic {
    // the area to be controlled
    private TrafficArea trafficArea;

    /**
     * the contructor
     * @param trafficArea the area definition needed
     * */
    public TrafficControlLogic(TrafficArea trafficArea) {
        this.trafficArea = trafficArea;
    }

    /**
     * sets the start of an ID at 0/y where y is the next free slot
     * @param id id of the client
     * @return the starting position
     * @throws MovementNotPossible
     * */
    public Coordinate start(short id) throws MovementNotPossible
    {
        Coordinate currentPosition = trafficArea.getPosition(id);
        if (currentPosition==null)
        {
            // client not found, this is fine
            for (short y = 0; y < trafficArea.getArea()[0].length; y++) {
                Coordinate pos = new Coordinate((short) 0,y);
                if (trafficArea.isFree(pos))
                {
                    trafficArea.place(id, pos);
                    return pos;
                }
            }

            throw new MovementNotPossible("no free position found");
        }
        else throw new MovementNotPossible("client already available");
    }

    /**
     * moves the client id one step towards the target position; client may also stop
     * @param id id of the client
     * @param targetToReach target to reach
     * @return the new position
     * @throws MovementNotPossible
     * @throws ArrayIndexOutOfBoundsException
     * */
    public Coordinate move(short id, Coordinate targetToReach) throws MovementNotPossible, ArrayIndexOutOfBoundsException
    {
        // get the current position of the client
        Coordinate currentPosition = trafficArea.getPosition(id);
        // calculate the next step around the current position
        Coordinate bestCoordinate = currentPosition;
        double distance = getDistance(bestCoordinate,targetToReach);

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                short x = (short) (currentPosition.getX() + xOffset);
                short y = (short) (currentPosition.getY() + yOffset);

                if (x<0) x = 0;
                if (y<0) y = 0;
                if (x>trafficArea.getArea().length-1) x = (short) (trafficArea.getArea().length-1);
                if (y>trafficArea.getArea()[0].length-1) y = (short) (trafficArea.getArea()[0].length-1);

                Coordinate coordinateToCheck = new Coordinate(x,y);
                if (trafficArea.isFree(coordinateToCheck))
                {
                    double newDdistance = getDistance(coordinateToCheck,targetToReach);
                    if (newDdistance<distance)
                    {
                        distance = newDdistance;
                        bestCoordinate = coordinateToCheck;
                    }
                }
            }
        }
        // new / old coordinate determined
        // update the area
        trafficArea.remove(id, currentPosition);
        trafficArea.place(id, bestCoordinate);
        return bestCoordinate;
    }

    /**
     * calculates the distance between 2 points
     * @param firstCoordinate
     * @param secondCoordinate
     * @return the distance
     * */
    private double getDistance(Coordinate firstCoordinate, Coordinate secondCoordinate)
    {
        double x1 = firstCoordinate.getX();
        double x2 = secondCoordinate.getX();
        double y1 = firstCoordinate.getY();
        double y2 = secondCoordinate.getY();

        if (x1==x2 && y1==y2)
            return 0.0;
        else
        {
            // calculate the distance
            return Math.sqrt(
                    Math.pow(x1-x2,2)+Math.pow(y1-y2,2)
            );
        }
    }
}
