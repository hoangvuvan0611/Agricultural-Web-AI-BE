package com.ttcn.vnuaexam.utils;

import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);
    public static final char COMMA = '.';

    public FileUtils() {
    }

    public static String getFileExtension(String filename) {
        if (StringUtils.isBlank(filename)) {
            return null;
        } else {
            int pos = filename.lastIndexOf(".");
            return pos > 0 ? filename.substring(pos + 1) : null;
        }
    }

    public static void saveFile(String filePath, byte[] data) {
        try {
            FileOutputStream stream = new FileOutputStream(filePath);

            try {
                stream.write(data);
            } catch (Throwable var6) {
                try {
                    stream.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            stream.close();
        } catch (IOException var7) {
            IOException e = var7;
            log.error("ERROR save filePath = {}", filePath, e);
        }

    }

    public static void saveFile(String filePath, MultipartFile file) {
        try {
            FileOutputStream stream = new FileOutputStream(filePath);

            try {
                stream.write(file.getBytes());
            } catch (Throwable var6) {
                try {
                    stream.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            stream.close();
        } catch (IOException var7) {
            IOException e = var7;
            log.error("ERROR save filePath = {}", filePath, e);
        }

    }

    public static void deleteFile(String filePath) {
        try {
            File fileToDelete = new File(filePath);
            Files.delete(fileToDelete.toPath());
        } catch (IOException var2) {
            IOException e = var2;
            log.error("ERROR delete filePath = {}", filePath, e);
        }

    }
}
