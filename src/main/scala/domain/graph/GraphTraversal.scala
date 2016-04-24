package domain.graph

abstract class GraphTraversal(val graph: Graph, val source: Int) {

  assume(graph != null)
  assume(source >= 0 && source < graph.adjList.length)

  def traversal(): Unit

}