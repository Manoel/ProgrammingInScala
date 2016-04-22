package domain.graph

import scala.collection.mutable.Set

class DFSTraversal(graph: Graph, source: Int) extends GraphTraversal(graph, source) {

  override def traversal() = {
    visit(source, Set(source))
  }

  private def visit(source: Int, visited: Set[Int]): Unit = {
    println(source)

    for (v <- graph.adjList(source))
      if (!visited.contains(v)) {
        visited += v
        visit(v, visited)
      }
  }

}