package domain.graph

import scala.collection.mutable.Set

/**
 * Book: Discrete Mathematics and its Applications
 *
 * Edition: 7th
 *
 * Chapter 10
 *
 * Question: Computer Projects Question 12
 *
 * Given the list of edges of a simple graph, determine
 * whether it is connected and find the number of connected
 * components if it is not connected.
 * 
 * Solution:
 * 
 * Iterate through the vertices of the graph and apply DFS at each
 * one as source vertex only when it was not visited by a previous
 * DFS call, if this is case, this means that this vertex and all other
 * vertices reachable from it are at the same connected component.
 */
object ConnectedComponentsCounter {

  def connectedComponets(graph: GraphWithAdjMatrix) : Int = {
    val visited = Set[Int]()
    
    val result = Array(0)
    
    for (v <- 0 to graph.vertices - 1) {
      if (!visited.contains(v)) {
        visited += v
        result(0) += 1
        dfs(graph, visited, v)
      }
    }
    
    result(0)
  }
  
  private def dfs(graph: GraphWithAdjMatrix, visited: Set[Int], vertex: Int): Unit = {
    for (v <- 0 to graph.vertices - 1) {
      if (graph.adjMatrix(vertex)(v) == 1 && !visited.contains(v)) {
        visited += v
        dfs(graph, visited, v)
      }
    }
  }
  
}