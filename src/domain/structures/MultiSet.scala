package domain.structures

import scala.collection.mutable

/**
 * Sometimes the number of times that an element occurs in an
 * unordered collection matters. Multisets are unordered collec-
 * tions of elements where an element can occur as a member
 * more than once. The notation {m 1 · a 1 , m 2 · a 2 , . . . , m r · a r }
 * denotes the multiset with element a 1 occurring m 1 times, el-
 * ement a 2 occurring m 2 times, and so on. The numbers m i ,
 * i = 1, 2, . . . , r are called the multiplicities of the elements
 * a i , i = 1, 2, . . . , r.
 * Let P and Q be multisets. The union of the multisets P
 * and Q is the multiset where the multiplicity of an element is
 * the maximum of its multiplicities in P and Q. The intersec-
 * tion of P and Q is the multiset where the multiplicity of an
 * element is the minimum of its multiplicities in P and Q. The
 * difference of P and Q is the multiset where the multiplicity
 * of an element is the multiplicity of the element in P less its
 * multiplicity in Q unless this difference is negative, in which
 * case the multiplicity is 0. The sum of P and Q is the multiset
 * where the multiplicity of an element is the sum of multiplic-
 * ities in P and Q. The union, intersection, and difference of
 * P and Q are denoted by P ∪ Q, P ∩ Q, and P − Q, respec-
 * tively (where these operations should not be confused with
 * the analogous operations for sets). The sum of P and Q is
 * denoted by P + Q.
 */
case class MultiSet(val elements: Map[Int, Int]) {

  def add(element: Int): MultiSet = {
		val elems = mutable.Map[Int, Int]() ++ elements
    
		elems += (element -> (elems.getOrElse(element, 0) + 1))
		
    MultiSet(elems.toMap)
  }
  
  def union(that: MultiSet): MultiSet = {
    val elemsOnlyInThat = that.elements.filter(e => !elements.contains(e._1)) 
    
    val elemsOnlyInMe = elements.filter(e => !that.elements.contains(e._1))
    
    val elemsInBoth = that.elements.filter(e => elements.contains(e._1)).
      map(both => (both._1, scala.math.max(both._2, elements(both._1))))
    
    val elems = elemsOnlyInThat ++ elemsOnlyInMe ++ elemsInBoth
            
    MultiSet(elems)
  }
  
  def sum(that: MultiSet): MultiSet = {
    val elemsOnlyInThat = that.elements.filter(e => !elements.contains(e._1)) 
    
    val elemsOnlyInMe = elements.filter(e => !that.elements.contains(e._1))
    
    val elemsInBoth = that.elements.filter(e => elements.contains(e._1)).
      map(both => (both._1, both._2 + elements(both._1)))
    
    val elems = elemsOnlyInThat ++ elemsOnlyInMe ++ elemsInBoth
            
    MultiSet(elems)
  }
  
  def difference(that: MultiSet): MultiSet = {
    val elemsOnlyInMe = elements.filter(e => !that.elements.contains(e._1))
    
    val elemsInBoth = elements.filter(e => that.elements.contains(e._1)).
      map(both => (both._1, both._2 - that.elements(both._1))).filter(diff => diff._2 > 0)
    
    val elems = elemsOnlyInMe ++ elemsInBoth
            
    MultiSet(elems)
  }
  
  def intersection(that: MultiSet): MultiSet = {
    val elemsInBoth = that.elements.filter(e => elements.contains(e._1)).
      map(both => (both._1, scala.math.min(both._2, elements(both._1))))
            
    MultiSet(elemsInBoth)
  }
  
}

object MultiSet {
  
  def main(args: Array[String]) {
    val multiSet1 = MultiSet(Map(0-> 10, 1 -> 2, 2 -> 3, 3 -> 4))
    
    val multiSet2 = MultiSet(Map(1 -> 2, 2 -> 3, 3 -> 4, 4 -> 10))
    
    println(multiSet1.union(multiSet2))
    
    println(multiSet1.intersection(multiSet2))
    
    println(multiSet1.sum(multiSet2))
    
    println(multiSet1.difference(multiSet2))
  }
  
}