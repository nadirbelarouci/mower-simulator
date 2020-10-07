package com.mower.commands;

import com.mower.BlaBlaMower;
import com.mower.Direction;
import com.mower.Mower;
import com.mower.Position;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {

    @Test
    void newInstances() {
        List<Command> commands = Arrays.asList(new Forward(), new Forward(), new LeftTurn(),
                new RightTurn(), new Forward(), new LeftTurn());
        List<Command> toTest = Command.newInstances("FFLRFL");
        assertEquals(commands.size(), toTest.size());
        for (int i = 0; i < commands.size(); i++) {
            assertEquals(commands.get(i).getClass(), toTest.get(i).getClass());
        }
    }

    @Test
    void list_of_commands_should_be_executed_on_a_mower_correctly() {
        List<Command> toTest = Command.newInstances("LFLFLFLFF");
        Mower mower1 = new BlaBlaMower(0, "1 2 N", new Position(5, 5));
        toTest.forEach(command -> command.execute(mower1));
        assertEquals(new Position(1,3),mower1.getPosition());
        assertEquals(Direction.N,mower1.getDirection());

        Mower mower2 = new BlaBlaMower(1, "3 3 E", new Position(5, 5));
        toTest = Command.newInstances("FFRFFRFRRF");
        toTest.forEach(command -> command.execute(mower2));
        assertEquals(new Position(5,1),mower2.getPosition());
        assertEquals(Direction.E,mower2.getDirection());


    }

}
