package com.ttcn.vnuaexam.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public final class FileUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    public FileUtils() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Get file extension from filename
     * @param filename the name of the file
     * @return the extension or null if none exists
     */
    public static Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> !f.isBlank())
                .map(f -> f.lastIndexOf("."))
                .filter(pos -> pos > 0)
                .map(pos -> filename.substring(pos + 1));
    }

    /**
     * Save byte array to file
     * @param filePath path to save the file
     * @param data byte array to save
     * @throws IOException if an I/O error occurs
     */
    public static void saveFile(Path filePath, byte[] data) throws IOException {
        try {
            // Create parent directories if they don't exist
            Files.createDirectories(filePath.getParent());

            // Use try-with-resources for automatic resource management
            try (var outputStream = Files.newOutputStream(filePath,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING)) {
                outputStream.write(data);
            }
        } catch (IOException e) {
            log.error("Error saving file to path = {}", filePath, e);
            throw e; // Rethrow to allow caller to handle
        }
    }

    /**
     * Save MultipartFile to file system
     * @param filePath path to save the file
     * @param file MultipartFile to save
     * @throws IOException if an I/O error occurs
     */
    public static void saveFile(Path filePath, MultipartFile file) throws IOException {
        try {
            saveFile(filePath, file.getBytes());
        } catch (IOException e) {
            log.error("Error saving MultipartFile to path = {}", filePath, e);
            throw e;
        }
    }

    /**
     * Delete file from file system
     * @param filePath path of file to delete
     * @throws IOException if an I/O error occurs
     */
    public static void deleteFile(Path filePath) throws IOException {
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.error("Error deleting file at path = {}", filePath, e);
            throw e;
        }
    }
}
