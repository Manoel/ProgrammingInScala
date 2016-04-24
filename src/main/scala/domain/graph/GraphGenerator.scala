package domain.graph

import scala.util.Random
import scala.collection.mutable.ListBuffer

/**
 * Book: Discrete Mathematics and its applications
 * Edition: 7th
 * Chapter: 10
 * Questions: Computer Projects Question 8 and Question 9
 *
 * Question 8:
 * Given a positive integer n, generate a simple graph with n
 * vertices by producing an adjacency matrix for the graph
 * so that all simple graphs with n vertices are equally likely
 * to be generated.
 *
 * Question 9:
 * Given a positive integer n, generate a simple directed
 * graph with n vertices by producing an adjacency matrix
 * for the graph so that all simple directed graphs with n
 * vertices are equally likely to be generated.
 *
 * Solution:
 * Let n be the number of vertices.
 * The maximum number of edges is n * (n - 1) / 2, let m be this value.
 * So generate a number from 0 to m, let r be this value.
 * Get at random r edges and create a graph with its adjacency matrix
 * with these edges respecting when it should be directed or undirected.
 */
object GraphGenerator {

  def generate(n: Int, directed: Boolean) = {
    val m = n * (n - 1) / 2
    val r = Random.nextInt(m + 1)

    val edges = new ListBuffer[(Int, Int)]

    for (u <- 0 to n - 1)
      for (v <- u + 1 to n - 1)
        edges += ((u, v))

    val adjMatrix = Array.ofDim[Int](n, n)

    for (ne <- 1 to r) {
      val i = Random.nextInt(edges.length)
      val e = edges(i)

      if (directed) {
        val direction = Random.nextInt(2)

        if (direction == 0)
          adjMatrix(e._1)(e._2) = 1
        else
          adjMatrix(e._2)(e._1) = 1

      } else {
        adjMatrix(e._1)(e._2) = 1
        adjMatrix(e._2)(e._1) = 1
      }

      edges.remove(i)
    }

    new GraphWithAdjMatrix(adjMatrix)
  }

}