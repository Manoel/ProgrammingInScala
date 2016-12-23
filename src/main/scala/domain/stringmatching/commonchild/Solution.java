package domain.stringmatching.commonchild;

import java.io.*;
import java.util.*;

public class Solution {
	
	static Map<Integer, String> subsA;
	
	static Map<Integer, String> subsB;

    public static void main(String[] args) {
        
    	System.out.println(-3 % 7);
    }
    
    private static Map<Integer, String> createSubs(String s) {
    	Map<Integer, String> result = new HashMap<>();
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (int i = 0; i < s.length(); i++) {
    		sb.append(s.charAt(i));
    		result.put(i, sb.toString());
    	}
    		
    	return result;
    }
    
    private static void addIfMatch(List<ChildData> children, String s, int index, char c, boolean findInA) {
        int i;
        
        if (findInA) {
        	i = subsA.get(index).indexOf(c);
        } else {
        	i = subsB.get(index).indexOf(c);
        }
        
        if (i >= 0) {
            if (findInA)
                children.add(new ChildData(i, index));
            else {
                children.add(new ChildData(index, i));
            }
        }
    }
    
    private static boolean addIfMatch(List<ChildData> children, ChildData cd, String s, int i, char c, boolean findInA) {
        int index;
         
        if (findInA) {
        	index = subsA.get(i).indexOf(c);
        } else {
        	index = subsB.get(i).indexOf(c);
        }
        
        boolean remove = false;
        
        if (index >= 0) {
            int iS;
            if (findInA) {
                iS = cd.indexesMatchA.get(cd.indexesMatchA.size() - 1);    
            } else {
                iS = cd.indexesMatchB.get(cd.indexesMatchB.size() - 1);
            }
            
            ChildData newCd;
            
            if (index > iS) {
                newCd = new ChildData(cd.indexesMatchA, cd.indexesMatchB);
                
                if (findInA) {
                    newCd.indexesMatchA.add(index);
                    newCd.indexesMatchB.add(i);
                } else {
                    newCd.indexesMatchA.add(i);
                    newCd.indexesMatchB.add(index);
                }
                
                remove = true;

            } else {
                if (findInA) {
                    newCd = new ChildData(index, i);    
                } else {
                    newCd = new ChildData(i, index);    
                }
            }
            
            children.add(newCd);
            
        }
        return remove;
    }
    
    private static int longestCommonChild(String a, String b) {
        int n = a.length();
        
        if (a.equals(b)) {
            return n;
        }
        
        subsA = createSubs(a);
        
        subsB = createSubs(b);
        
        List<ChildData> children = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            
            char ca = a.charAt(i);
            
            char cb = b.charAt(i);
            
            List<ChildData> cds = new ArrayList<>();
            
            if (ca == cb) {
                if (children.size() > 0) {
                    for (ChildData cd : children) {
                        ChildData newCd = new ChildData(cd.indexesMatchA, cd.indexesMatchB);
                        newCd.indexesMatchA.add(i);
                        newCd.indexesMatchB.add(i);
                        cds.add(newCd);
                    }    
                } else {
                    cds.add(new ChildData(i));
                }
                children = cds;   
            } else {
                if (children.size() == 0) {
                    addIfMatch(children, b, i, ca, false);
                    addIfMatch(children, a, i, cb, true);
                } else {
                    List<ChildData> toRemove = new ArrayList<>();
                    for (ChildData cd : children) {
                        boolean remove1 = addIfMatch(cds, cd, b, i, ca, false);
                        boolean remove2 = addIfMatch(cds, cd, a, i, cb, true);
                        
                        if (remove1 || remove2) {
                            toRemove.add(cd);        
                        }
                    } 
                    for (ChildData cd: toRemove) {
                        children.remove(cd);
                    }
                    for (ChildData cd: cds) {
                        children.add(cd);
                    }
                }
            }
            
        }
        
        int max = 0;
        
        for (ChildData cd : children) {
            if (cd.indexesMatchA.size() > max) {
                max = cd.indexesMatchA.size();
            }
        }
        
        return max;
    }
}

class ChildData {
 
    List<Integer> indexesMatchA;
    List<Integer> indexesMatchB;
    
    ChildData(List<Integer> indexesMatchA, List<Integer> indexesMatchB) {
        this.indexesMatchA = indexesMatchA;
        this.indexesMatchB = indexesMatchB;
    }
    
    ChildData(int i) {        
        indexesMatchA = new ArrayList<>();
        indexesMatchA.add(i);        
        indexesMatchB = new ArrayList<>();
        indexesMatchB.add(i);
    }
    
    ChildData(int indexA, int indexB) {
        indexesMatchA = new ArrayList<>();
        indexesMatchA.add(indexA);        
        indexesMatchB = new ArrayList<>();
        indexesMatchB.add(indexB);
    }
    
    @Override
    public String toString() {
        return "CD(" + indexesMatchA + ", " + indexesMatchB + ")";
    }
}
