package com.exerp.uniquewords;

import static java.util.stream.Collectors.toMap;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class UniqueWordFrequencyFinder {

  private StringCleaner stringCleaner;

  public UniqueWordFrequencyFinder(StringCleaner stringCleaner) {
    this.stringCleaner = stringCleaner;
  }

  /**
   * Find all unique words in the given BufferedReader, count their frequency and sort them by
   * descending order. Limit the result by the limit parameter
   * 
   * @param lines - lines of text to process
   * @param resultSize - how many results to return
   * @return sorted frequency list of words with their occurrence count
   * @throws InvalidResultSizeException 
   * @throws IOException
   */
  public Map<String, Integer> find(Stream<String> lines, int resultSize) 
      throws InvalidResultSizeException {
    Map<String, Integer> result = new HashMap<>();
    
    if (resultSize < 0) {
      throw new InvalidResultSizeException("Result Size must be higher than zero.");
    } else if (resultSize == 0) {
      return result; // no need to process anything
    }

    lines.forEach(line -> {
      line = stringCleaner.clean(line.toLowerCase());
      if (!line.isEmpty()) {
        String[] words = line.split(" ");
        for (String word : words) {
          int count = 0;
          if (result.containsKey(word)) {
            count = result.get(word);
          }
          result.put(word, count + 1);
        }
      }
    });

    return result
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(resultSize)
            .collect(
                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
  }

}
