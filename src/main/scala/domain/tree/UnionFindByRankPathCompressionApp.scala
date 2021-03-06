package domain.tree

object UnionFindByRankPathCompressionApp {
  
  def main(args: Array[String]) {
    val uf = new UnionFindByRankPathCompression(5)
    
    println(uf)
    
    printAll(uf)  
    
    uf.union(0, 4)
    
    printAll(uf)
    
    uf.union(1, 3)
    
    printAll(uf)
    
    uf.union(2, 3)
    
    printAll(uf)
    
    uf.union(0, 2)
    
    printAll(uf)
  }
  
   private def printAll(uf: UnionFindByRankPathCompression) {
    println("All elements:")
    for (i <- 0 until uf.subsets.length)
      println(uf.find(i))    
  }
 
  
}