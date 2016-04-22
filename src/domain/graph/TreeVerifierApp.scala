package domain.graph

object TreeVerifierApp {
  
  def main(args: Array[String]) = {
    
    val adjMatrix1 = Array(
      Array(0, 1, 1, 0, 0, 0),
      Array(1, 0, 1, 1, 0, 0),
      Array(1, 1, 0, 1, 1, 0),
      Array(0, 1, 1, 0, 1, 1),
      Array(0, 0, 1, 1, 0, 1),
      Array(0, 0, 0, 1, 1, 0)
    )
    
    val graph1 = new GraphWithAdjMatrix(adjMatrix1)
    
    println(TreeVerifier.isTree(graph1))
    
    val adjMatrix2 = Array(
      Array(0, 0, 1, 0, 0, 0),
      Array(0, 0, 1, 0, 0, 0),
      Array(1, 1, 0, 0, 1, 0),
      Array(0, 0, 0, 0, 1, 1),
      Array(0, 0, 1, 1, 0, 0),
      Array(0, 0, 0, 1, 0, 0)
    )
    
    val graph2 = new GraphWithAdjMatrix(adjMatrix2)
    
    println(TreeVerifier.isTree(graph2))
        
  }
  
}