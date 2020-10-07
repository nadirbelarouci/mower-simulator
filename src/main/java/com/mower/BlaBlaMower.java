package com.mower;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.mower.Direction.*;

public class BlaBlaMower implements Mower {
    private static final List<Direction> directions = Arrays.asList(N, E, S, W);
    private final Position mapCorner;
    private final int id;
    private Position position;
    private int direction;

    public BlaBlaMower(int id, String config, Position mapCorner) {
        this.id = id;
        this.position = new Position(config);
        this.direction = directions.indexOf(Direction.valueOf(config.split(" ")[2]));
        this.mapCorner = mapCorner;
    }

    @Override
    public void move() {
        position = tryMove();
    }

    @Override
    public Direction getDirection() {
        return directions.get(direction);
    }

    @Override
    public Position tryMove() {
        int x = position.x, y = position.y;
        switch (directions.get(direction)) {
            case N:
                y++;
                break;
            case E:
                x++;
                break;
            case S:
                y--;
                break;
            case W:
                x--;
                break;
        }
        if (x < 0 || y < 0 || x > mapCorner.x || y > mapCorner.y)
            return position;

        return new Position(x, y);
    }

    @Override
    public void rotate(boolean toRight) {
        if (toRight)
            direction = (++direction) % directions.size();
        else {
            direction--;
            if (direction < 0) {
                direction += directions.size();
            }
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return position.x + " " + position.y + " " + directions.get(direction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlaBlaMower that = (BlaBlaMower) o;
        return id == that.id &&
                direction == that.direction &&
                Objects.equals(mapCorner, that.mapCorner) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapCorner, id, position, direction);
    }
}
