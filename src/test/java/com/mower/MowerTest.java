package com.mower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MowerTest {
    Mower mower;

    @BeforeEach
    void setup() {
        mower = new BlaBlaMower(0, "1 2 N", new Position(5, 5));
    }


    @Test
    void move() {
        mower.move();
        assertEquals(new Position(1, 3), mower.getPosition());
        assertEquals(Direction.N, mower.getDirection());

        mower.move();
        mower.move();
        mower.move();
        assertEquals(new Position(1, 5), mower.getPosition());
        assertEquals(Direction.N, mower.getDirection());

        mower.rotate(true);
        mower.move();
        assertEquals(new Position(2, 5), mower.getPosition());
        assertEquals(Direction.E, mower.getDirection());

        mower.rotate(true);
        mower.move();
        assertEquals(new Position(2, 4), mower.getPosition());
        assertEquals(Direction.S, mower.getDirection());

        mower.rotate(true);
        mower.move();
        assertEquals(new Position(1, 4), mower.getPosition());
        assertEquals(Direction.W, mower.getDirection());

        mower.move();
        mower.move();
        mower.move();
        assertEquals(new Position(0, 4), mower.getPosition());
        assertEquals(Direction.W, mower.getDirection());

    }

    @Test
    void rotate() {
        mower.rotate(true);
        assertEquals(Direction.E, mower.getDirection());
        mower.rotate(true);
        assertEquals(Direction.S, mower.getDirection());
        mower.rotate(true);
        assertEquals(Direction.W, mower.getDirection());
        mower.rotate(true);
        assertEquals(Direction.N, mower.getDirection());
        mower.rotate(false);
        assertEquals(Direction.W, mower.getDirection());
        mower.rotate(false);
        assertEquals(Direction.S, mower.getDirection());
        mower.rotate(false);
        assertEquals(Direction.E, mower.getDirection());
        mower.rotate(false);
        assertEquals(Direction.N, mower.getDirection());


    }
}
