package eu.boxwork.dhbw.examhelpers.trafficcontrol;

/**
 * represents the area to be controlled by the traffic control system
 * */
public class TrafficArea {
    private static final short NO_ID = -1;
    // current Area
    private short[][][] area;

    /**
     * initialises this area with sizes
     * @param maxPerNode maximum IDs per node
     * @param maxSizeX size in x - direction
     * @param maxSizeY size in y - direction
     * */
    public TrafficArea(short maxPerNode, short maxSizeX, short maxSizeY)
    {
        // max definitions
        area = new short[maxSizeX][maxSizeY][maxPerNode];
        // init the area
        clear();
    }

    /**
     * removes an ID from a position, throws an exception if not possible
     * @param id id of the client
     * @param from coordinate the client currently is allocated to
     * @throws MovementNotPossible
     * @throws ArrayIndexOutOfBoundsException
     * */
    public void remove(short id, Coordinate from) throws MovementNotPossible, ArrayIndexOutOfBoundsException
    {
        boolean idFoundAndRemoved = false;
        for (int i = 0; i < area[from.getX()][from.getY()].length; i++) {
            if (area[from.getX()][from.getY()][i]==id)
            {
                // id found start start
                area[from.getX()][from.getY()][i]=NO_ID;
                idFoundAndRemoved=true;
                break;
            }
        }
        if (!idFoundAndRemoved) throw new MovementNotPossible("id not found at start");
    }

    /**
     * places an ID to a position, throws an exception if not possible
     * @param id id of the client
     * @param to coordinate the client currently is allocated to
     * @throws MovementNotPossible
     * @throws ArrayIndexOutOfBoundsException
     * */
    public void place(short id, Coordinate to) throws MovementNotPossible, ArrayIndexOutOfBoundsException
    {
        int freePos = -1;
        for (int i = 0; i < area[to.getX()][to.getY()].length; i++) {
            if (area[to.getX()][to.getY()][i]==-1)
            {
                freePos = i;
                // id may be placed, if not yet set there
            }
            else if (area[to.getX()][to.getY()][i]==id)
            {
                throw new MovementNotPossible("id already placed at target position");
            }
        }
        if (freePos==-1) throw new MovementNotPossible("no empty space left");
        area[to.getX()][to.getY()][freePos]=id;
    }

    /**
     * returns the current position of the client
     * @param id the client id to search fpr
     * @return the coordinate or null if id not found
     * */
    public Coordinate getPosition(short id) {
        for (short x = 0; x < area.length; x++) {
            for (short y = 0; y < area[x].length; y++) {
                for (short clientIDPos = 0; clientIDPos < area[x][y].length; clientIDPos++) {
                    if (area[x][y][clientIDPos]==id) return new Coordinate(x,y);
                }
            }
        }
        // not found
        return null;
    }

    /**
     * checks if the coordinate is ok for placing the id
     * @param position position to check
     * @return true, if this position is fine
     * */
    public boolean isFree(Coordinate position) {
        short x = position.getX();
        short y = position.getY();
        for (short clientIDPos = 0; clientIDPos < area[x][y].length; clientIDPos++) {
            if (area[x][y][clientIDPos]==NO_ID) return true;
        }
        // no space left
        return false;
    }

    /*
    * GETTER AND SETTER to update the area directly
    * */
    public short[][][] getArea() {
        synchronized (this) {
            return area;
        }
    }

    public void setArea(short[][][] area) {
        synchronized (this)
        {
            this.area = area;
        }
    }

    // DEBUG
    /**
     * prints the current area for debug reason
     * */
    public void print()
    {
        System.out.println("############## AREA     ###############");

        for (int y = 0; y < area[0].length; y++) {
            for (int x = 0; x < area.length; x++) {
                    StringBuilder toPrint = new StringBuilder("\t");
                    for (int i = 0; i < area[x][y].length; i++) {
                        toPrint.append(" ").append(area[x][y][i]);
                    }
                    System.out.print("|"+toPrint+"|");
            }
            System.out.println("");
        }
        System.out.println("############## AREA END ###############");
    }

    public void clear() {
        for (short x = 0; x < area.length; x++) {
            for (short y = 0; y < area[x].length; y++) {
                for (short clientIDPos = 0; clientIDPos < area[x][y].length; clientIDPos++) {
                    area[x][y][clientIDPos] = NO_ID;
                }
            }
        }
    }
}
