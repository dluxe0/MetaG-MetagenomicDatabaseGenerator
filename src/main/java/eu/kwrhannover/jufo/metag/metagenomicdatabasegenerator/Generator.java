package eu.kwrhannover.jufo.metag.metagenomicdatabasegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.function.Consumer;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Generator {
    public static void generateDatabases(Path path, int databases, int reads, boolean createFolder, final Consumer<Double> updateProgress) {
        System.out.println("Generating " + databases + " Databases...");

        Path generatingPath = path;
        if (createFolder) {
            File genrateDir = new File(path + "/MetaG_Autogenerated_Metagenomic_Databases");
            genrateDir.mkdirs();
            generatingPath = Path.of(String.valueOf(genrateDir));
        }
        for (int i = 0; i < databases; ++i) {
            updateProgress.accept(i / (double) databases);
            System.out.println("Generating Database " + i + " / " + databases);
            generateDatabase(generatingPath, reads);
        }
        System.out.println("Finish");
    }

    private static void generateDatabase(Path geratingPath, int reads) {
        Path databaseFile = Path.of(geratingPath + "/MetaG_Autogenerated_Metagenomic_Database_" + (LocalDateTime.now().toString().replace(":", "")) + ".csv");
        try (BufferedWriter databaseWriter = Files.newBufferedWriter(databaseFile, UTF_8)) {
            for (int i = 0; i < reads; i++) {
                databaseWriter.write(Math.random() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
