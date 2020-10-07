package com.mower;

import com.mower.commands.Command;
import com.mower.commands.Forward;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Simulator {
    private final Map<Mower, List<Command>> mowers;
    private final ConcurrentHashMap<Position, Semaphore> positions = new ConcurrentHashMap<>();
    private final int parallelism;

    public Simulator(Configuration config) {
        this(config, 4);
    }

    public Simulator(Configuration config, int parallelism) {
        mowers = config.getMowers();
        mowers.forEach((mower, commands) -> {
            if (!positions.computeIfAbsent(mower.getPosition(), p -> new Semaphore(1)).tryAcquire())
                throw new IllegalStateException("Two mowers should not be in the same position at initialisation");
        });
        this.parallelism = parallelism;
    }

    /**
     * Simulate the mower movements according to the configuration file commands and initial positions.
     * Multiple calls to this method may not be consistent, due to the multi-threaded environment (When
     * the path of two mowers collides in a same period of time).
     */
    public void simulate() {
        ExecutorService executor = Executors.newFixedThreadPool(this.parallelism);
        CompletableFuture.runAsync(this::execute, executor)
                .join();
        executor.shutdown();

        mowers.keySet()
                .forEach(System.out::println);
    }

    private void execute() {
        mowers.entrySet()
                .parallelStream()
                .forEach(this::execute);
    }

    private void execute(Map.Entry<Mower, List<Command>> mower) {
        mower.getValue().forEach(command -> execute(mower.getKey(), command));
    }

    /**
     * This method execute a command for a mower
     * Rotation commands are not an issue when they are executed in a multi-threaded env
     * Forward commands however, needs to be synchronized, in order to achieve that, we map
     * for each newPos that a mower may go to, a Semaphore of 1 permit, whoever acquire it, will move
     * to this position i.e newPos, this semaphore will be released once the mower moves to another position.
     * Note that the use computeIfAbsent is necessary, since this method block other calls, and atomic,
     * this doesn't guarantee that its caller will acquire the semaphore, it guarantees that there is only one
     * instance of that semaphore in that newPos.
     */
    private void execute(Mower mower, Command command) {
        if (command instanceof Forward) {
            Position oldPos = mower.getPosition();
            Position newPos = mower.tryMove();

            Semaphore posLock = positions.computeIfAbsent(newPos, position -> new Semaphore(1));
            if (posLock.tryAcquire()) {
                command.execute(mower);
                if (!newPos.equals(oldPos))
                    positions.get(oldPos).release();
            }

        } else {
            command.execute(mower);
        }
    }

    Map<Mower, List<Command>> getMowers() {
        return mowers;
    }
}
