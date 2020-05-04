package org.webcrawler.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Marker interface for all parsed documents
 */
public interface ParsedContent {

    byte[] getRawData();

    /**
     * Store file on selected path
     * @param pathToStore path to file in which store content
     */
    default void store(Path pathToStore) {
        try {
            Files.write(pathToStore, getRawData(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
