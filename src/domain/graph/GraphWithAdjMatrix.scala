package domain.graph

import scala.collection.mutable.ListBuffer

class GraphWithAdjMatrix(val adjMatrix: Array[Array[Int]]) {
  assume(adjMatrix != null)

  def vertices = adjMatrix.length

  def isDirected: Boolean = {
    for (i <- 0 to adjMatrix.length - 1) {
      for (j <- 0 to adjMatrix.length - 1) {
        if (isDirectedEdge(i, j))
          return true

        if (isEdge(i, j))
          return false
      }
    }
    false
  }
  
  def adj(vertex: Int): Set[Int] = {
    val result = ListBuffer[Int]()
    
    for (v <- 0 until vertices)
      if (adjMatrix(vertex)(v) > 0)
        result += v
    
    result.toSet
  }

  def isEdge(u: Int, v: Int) = adjMatrix(u)(v) == 1 || adjMatrix(v)(u) == 1

  def isDirectedEdge(u: Int, v: Int) =
    (adjMatrix(u)(v) == 1 && adjMatrix(v)(u) == 0) ||
      (adjMatrix(u)(v) == 0 && adjMatrix(v)(u) == 1)

  def degree(vertex: Int) = {
    assume(vertex >= 0 && vertex < vertices)

    var degree = 0

    for (v <- adjMatrix(vertex))
      degree += v

    degree
  }
  
  def allEvenDegree: Boolean = {
    for (v <- 0 to vertices - 1)
      if (degree(v) % 2 != 0)
        return false
        
    return true
  }
  
  def twoOddDegree: (Boolean, (Int, Int)) = {
    var odd = 0
    
    val result = Array(-1, -1)
    
    for (v <- 0 to vertices - 1)
      if (degree(v) % 2 != 0) {
        odd += 1
        if (result(0) == -1)
          result(0) = v
        else
          result(1) = v
      }
        
    (odd == 2, (result(0), result(1)))
  }
  
  def hasEdge: Boolean = {
    for (u <- 0 to vertices - 1) {
      for (v <- 0 to vertices - 1) {
        if (adjMatrix(u)(v) >= 1)
          return true
      }
    }
    return false
  }

  override def toString(): String = adjMatrix.deep.mkString("\n")

}