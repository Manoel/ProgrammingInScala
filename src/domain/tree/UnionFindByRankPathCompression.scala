package domain.tree

class UnionFindByRankPathCompression(val n: Int) {
  
  case class Element(var parent: Int, var rank: Int)
  
  val subsets = new Array[Element](n)
  
  initialize()
  
  private def initialize() {
    for (i <- 0 until subsets.length)
      subsets(i) = new Element(i, 0)
  }
  
  def find(i: Int): Int = {
    if (subsets(i).parent != i)
      subsets(i).parent = find(subsets(i).parent)      
     
    subsets(i).parent      
  }
  
  def union(x: Int, y: Int) {
    val xroot = find(x)
    val yroot = find(y)
    
    if (subsets(xroot).rank < subsets(yroot).rank)
      subsets(xroot).parent = yroot
    else if (subsets(xroot).rank > subsets(yroot).rank)
      subsets(yroot).parent = xroot
    else {
      subsets(yroot).parent = xroot
      subsets(xroot).rank += 1
    }
    
  }
  
   override def toString = {
      (List() ++ subsets).toString
    }
  
}