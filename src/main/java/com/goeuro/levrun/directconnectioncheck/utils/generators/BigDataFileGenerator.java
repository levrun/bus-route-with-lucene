package com.goeuro.levrun.directconnectioncheck.utils.generators;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class BigDataFileGenerator {

    public static String BIG_FILE_PATH = "C:\\dev\\interviews\\goeuro\\directconnectioncheck\\src\\main\\resources\\big.txt";
    public static String EXAMPLE_FILE_PATH = "C:\\dev\\interviews\\goeuro\\directconnectioncheck\\src\\main\\resources\\example.txt";

    public void generateBigFile() {
        int NUMBER_OF_ROUTES = 100000;
        int STATIONS_ID_RANGE = 1000000;
        int NUMBER_OF_STATIONS_PER_ROUTE = 1000;

        writeLineToBigFile(NUMBER_OF_ROUTES + "\n");
        Set<Integer> randomStorage = new HashSet<>();
        for(int i = 0; i < NUMBER_OF_ROUTES; i++) {
            String randomRoute = "";
            for(int j = 0; j < NUMBER_OF_STATIONS_PER_ROUTE; j++) {
                int randomNumber = ThreadLocalRandom.current().nextInt(0, STATIONS_ID_RANGE + 1);
                while(randomStorage.contains(randomNumber)) {
                    randomNumber = ThreadLocalRandom.current().nextInt(0, STATIONS_ID_RANGE + 1);
                }
                randomStorage.add(randomNumber);

                randomRoute = randomRoute +  randomNumber + " ";
            }
            randomStorage.clear();
            writeLineToBigFile(i + " " + randomRoute + "\n");
        }

    }

    private void writeLineToBigFile(String str) {
        byte data[] = str.getBytes();
        Path p = Paths.get(EXAMPLE_FILE_PATH);

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

}
