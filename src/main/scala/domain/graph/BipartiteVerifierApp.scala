package domain.graph

object BipartiteVerifierApp {
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
    
    println(new BipartiteVerifier(graph).isBipartite())
    
    val graph2 = new Graph(6)
    graph2.addEdge(0, 1)
    graph2.addEdge(0, 3)
    graph2.addEdge(0, 5)
    graph2.addEdge(2, 1)
    graph2.addEdge(2, 3)
    graph2.addEdge(2, 5)
    graph2.addEdge(4, 1)
    graph2.addEdge(4, 3)
    graph2.addEdge(4, 5)
    
    println(new BipartiteVerifier(graph2).isBipartite())
    
    val graph3 = new Graph(6)
    graph3.addEdge(0, 1)
    graph3.addEdge(0, 3)
    graph3.addEdge(0, 5)
    graph3.addEdge(2, 1)
    graph3.addEdge(2, 3)
    graph3.addEdge(2, 5)
    graph3.addEdge(4, 1)
    graph3.addEdge(4, 3)
    graph3.addEdge(4, 5)
    graph3.addEdge(3, 5) // this edge makes the graph not bipartite
    
    println(new BipartiteVerifier(graph3).isBipartite())
  }
}