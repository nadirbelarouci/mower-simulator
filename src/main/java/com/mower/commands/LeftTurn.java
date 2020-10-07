package com.mower.commands;

import com.mower.Mower;

public class LeftTurn implements Command {
    @Override
    public void execute(Mower mower) {
        mower.rotate(false);
    }

    @Override
    public String toString() {
        return "L";
    }
}
