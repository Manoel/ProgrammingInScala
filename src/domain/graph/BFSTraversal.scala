package domain.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

class BFSTraversal(graph: Graph, source: Int) extends GraphTraversal(graph, source) {
  
  override def traversal() = {
    val visited = Set(source)
    val queue = ListBuffer(source)
    
    while (!queue.isEmpty) {
      val u = queue.remove(0)
      println(u)
      for (v <- graph.adjList(u))
        if (!visited.contains(v)) {
          queue += v
          visited += v
        }
    }
  }
  
}