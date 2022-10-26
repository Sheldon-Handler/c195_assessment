package com.example.c195_assessment;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Java class LogWriter
 * Contains method for appending log file
 */
public class LogWriter {

    /**
     * Searches for a file named "login_activity.txt" in root folder.
     * Appends the string parameter into the file if the file found.
     * If "login_activity.txt" is not found in new directory, creates file with that name and writes string parameter to it.
     *
     * @param string String to append insert into the file
     */
    public void addToLog(String string) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("login_activity.txt", true);
            fileOutputStream.write((string + "\n").getBytes());
            fileOutputStream.close();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
