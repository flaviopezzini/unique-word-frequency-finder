package com.exerp.uniquewords;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

public class UniqueWordFrequencyFinderTest {
  
  private UniqueWordFrequencyFinder uniqueWordFrequencyFinder;
  
  @Before
  public void setUp() {
    uniqueWordFrequencyFinder = new UniqueWordFrequencyFinder(new StringCleaner());
  }

  @Test(expected = InvalidResultSizeException.class)
  public void shouldNotAcceptResultSizeBelowZero() {
    List<String> empty = Arrays.asList();
    uniqueWordFrequencyFinder.find(empty.stream(), -1);
  }
  
  @Test
  public void shouldHandleEmptyList() {
    List<String> empty = Arrays.asList();
    Map<String, Integer> result = uniqueWordFrequencyFinder.find(empty.stream(), 10);
    
    Map<String, Integer> expected = new HashMap<String, Integer>() {{
    }};
    assertEquals(expected, result);
  }
  
  @Test
  public void shouldHandleJustOneWord() {
    List<String> one = Arrays.asList("one");
    Map<String, Integer> result = uniqueWordFrequencyFinder.find(one.stream(), 10);
    Map<String, Integer> expected = new HashMap<String, Integer>() {{
      put("one", 1);
    }};
    assertEquals(expected, result);
  }
  
  @Test
  public void shouldOrderWordsCorrectly() {
    List<String> words = Arrays.asList("first", "first", "first", "second", "second", "third");
    Map<String, Integer> result = uniqueWordFrequencyFinder.find(words.stream(), 10);
    
    Map<String, Integer> expected = new HashMap<String, Integer>() {{
      put("first", 3);
      put("second", 2);
      put("third", 1);
    }};
    assertEquals(expected, result);
  }
  
  @Test
  public void shouldHandleSpecialCharacters() {
    List<String> words = Arrays.asList("first", "first.", "first,", "second.", "second@", "third#");
    Map<String, Integer> result = uniqueWordFrequencyFinder.find(words.stream(), 10);
    
    Map<String, Integer> expected = new HashMap<String, Integer>() {{
      put("first", 3);
      put("second", 2);
      put("third", 1);
    }};
    assertEquals(expected, result);
  }
  
  @Test
  public void shouldLimitResults() {
    List<String> words = Arrays.asList("first", "first", "first", "second", "second", "third");
    Map<String, Integer> result = uniqueWordFrequencyFinder.find(words.stream(), 2);
    
    Map<String, Integer> expected = new HashMap<String, Integer>() {{
      put("first", 3);
      put("second", 2);
    }};
    assertEquals(expected, result);
  }
  
  @Test
  public void shouldHandleFileStreams() throws IOException {
    Stream<String> fileLines = Files.lines(Paths.get("src/test/resources/tempest.txt"));
    
    Map<String, Integer> result = uniqueWordFrequencyFinder.find(fileLines, 10);
    Map<String, Integer> expected = new HashMap<String, Integer>() {{
      put("and", 514);
      put("the", 513);
      put("i", 446);
      put("to", 324);
      put("a", 310);
      put("of", 295);
      put("my", 288);
      put("you", 211);
      put("that", 188);
      put("this", 185);
    }};
    
    assertEquals(expected, result);
  }
}
