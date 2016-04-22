package domain.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

/**
 * Book: Discrete Mathematics and its Applications
 *
 * Edition: 7th
 *
 * Chapter: 10
 *
 * Question: Computer Projects Question 13
 *
 * Theorem: A connected multigraph with at least two vertices has an Euler circuit
 * if and only if each of its vertices has even degree.
 *
 * Theorem: A connected multigraph has an Euler path but not an Euler circuit
 * if and only if it has exactly two vertices of odd degree.
 *
 * Question: Given the vertex pairs associated to the edges of a multigraph,
 * determine whether it has an Euler circuit and, if not, whether it has an
 * Euler path. Construct an Euler path or circuit if it exists.
 */
object EulerCircuitPathConstructor {

  def circuitOrPath(graph: GraphWithAdjMatrix) = {
    assume(graph != null)

    var c: ListBuffer[Int] = null

    if (graph.allEvenDegree) {
      c = findCircuit(graph)
    } else {
      val tod = graph.twoOddDegree

      if (tod._1) {
        val g = new GraphWithAdjMatrix(copy(graph.adjMatrix))
        
        g.adjMatrix(tod._2._1)(tod._2._2) += 1
        g.adjMatrix(tod._2._2)(tod._2._1) += 1
        
        c = findCircuit(g)
        
        c = removeEdge(c, tod._2)        
        
      }
    }

    c
  }
  
  private def removeEdge(circuit: ListBuffer[Int], vertices: (Int, Int)) = {
    val u = vertices._1
    val v = vertices._2
    
    var i = 0
    
    while ((circuit(i) != u || circuit(i + 1) != v) 
        && (circuit(i) != v || circuit(i + 1) != u)) {
      
      i += 1
      
    }
    
    circuit.slice(i + 1, circuit.length - 1) ++ circuit.slice(0, i + 1)
  }

  private def findCircuit(graph: GraphWithAdjMatrix) = {
    var c = circuit(graph)

    var H = removeEdges(graph, c)

    while (H.hasEdge) {
      var i = 0
      var subcircuit: ListBuffer[Int] = null

      do {
        subcircuit = circuit(H, c(i))

        i += 1
      } while (i < c.length && subcircuit(0) != c(i - 1))

      H = removeEdges(H, subcircuit)

      insert(c, subcircuit)
    }
    
    c
  }

  def circuit(graph: GraphWithAdjMatrix): ListBuffer[Int] = {
    circuit(graph, 0)
  }

  def circuit(graph: GraphWithAdjMatrix, source: Int): ListBuffer[Int] = {
    val path = ListBuffer(0)

    dfs(graph, -1, source, Set(0), path, Array(false))

    val startAndEnd = path(path.length - 1)

    while (path(0) != startAndEnd)
      path.remove(0)

    path
  }

  private def dfs(
    graph: GraphWithAdjMatrix,
    parent: Int,
    source: Int,
    visited: Set[Int],
    path: ListBuffer[Int],
    found: Array[Boolean]): Unit = {

    for (v <- 0 to graph.vertices - 1) {
      if (!found(0)) {
        if (graph.adjMatrix(source)(v) >= 1 && !visited.contains(v)) {
          visited += v
          path += v
          dfs(graph, source, v, visited, path, found)
        } else if (graph.adjMatrix(source)(v) > 1 && visited.contains(v)) {
          path += v
          found(0) = true
        } else if (graph.adjMatrix(source)(v) == 1 && visited.contains(v) && v != parent) {
          path += v
          found(0) = true
        }
      }
    }
  }

  private def copy(matrix: Array[Array[Int]]) = {
    val result = new Array[Array[Int]](matrix.length)
    
    for (i <- 0 to matrix.length - 1){
      result(i) = new Array[Int](matrix(i).length)  
      Array.copy(matrix(i), 0, result(i), 0, matrix(i).length)
    }
    
    result
  }
  
  private def removeEdges(graph: GraphWithAdjMatrix, circuit: ListBuffer[Int]) = {
    val adjMatrix = copy(graph.adjMatrix)

    for (j <- 1 to circuit.length - 1) {
      val i = j - 1

      val u = circuit(i)
      val v = circuit(j)

      adjMatrix(u)(v) -= 1
      adjMatrix(v)(u) -= 1
    }

    new GraphWithAdjMatrix(adjMatrix)
  }

  private def insert(circuit: ListBuffer[Int], subcircuit: ListBuffer[Int]) = {
    var i = 0

    while (circuit(i) != subcircuit(0)) {
      i += 1
    }

    subcircuit.remove(subcircuit.length - 1)

    circuit.insertAll(i, subcircuit)
  }

}