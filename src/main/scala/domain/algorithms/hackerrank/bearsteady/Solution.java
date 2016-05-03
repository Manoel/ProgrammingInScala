package domain.algorithms.hackerrank.bearsteady;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {  
    	
    	for (int i = 0; i < 11; i++) 
    		System.out.println(UUID.randomUUID());
    	
        Scanner input = new Scanner(System.in);
        
        input.nextInt();
                      
        String s = input.next();
        
        input.close();
        
        System.out.println(minimumToBeSteady(s));
        
        
    }
    
    public static int minimumToBeSteady(String s) {
        int n = s.length();
        
        int steady = n / 4;
        
        boolean alreadySteady = true;
        
        Map<Character, Integer> count = new HashMap<Character, Integer>();
        
        for (char c: s.toCharArray()) {
            if (!count.containsKey(c))
                count.put(c, 0);
            count.put(c, count.get(c) + 1);
            
            if (alreadySteady && count.get(c) > steady)
                alreadySteady = false;
        }
        
        if (alreadySteady)
           return 0;
        else {
            String candidate = candidate(count, steady);
            
            if (search(s, candidate))
                return candidate.length();
            else {
                List<Character> lessThanOrEqual = lessThanOrEqualToSteady(count, steady);
                
                List<String> toAdd = new ArrayList<String>();
                toAdd.add("");
                
                for (int i = candidate.length(); i < s.length() ; i++) {
                    toAdd = add(lessThanOrEqual, toAdd);
                    for (String a: toAdd) {
                        String c = candidate + a;
                        if (search(s, c)) {
                            return c.length();
                        }
                    }                                     
                }
                
                return s.length();
            }
        }
    }
    
    public static List<String> add(List<Character> lessThanOrEqual, List<String> toAdd) {
        List<String> result = new ArrayList<String>();
        
        for (String s: toAdd) {
            for (char a: lessThanOrEqual) {
                result.add(s + (a+""));
            }
        }
        
        return result;        
    }
    
    public static List<Character> lessThanOrEqualToSteady(Map<Character, Integer> count, int steady) {
        List<Character> result = new ArrayList<Character>();
        
        for (Map.Entry<Character, Integer> e: count.entrySet()) {
            if (e.getValue() <= steady) 
                result.add(e.getKey());
        }
        
        return result;
    }
    
    public static String candidate(Map<Character, Integer> count, int steady) {
        StringBuilder s = new StringBuilder();
        
        for (Map.Entry<Character, Integer> e: count.entrySet()) {
            if (e.getValue() > steady) {
                for (int i = 0; i < e.getValue() - steady; i++)
                    s.append(e.getKey());
            }
        }
        
        return s.toString();
    }
    
    public static boolean search(String text, String word) {
	    if (text.length() < word.length())
	        return false;
	   
	    Map<Character, Integer> countInWord = new HashMap<Character, Integer>();
	    
	    int hashWord = 0;
	    
	    for (char c: word.toCharArray()) {
	        if (!countInWord.containsKey(c)) {
	            countInWord.put(c, 0);
	        }
	        countInWord.put(c, countInWord.get(c) + 1);
	        hashWord += c;
	    }
	    
	    Map<Character, Integer> countInText = new HashMap<Character, Integer>();
	    
	    int hashText = 0;
	    
	    for (int i = 0; i < word.length(); i++) {
	        char c = text.charAt(i);
	        if (!countInText.containsKey(c)) {
	            countInText.put(c, 0);
	        }
	        countInText.put(c, countInText.get(c) + 1);
	        hashText += c;
	    }
	    
	    if (hashText == hashWord && isEquals(countInText, countInWord)) 
            return true;
	    
	    for (int i = 1; i <= text.length() - word.length(); i++) {
	        char previous = text.charAt(i - 1);
	        
	        char next = text.charAt(i + word.length() - 1);
	        
	        hashText += (next - previous);
	        
	        if (countInText.get(previous) == 1)
	            countInText.remove(previous);
	        else 
	           countInText.put(previous, countInText.get(previous) - 1);
	       
	        if (!countInText.containsKey(next)) 
	            countInText.put(next, 0);
	            
	        countInText.put(next, countInText.get(next) + 1);
	        
	        if (hashText == hashWord && isEquals(countInText, countInWord))
	            return true;
	    }
	    
	    return false;
	}
	
	private static boolean isEquals(Map<Character, Integer> countInText, Map<Character, Integer> countInWord) {
	    for (Map.Entry<Character, Integer> e: countInText.entrySet()) {
	        if (!countInWord.containsKey(e.getKey()))
	            return false;
	        int textValue = e.getValue();
	        int wordValue = countInWord.get(e.getKey());
	        if (textValue != wordValue) 
	            return false;
	    }
	    return true;
	}
}