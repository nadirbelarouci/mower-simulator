package com.mower.commands;

import com.mower.Mower;

public class Forward implements Command {
    @Override
    public void execute(Mower mower) {
         mower.move();
    }

    @Override
    public String toString() {
        return "F";
    }
}
