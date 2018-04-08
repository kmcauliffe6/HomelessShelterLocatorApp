//package edu.gatech.cs2340.thisteamnameapp;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import static junit.framework.TestCase.assertEquals;
//
//import static org.junit.Assert.assertNull;
//import static org.mockito.Mockito.*;
//
///**
// * Created by robertwaters on 3/16/16.
// */
//public class ModelTest {
//    private Model testModel;
//
//    @Before
//    public void setUp() {
//        testModel = new Model();
//    }
//
//    @Test
//    public void testAdd() {
//        assertEquals("Simple Add result incorrect",56, testModel.execute(16, 40, "+"));
//    }
//
//    @Test
//    public void testMultiply() {
//        assertEquals("Simple multiply incorrect", 21, testModel.execute(7,3, "*"));
//    }
//
//    @Test
//    public void testDatabase() {
//        Database db = mock(Database.class);
//        when(db.getStudentIDFor("Bob")).thenReturn("XY32F14");
//        when(db.getStudentNameFor(1234)).thenReturn("Bob Waters");
//
//        assertEquals("XY32F14", db.getStudentIDFor("Bob"));
//        assertNull(db.getStudentIDFor("Sally"));
//
//        assertEquals("Bob Waters", db.getStudentNameFor(1234));
//        assertNull(db.getStudentNameFor(5463));
//    }
//
//}
