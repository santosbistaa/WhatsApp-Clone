package com.whatsapp.clone.file;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
/*
This is the utility helper class that is designed to read files from the disk and
return their contents as a byte[].
--> returns byte[0]  if file is not found or path is invalid
*/
public class FileUtils {

    private FileUtils() {}

    public static byte[] readFileFromLocation(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) {
            return new byte[0];
        }
        try {
            Path file = new File(fileUrl).toPath();
            return Files.readAllBytes(file);
        } catch (IOException e) {
            log.warn("No file found in the path {}", fileUrl);
        }
        return new byte[0];
    }
}
