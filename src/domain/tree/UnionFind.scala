package domain.tree

class UnionFind(val n: Int) {
  
  val parent = new Array[Int](n)
  
  initialize()
  
  private def initialize() {
    for (i <- 0 until n)
      parent(i) = i
  }
  
  def find(i: Int): Int = {
    if (parent(i) == i)
      i
    else
      find(parent(i))
  }
  
  def union(x: Int, y: Int) {
    val xset = find(x)
    val yset = find(y)
    parent(xset) = yset
  }
  
  override def toString = {
    (List() ++ parent).toString
  }
  
}