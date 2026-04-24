package com.cleancode.martinfowler.videostore;


import org.junit.Before;
import org.junit.Test;

import com.cleancode.martinfowler.videostore.ChildrensMovie;
import com.cleancode.martinfowler.videostore.NewReleaseMovie;
import com.cleancode.martinfowler.videostore.RegularMovie;
import com.cleancode.martinfowler.videostore.Rental;
import com.cleancode.martinfowler.videostore.Statement;

import static org.junit.Assert.*;

public class VideoStoreTest {

    @Before
    public void setUp() {
        statement = new Statement("Fred");
    }

    @Test
    public void testSingleNewReleaseStatement() {
        statement.addRental(new Rental(new NewReleaseMovie("The Cell"), 3));
        assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n", statement.statement());
    }

    @Test
    public void testDualNewReleaseStatement() {
        statement.addRental(new Rental(new NewReleaseMovie("The Cell"), 3));
        statement.addRental(new Rental(new NewReleaseMovie("The Tigger Movie"), 3));
        assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n", statement.statement());
    }

    @Test
    public void testSingleChildrensStatement() {
        statement.addRental(new Rental(new ChildrensMovie("The Tigger Movie"), 3));
        assertEquals("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n", statement.statement());
    }

    @Test
    public void testMultipleRegularStatement() {
        statement.addRental(new Rental(new RegularMovie("Plan 9 from Outer Space"), 1));
        statement.addRental(new Rental(new RegularMovie("8 1/2"), 2));
        statement.addRental(new Rental(new RegularMovie("Eraserhead"), 3));

        assertEquals("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", statement.statement());
    }

    private Statement statement;
}
