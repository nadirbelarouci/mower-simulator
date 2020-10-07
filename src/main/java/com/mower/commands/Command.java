package com.mower.commands;

import com.mower.Mower;

import java.util.*;

public interface Command {
    static List<Command> newInstances(String stream) {
        List<Command> commands = new ArrayList<>();
        for (char c : stream.toCharArray())
            commands.add(newInstance(c));
        return commands;
    }

    static Command newInstance(char command) {
        switch (command) {
            case 'F':
                return new Forward();
            case 'L':
                return new LeftTurn();
            case 'R':
                return new RightTurn();
        }
        throw new IllegalArgumentException("Command '" + command + "' is not found");
    }

    void execute(Mower mower);

}
