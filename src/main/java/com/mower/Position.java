package com.mower;

import java.util.Objects;

public class Position {
    public final int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(String position) {
        String []p = position.split("\\s+");
        this.x = Integer.parseInt(p[0]);
        this.y = Integer.parseInt(p[1]);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
