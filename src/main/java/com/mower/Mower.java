package com.mower;

public interface Mower {
    void move();
    Position tryMove();
    void rotate(boolean toRight);
    Position getPosition();
    Direction getDirection();
}
