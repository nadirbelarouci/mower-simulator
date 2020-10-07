package com.mower.commands;

import com.mower.Mower;

public class RightTurn implements Command {
    @Override
    public void execute(Mower mower) {
        mower.rotate(true);
    }

    @Override
    public String toString() {
        return "R";
    }
}
