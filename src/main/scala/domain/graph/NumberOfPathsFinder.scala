package domain.graph

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set

/**
 * Book: Discrete Mathematics and Its Applications
 *
 * Edition: 7th
 *
 * Chapter: 10
 *
 * Computer Projects Question 11
 *
 * Given an adjacency matrix of a graph and a positive integer n,
 * find the number of paths of length n between two vertices.
 * (Produce a version that works for directed and undirected graphs.)
 *
 * Solution:
 *
 * Use Depth First Search (DFS) starting at each vertex and count each path
 * with length n. If the graph is undirected, divided the result by 2.
 * Assume that the graph is simple (no loop, no multi edge)
 */
object NumberOfPathsFinder {

  def find(graph: GraphWithAdjMatrix, length: Int): Int = {

    val paths = Array(0)

    for (v <- 0 to graph.vertices - 1)
      dfs(graph, v, Set(v), length, 0, paths)

    if (graph.isDirected)
      paths(0)
    else
      paths(0) / 2
  }

  private def dfs(
    graph: GraphWithAdjMatrix,
    vertex: Int,
    visited: Set[Int],
    length: Int,
    current: Int,
    paths: Array[Int]): Unit = {

    for (v <- 0 to graph.vertices - 1) {
      if (graph.adjMatrix(vertex)(v) == 1 && !visited.contains(v)) {

        val newLength = current + 1

        if (newLength == length) {
          paths(0) += 1
        } else {
        	visited += v
          dfs(graph, v, visited, length, newLength, paths)
        }
      }
    }

    visited.remove(vertex)

  }

}