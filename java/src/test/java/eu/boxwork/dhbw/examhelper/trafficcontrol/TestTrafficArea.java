package eu.boxwork.dhbw.examhelper.trafficcontrol;

import eu.boxwork.dhbw.examhelpers.trafficcontrol.Coordinate;
import eu.boxwork.dhbw.examhelpers.trafficcontrol.MovementNotPossible;
import eu.boxwork.dhbw.examhelpers.trafficcontrol.TrafficArea;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTrafficArea {

    @BeforeAll
    public static void init()
    {
    }

    @Test
    public void testPlaceSomewhere() {
        TrafficArea area = new TrafficArea((short) 1, (short) 10, (short) 10);
       try {
           area.place((short) 1, new Coordinate((short) 0, (short) 0));
       }
       catch (Exception e)
       {
           fail(e);
           e.printStackTrace();
       }
    }

    @Test
    public void testPlaceOutOfBounds() {
        TrafficArea area = new TrafficArea((short) 1, (short) 10, (short) 10);
        try {
            area.place((short) 1, new Coordinate((short) 11, (short) 0));
            fail("no error");
        }
        catch (Exception e)
        {
            assertTrue( e instanceof ArrayIndexOutOfBoundsException);
        }
    }

    @Test
    public void testPlaceOccupied() {
        TrafficArea area = new TrafficArea((short) 1, (short) 10, (short) 10);
        try {
            area.place((short) 1, new Coordinate((short) 0, (short) 0));
            area.place((short) 2, new Coordinate((short) 0, (short) 0));
            fail("no error");
        }
        catch (Exception e)
        {
            assertTrue( e instanceof MovementNotPossible);
        }
    }

    @Test
    public void testIsFree() {
        TrafficArea area = new TrafficArea((short) 1, (short) 10, (short) 10);
        try {
            area.place((short) 1, new Coordinate((short) 0, (short) 0));
            assertFalse(area.isFree(new Coordinate((short) 0, (short) 0)));
            assertTrue(area.isFree(new Coordinate((short) 1, (short) 0)));
            assertTrue(area.isFree(new Coordinate((short) 0, (short) 1)));
            assertTrue(area.isFree(new Coordinate((short) 1, (short) 1)));
        }
        catch (Exception e)
        {
            fail(e);
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPositionNotFound() {
        TrafficArea area = new TrafficArea((short) 1, (short) 10, (short) 10);
        try {
            Coordinate pos = area.getPosition((short) 1);
            assertNull(pos);
        }
        catch (Exception e)
        {
            fail(e);
            e.printStackTrace();
        }
    }

    @Test
    public void testPlaceSomewhereTwice() {
        TrafficArea area = new TrafficArea((short) 1, (short) 10, (short) 10);
        try {
            area.place((short) 1, new Coordinate((short) 0, (short) 0));
            area.place((short) 1, new Coordinate((short) 0, (short) 0));
            fail("no error");
        }
        catch (Exception e)
        {
            assertTrue( e instanceof MovementNotPossible);
        }
    }

    @Test
    public void testPlaceRemovePlaceSomewhere() {
        TrafficArea area = new TrafficArea((short) 1, (short) 10, (short) 10);
        try {
            area.place((short) 1, new Coordinate((short) 0, (short) 0));
            area.remove((short) 1, new Coordinate((short) 0, (short) 0));
            area.place((short) 1, new Coordinate((short) 0, (short) 0));
        }
        catch (Exception e)
        {
            fail(e);
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveNotFound() {
        TrafficArea area = new TrafficArea((short) 1, (short) 10, (short) 10);
        try {
            area.remove((short) 1, new Coordinate((short) 0, (short) 0));
            fail("no error");
        }
        catch (Exception e)
        {
            assertTrue( e instanceof MovementNotPossible);
        }
    }

    @Test
    public void testPlaceTwiceSomewhere() {
        TrafficArea area = new TrafficArea((short) 1, (short) 10, (short) 10);
        try {
            area.place((short) 1, new Coordinate((short) 0, (short) 0));
            area.remove((short) 1, new Coordinate((short) 0, (short) 0));
            area.place((short) 1, new Coordinate((short) 1, (short) 2));
            Coordinate ret = area.getPosition((short)1);
            assertEquals(1, ret.getX());
            assertEquals(2, ret.getY());
        }
        catch (Exception e)
        {
            fail(e);
            e.printStackTrace();
        }
    }
}
