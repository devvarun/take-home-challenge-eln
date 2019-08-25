package com.labforward.eln.util;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Dev
 *
 */
public class StringUtils {

	/**
	 * This method returns the Levenshtein Distance between provided strings
	 * @param str1 String 1
	 * @param str2 String 2
	 * @return int Levenshtein Distance
	 */
	public static int calculateLevenshteinDistance(String str1, String str2){
		
		//if first string is empty then the distance will always be length of other string
		if (str1.isEmpty()) {
            return str2.length();
        }
 
		//if second string is empty then the distance will always be length of first string
        if (str2.isEmpty()) {
            return str1.length();
        } 
        
        //Calculate distances for substitution, insertion & deletion 
        int ttlSubstitutions = calculateLevenshteinDistance(str1.substring(1), str2.substring(1)) + substitutionCost(str1.charAt(0), str2.charAt(0));
        
        int ttlInsertions = calculateLevenshteinDistance(str1, str2.substring(1)) + 1;
        
        int ttlDeletions = calculateLevenshteinDistance(str1.substring(1), str2) + 1;
        
        //Minimum distance between substitution, insertion & deletion will be the Levenshtein Distance
        int minDis = ttlSubstitutions < ttlInsertions ? ttlSubstitutions : ttlInsertions;
        
        if(ttlDeletions < minDis)
        	minDis = ttlDeletions;
        
        return minDis; //return Levenshtein Distance
	}
	
	public static List<String> getWords(String text) {
	    List<String> words = new ArrayList<String>();
	    BreakIterator breakIterator = BreakIterator.getWordInstance();
	    breakIterator.setText(text);
	    int lastIndex = breakIterator.first();
	    while (BreakIterator.DONE != lastIndex) {
	        int firstIndex = lastIndex;
	        lastIndex = breakIterator.next();
	        if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
	            words.add(text.substring(firstIndex, lastIndex));
	        }
	    }

	    return words;
	}
	
	
	private static int substitutionCost(char a, char b) {
        return a == b ? 0 : 1;
    }
}
