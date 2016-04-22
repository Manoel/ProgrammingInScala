package domain.graph

import scala.collection.mutable.Set

object TreeVerifier {

  def isTree(graph: GraphWithAdjMatrix) = {

    val visited = Set[Int]()

    var components = 0
    
    val foundCircuit = Array(false)

    for (v <- 0 to graph.vertices - 1) {
      if (!visited.contains(v)) {
        components += 1
        visited += v
        dfs(graph, v, visited, -1, foundCircuit)
      }
    }
    
    !foundCircuit(0) && components == 1
  }

  private def dfs(graph: GraphWithAdjMatrix, source: Int, visited: Set[Int], parent: Int, foundCircuit: Array[Boolean]): Unit = {

    for (v <- 0 to graph.vertices - 1) {
      if (!foundCircuit(0)) {

        if (graph.adjMatrix(source)(v) == 1) {

          if (!visited.contains(v)) {

            visited += v
            dfs(graph, v, visited, source, foundCircuit)

          } else if (v != parent) {
            foundCircuit(0) = true
          }

        }
      }
    }
  }

}