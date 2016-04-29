package domain.algorithms.hackerrank.bearsteady;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Integer n = Integer.parseInt(br.readLine().trim());
        String s = br.readLine().trim();
        
        br.close();
        
        int steady = n / 4;
        
        // A:0 , C:1, G:2, T: 3
        Map<Character, Integer> indexes = new HashMap<Character, Integer>();
        indexes.put('A', 0);
        indexes.put('C', 1);
        indexes.put('G', 2);
        indexes.put('T', 3);
        
        Map<Integer, Character> letters = new HashMap<Integer, Character>();
        letters.put(0, 'A');
        letters.put(1, 'C');
        letters.put(2, 'G');
        letters.put(3, 'T');
        
        int[] ocurrences = new int[4];
        
        for (char c: s.toCharArray()) {
            ocurrences[indexes.get(c)]++;
        }
        
        int index = getIndexMaxOcurrence(ocurrences);
        
        int result = 0;
        
        if (ocurrences[index] > steady) {
            int ocurrence = ocurrences[index];
            
            char c = letters.get(index);
            
            int shouldBe = ocurrence - steady;
            
            int place = findPlace(s, c, shouldBe);
            
            while (place == -1) {
                place = findPlace(s, c, --shouldBe);
            }
            
            int count = 0;
            
            while (count < shouldBe) {
                int minIndex = getIndexMinOcurrence(ocurrences);
                result++;
                count++;
                ocurrences[minIndex]++;
                ocurrences[index]--;
                place++;
            }
            
            index = getIndexMinOcurrence(ocurrences);
            
            while (ocurrences[index] < steady) {
                char nextC = s.charAt(place);    
                
                if (ocurrences[indexes.get(nextC)] > steady) {
                    int indexMin = getIndexMinOcurrence(ocurrences);
                    ocurrences[indexMin]++;
                    ocurrences[indexes.get(nextC)]--;
                }
                
                result++;
                place++;
                index = getIndexMinOcurrence(ocurrences);
            }
        }
        
        System.out.println(result);
        
    }
    
    private static int findPlace(String s, char c, int ocurrence) {
        char[] array = new char[ocurrence];
        for (int i = 0; i < ocurrence; i++)
            array[i] = c;
        String find = new String(array);
        return s.indexOf(find);
    }
    
    private static int getIndexMinOcurrence(int[] ocurrences) {
        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < 4; i++)  {
            if (ocurrences[i] < min) {
                min = ocurrences[i];
                result = i;
            }
        }
        return result;        
    }
    
    private static int getIndexMaxOcurrence(int[] ocurrences) {
        int max = 0;
        int result = 0;
        for (int i = 0; i < 4; i++)  {
            if (ocurrences[i] > max) {
                max = ocurrences[i];
                result = i;
            }
        }
        return result;        
    }
}
