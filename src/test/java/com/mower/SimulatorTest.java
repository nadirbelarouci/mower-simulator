package com.mower;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulatorTest {

    private static Stream<Arguments> simulatorTestCaseInputProvider() {
        return Stream.of(
                Arguments.of("input1",
                        Arrays.asList(
                                new BlaBlaMower(1, "1 3 N", new Position(5, 5)),
                                new BlaBlaMower(2, "5 1 E", new Position(5, 5)))

                ),

                /*
                *   mower 1 should not step on  mower 2 since this last one doesn't move.
                *   mower 1 have the same path at the first test case.
                * */
                Arguments.of("input2",
                        Arrays.asList(
                                new BlaBlaMower(1, "1 2 N", new Position(5, 5)),
                                new BlaBlaMower(2, "1 3 W", new Position(5, 5)),
                                new BlaBlaMower(3, "2 3 S", new Position(5, 5)))

                ));
    }


    @ParameterizedTest
    @MethodSource("simulatorTestCaseInputProvider")
    void simulate_should_execute_commands_for_each_mower_in_the_config_file(
            String configFile, List<Mower> mowers) throws IOException {
        System.out.println(configFile);
        Simulator simulator = new Simulator(new Configuration(getClass().getResourceAsStream(configFile)));
        simulator.simulate();
        assertEquals(simulator.getMowers().size(), mowers.size());

        AtomicInteger i = new AtomicInteger(0);
        simulator.getMowers().forEach((mower, commands) -> {
            Mower expected = mowers.get(i.getAndIncrement());
            assertEquals(expected, mower);
        });

    }


}
