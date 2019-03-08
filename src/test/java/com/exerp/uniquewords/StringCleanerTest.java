package com.exerp.uniquewords;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StringCleanerTest {
  
  private StringCleaner stringCleaner;
  
  @Before
  public void setUp() {
    stringCleaner = new StringCleaner();
  }

  @Test
  public void shouldHandleNullString() {
    assertEquals("", stringCleaner.clean(null));
  }
  
  @Test
  public void shouldHandleEmptyString() {
    assertEquals("", stringCleaner.clean(""));
  }
  
  @Test
  public void shouldStripBadCharacters() {
    assertEquals("", stringCleaner.clean("!@#$%ˆ&*()\n\t\0"));
  }
  
}
