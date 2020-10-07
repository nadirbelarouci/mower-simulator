package com.mower;

import com.mower.commands.Command;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Configuration {
    private final Map<Mower, List<Command>> mowers = new LinkedHashMap<>();

    public Configuration(InputStream configurationFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(configurationFile, StandardCharsets.UTF_8))){

            String mapCorner = reader.readLine();
            Position mapCorner1 = new Position(mapCorner);
            String mowerConfig;
            String commands;
            int id = 0;
            while ((mowerConfig = reader.readLine()) != null) {
                commands = reader.readLine();
                mowers.put(new BlaBlaMower(++id, mowerConfig, mapCorner1), Command.newInstances(commands));
            }
        }
    }

    public Map<Mower, List<Command>> getMowers() {
        return mowers;
    }
}
