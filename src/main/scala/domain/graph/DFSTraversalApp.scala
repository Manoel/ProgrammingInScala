package domain.graph

object DFSTraversalApp {
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
    
    new DFSTraversal(graph, 5).traversal()
  }
}