package domain.graph

object DijkistraShortestPathApp {
  
  def main(args: Array[String]) = {
    val adjMatrix = Array(
      Array(0, 4, 2, 0, 0, 0),
      Array(4, 0, 1, 5, 0, 0),
      Array(2, 1, 0, 8, 10, 0),
      Array(0, 5, 8, 0, 2, 6),
      Array(0, 0, 10, 2, 0, 3),
      Array(0, 0, 0, 6, 3, 0)
    )
    
    val graph = new GraphWithAdjMatrix(adjMatrix)
    
    println(DijkistraShortestPath.shortestPath(graph, 0, 5))
  }
  
}