package eu.boxwork.dhbw.examhelpers.trafficcontrol;

/**
 * simple coordinate class used
 * */
public class Coordinate {
    short x = -1;
    short y = -1;

    public Coordinate(short x, short y) {
        this.x = x;
        this.y = y;
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }
}
