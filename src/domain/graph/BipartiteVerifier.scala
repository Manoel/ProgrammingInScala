package domain.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

class BipartiteVerifier(val graph: Graph) {

  assume(graph != null)

  def isBipartite(): Boolean = {
    val source = 0;
    val set1 = Set(source)
    val set2 = Set[Int]()
    val visited = Set(source)
    val queue = ListBuffer(source)

    while (!queue.isEmpty) {
      val u = queue.remove(0)

      for (v <- graph.adjList(u))
        if (!visited.contains(v)) {
          queue += v
          visited += v

          if (set1.contains(u))
            set2 += v
          else
            set1 += v

        } else {
          if ((set1.contains(u) && set1.contains(v)) || 
              (set2.contains(u) && set2.contains(v))) {
            return false
          }
        }
    }

    return true
  }

}