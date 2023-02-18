package eu.boxwork.dhbw.examhelper.trafficcontrol;

import eu.boxwork.dhbw.examhelpers.trafficcontrol.Coordinate;
import eu.boxwork.dhbw.examhelpers.trafficcontrol.MovementNotPossible;
import eu.boxwork.dhbw.examhelpers.trafficcontrol.TrafficArea;
import eu.boxwork.dhbw.examhelpers.trafficcontrol.TrafficControlLogic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTrafficControlLogic {

    private static TrafficArea area = null;
    public static TrafficControlLogic logic = null;

    @BeforeAll
    public static void init()
    {
        area = new TrafficArea((short) 1, (short) 3, (short) 3);
        logic = new TrafficControlLogic(area);
    }

    @BeforeEach
    public void initTest()
    {
        area.clear();
    }

    @Test
    public void testStartOne() {
       try {
           logic.start((short)1);
           Coordinate pos = area.getPosition((short)1);
           assertEquals(0,pos.getX());
           assertEquals(0,pos.getY());
       }
       catch (Exception e)
       {
           fail(e);
           e.printStackTrace();
       }
    }

    @Test
    public void testStartOneTwice() {
        try {
            logic.start((short)1);
            logic.start((short)1);
            fail("no error");
        }
        catch (Exception e)
        {
            assertTrue(e instanceof MovementNotPossible);
        }
    }

    @Test
    public void testStartTwo() {
        try {
            logic.start((short)1);
            Coordinate pos = area.getPosition((short)1);
            logic.start((short)2);
            Coordinate pos2 = area.getPosition((short)2);
            assertEquals(0,pos.getX());
            assertEquals(0,pos.getY());
            assertEquals(0,pos2.getX());
            assertEquals(1,pos2.getY());
        }
        catch (Exception e)
        {
            fail(e);
            e.printStackTrace();
        }
    }

    @Test
    public void testStartMax() {
        try {
            logic.start((short)1);
            logic.start((short)2);
            logic.start((short)3);
            Coordinate pos = area.getPosition((short)3);
            assertEquals(0,pos.getX());
            assertEquals(2,pos.getY());
        }
        catch (Exception e)
        {
            fail(e);
            e.printStackTrace();
        }
    }

    @Test
    public void testStartMoreThanMax() {
        try {
            logic.start((short)1);
            logic.start((short)2);
            logic.start((short)3);
            logic.start((short)4);
            fail("no error");
        }
        catch (Exception e)
        {
            assertTrue(e instanceof MovementNotPossible);
        }
    }

    @Test
    public void testMoveOne() {
        try {
            logic.start((short)1);
            logic.start((short)2);
            Coordinate target = new Coordinate((short) 1, (short) 1);
            logic.move((short) 1,target);
            Coordinate pos = area.getPosition((short)1);
            assertEquals(1,pos.getX());
            assertEquals(1,pos.getY());
        }
        catch (Exception e)
        {
            fail(e);
            e.printStackTrace();
        }
    }

    @Test
    public void testStayPosition() {
        try {
            logic.start((short)1);
            Coordinate target = new Coordinate((short) 0, (short) 0);
            logic.move((short) 1,target);
            Coordinate pos = area.getPosition((short)1);
            assertEquals(0,pos.getX());
            assertEquals(0,pos.getY());
        }
        catch (Exception e)
        {
            fail(e);
            e.printStackTrace();
        }
    }

    @Test
    public void testMoveOneOutOfArea() {
        try {
            logic.start((short)1);
            Coordinate target = new Coordinate((short) 3, (short) 3);
            logic.move((short) 1,target);
            logic.move((short) 1,target);
            logic.move((short) 1,target);
            logic.move((short) 1,target); // stops at 2/2
            Coordinate pos = area.getPosition((short)1);
            assertEquals(2,pos.getX());
            assertEquals(2,pos.getY());
        }
        catch (Exception e)
        {
            fail(e);
            e.printStackTrace();
        }
    }

    @Test
    public void testMoveTwoSame() {
        try {
            logic.start((short)1);
            logic.start((short)2);
            Coordinate target = new Coordinate((short) 1, (short) 1);
            logic.move((short) 1,target);
            logic.move((short) 2,target);
            Coordinate pos = area.getPosition((short)1);
            assertEquals(1,pos.getX());
            assertEquals(1,pos.getY());

            pos = area.getPosition((short)2);
            assertEquals(0,pos.getX());
            assertEquals(1,pos.getY());
        }
        catch (Exception e)
        {
            fail(e);
            e.printStackTrace();
        }
    }

    @Test
    public void testMoveTwoNotSame() {
        try {
            logic.start((short)1);
            logic.start((short)2);
            Coordinate target = new Coordinate((short) 1, (short) 1);
            Coordinate target2 = new Coordinate((short) 1, (short) 0);
            logic.move((short) 1,target);
            logic.move((short) 2,target2);
            Coordinate pos = area.getPosition((short)1);
            assertEquals(1,pos.getX());
            assertEquals(1,pos.getY());

            pos = area.getPosition((short)2);
            assertEquals(1,pos.getX());
            assertEquals(0,pos.getY());
        }
        catch (Exception e)
        {
            fail(e);
            e.printStackTrace();
        }
    }

}
