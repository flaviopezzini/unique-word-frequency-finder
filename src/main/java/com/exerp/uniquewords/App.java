package com.exerp.uniquewords;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Entry point for the unique words program.
 * The program will take 2 arguments, the file name and the limit
 * which determines how many results to return
 */
public class App {

  public static void main(String[] args) {
    String fileName;
    int resultSize = 10;
    if (args.length == 1) {
      fileName = args[0];
    } else if (args.length == 2) {
      fileName = args[0];
      try {
        resultSize = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        System.err.println("Invalid result size parameter.");
        return;
      }
    } else {
      System.out.println("Usage: uniqueWords file-path [result-size]");
      return;
    }
    
    UniqueWordFrequencyFinder finder = new UniqueWordFrequencyFinder(new StringCleaner());
    Stream<String> lines;
    try {
      lines = Files.lines(Paths.get(fileName));
      Map<String, Integer> result = finder.find(lines, resultSize);

      result.forEach((key, value) -> {
        System.out.println(key + " (" + value + ")");
      });

    } catch (IOException e) {
      System.err.println("There has been an error processing your file: " + e.getMessage());
    } catch (InvalidResultSizeException e) {
      System.err.println(e.getMessage());
    }
  }

}
