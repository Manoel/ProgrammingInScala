package domain.graph

object BFSTraversalApp {
  def main(args: Array[String]) {
    val graph = new Graph(11)

    graph.addEdge(0, 2)
    graph.addEdge(1, 2)
    graph.addEdge(2, 4)
    graph.addEdge(3, 4)
    graph.addEdge(3, 5)
    graph.addEdge(4, 5)
    graph.addEdge(5, 6)
    graph.addEdge(5, 7)
    graph.addEdge(6, 7)
    graph.addEdge(7, 8)
    graph.addEdge(7, 10)
    graph.addEdge(9, 10)
    
    println("Degree of vertices")
    for (v <- 0 to graph.adjList.length - 1)
      println("Vertex " + v + ": " + graph.degree(v))

    println("BFS traversal with source " + 5)  
    new BFSTraversal(graph, 5).traversal()
  }
}