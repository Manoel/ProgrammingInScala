package domain.graph

object NumberOfPathsFinderApp {
  def main(args: Array[String]) {
    val adjMatrix = Array(
        Array(0, 1, 1, 1), 
        Array(1, 0, 1, 1), 
        Array(1, 1, 0, 1), 
        Array(1, 1, 1, 0))
        
    val graph = new GraphWithAdjMatrix(adjMatrix)
    
    println(NumberOfPathsFinder.find(graph, 2))
  }
}