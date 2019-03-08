package com.exerp.uniquewords;

public class StringCleaner {
  
  private static final String INVALID_CHARACTERS = "[^a-z \\-']";
  
  /**
   * Cleans the string of the invalid characters
   * @param string - a string
   * @return - the cleaned string
   */
  public String clean(String dirty) {
    if (dirty == null) {
      return "";
    }
    String clean = dirty.replaceAll(INVALID_CHARACTERS, " ");
    clean = clean.replaceAll("  ", " ");
    
    return clean.trim();
  }
}
