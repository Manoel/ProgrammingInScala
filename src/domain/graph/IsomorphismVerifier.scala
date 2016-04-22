package domain.graph

import domain.tree.Edge
import scala.collection.mutable
import FunctionsGenerator.FnGeneratorNode

/**
 * The problem of determining whether any two graphs are isomorphic is of special
 * interest because it is one of only a few NP problems not known to be either tractable
 * or NP-complete.
 */
object IsomorphismVerifier {

  def areIsomorphic(G: List[Edge], H: List[Edge]): Boolean = {
    if (G.size != H.size)
      false
    else {
      val G_vertices = vertices(G)
      val H_vertices = vertices(H)

      if (G_vertices.size != H_vertices.size)
        false
      else {
        val G_degrees = degrees(G)
        val H_degrees = degrees(H)

        if (!compatibleDegrees(G_degrees, H_degrees))
          false
        else {
          val functions = generateFunctions(G_degrees, H_degrees)

          val fnCombinations = generateCombinations(functions)

          val G_matrix = fillMatrix(G, G_vertices.size)
          val H_matrix = fillMatrix(H, H_vertices.size)

          var result = false

          val fnIterator = fnCombinations.iterator

          while (!result && fnIterator.hasNext) {
            val fnComb = fnIterator.next()

            val newMatrix = Array.ofDim[Int](H_vertices.size, H_vertices.size)

            update(newMatrix, H_matrix, fnComb)

            result = equalsMatrix(G_matrix, newMatrix)
          }

          result
        }
      }
    }
  }

  private def equalsMatrix(matrix1: Array[Array[Int]], matrix2: Array[Array[Int]]): Boolean = {

    for (i <- 0 until matrix1.length) {
      for (j <- 0 until matrix1.length) {
        if (matrix1(i)(j) != matrix2(i)(j))
          return false
      }
    }

    return true
  }

  private def update(newMatrix: Array[Array[Int]], matrix: Array[Array[Int]], functions: List[Map[Int, Int]]) {
    for (i <- 0 until newMatrix.length) {
      for (j <- 0 until newMatrix.length) {

        var fnWith_i = functions.find(fn => fn.contains(i)).get
        var fnWith_j = functions.find(fn => fn.contains(j)).get

        newMatrix(i)(j) = matrix(fnWith_i(i))(fnWith_j(j))
      }
    }
  }

  private def fillMatrix(edges: List[Edge], dim: Int): Array[Array[Int]] = {
    val matrix = Array.ofDim[Int](dim, dim)

    for (e <- edges) {
      matrix(e.u)(e.v) = 1
      matrix(e.v)(e.u) = 1
    }

    matrix
  }

  private def generateCombinations(functions: Map[Int, List[Map[Int, Int]]]): List[List[Map[Int, Int]]] = {
    val result = mutable.ListBuffer[mutable.ListBuffer[Map[Int, Int]]]()

    for (degree <- functions.keys) {
      val degreeFns = functions.get(degree).get

      if (result.isEmpty) {
        for (fn <- degreeFns)
          result += mutable.ListBuffer[Map[Int, Int]](fn)
      } else {
        for (fn <- degreeFns) {
          for (i <- 0 until result.length)
            result(i) += fn
        }
      }
    }

    result.toList.map(l => l.toList)
  }

  private def generateFunctions(
    G_degrees: Map[Int, List[Int]],
    H_degrees: Map[Int, List[Int]]): Map[Int, List[Map[Int, Int]]] = {

    val result = mutable.Map[Int, List[Map[Int, Int]]]()

    for (degree <- G_degrees.keys) {
      val functions = generateFunctions(G_degrees.get(degree).get, H_degrees.get(degree).get)
      result += (degree -> functions)
    }

    result.toMap
  }

  private def generateFunctions(G_vertices: List[Int], H_vertices: List[Int]): List[Map[Int, Int]] = 
     FunctionsGenerator.generateFunctions(FnGeneratorNode(G_vertices, H_vertices))
  
  private def compatibleDegrees(G_degrees: Map[Int, List[Int]], H_degrees: Map[Int, List[Int]]): Boolean =
    (G_degrees.size == H_degrees.size
      && G_degrees.forall(entry => H_degrees.contains(entry._1)
        && H_degrees.get(entry._1).get.length == entry._2.length))

  private def degrees(edges: List[Edge]): Map[Int, List[Int]] = {
    val result = mutable.Map[Int, mutable.ListBuffer[Int]]()

    val degrees = VertexDegreeFinder.findUndirected(edges)

    for (v <- degrees.keys) {
      val degree = degrees.get(v).get

      if (!result.contains(degree))
        result += (degree -> mutable.ListBuffer())

      result.get(degree).get += v
    }

    result.mapValues { vertices => vertices.toList }.toMap
  }

  private def vertices(edges: List[Edge]): Set[Int] = {
    val result = mutable.Set[Int]()

    edges.foreach { edge =>
      {
        result += edge.u
        result += edge.v
      }
    }

    result.toSet
  }

  def main(args: Array[String]) {
    var G_edges = List(Edge(0, 1), Edge(1, 2), Edge(2, 3), Edge(3, 0))
    var H_edges = List(Edge(3, 0), Edge(0, 2), Edge(1, 2), Edge(1, 3))

    println(areIsomorphic(G_edges, H_edges))
    
    G_edges = List(Edge(0, 1), Edge(0, 4), Edge(1, 2), Edge(2, 3), Edge(2, 4), Edge(3, 4))
    H_edges = List(Edge(0, 1), Edge(0, 2), Edge(0, 3), Edge(0, 4), Edge(1, 2), Edge(2, 3))
    
    println(areIsomorphic(G_edges, H_edges))
    
    G_edges = List(Edge(0, 1), Edge(0, 3), Edge(1, 2), Edge(1, 5), Edge(2, 3), Edge(3, 4), Edge(4, 5))
    H_edges = List(Edge(0, 1), Edge(0, 4), Edge(1, 2), Edge(2, 3), Edge(2, 5), Edge(3, 4), Edge(4, 5))
    
    println(areIsomorphic(G_edges, H_edges))
  }

}