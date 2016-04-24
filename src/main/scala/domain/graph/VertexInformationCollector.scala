package domain.graph

import scala.collection.mutable.Set

object VertexInformationCollector {
  
  def collect(graph: GraphWithAdjMatrix, vertex: Int): VertexInformation = {
    val p = parent(graph, vertex)
    val levelAndAncestors = level(graph, vertex)
    val c = children(graph, vertex)
    val d = descendants(graph, vertex)
    
    new VertexInformation(
        p, 
        levelAndAncestors._1, 
        c.toSet, 
        levelAndAncestors._2.toSet,
        d.toSet)
  }
  
  private def descendants(graph: GraphWithAdjMatrix, vertex: Int) = {
    val result = Set[Int]()
    dfs(graph, vertex, Set(vertex), result)
    result
  }
  
  private def dfs(graph: GraphWithAdjMatrix, source: Int, visited: Set[Int], descendants: Set[Int]): Unit = {
    for (v <- 0 to graph.vertices - 1) {
      if (graph.adjMatrix(source)(v) == 1 && !visited.contains(v)) {
        visited += v
        descendants += v
        dfs(graph, v, visited, descendants)
      }
    }
  }
  
  private def parent(graph: GraphWithAdjMatrix, vertex: Int) = {
    var parent = -1
    
    for (v <- 0 to graph.vertices - 1) {
        if (graph.adjMatrix(v)(vertex) == 1) {
          parent = v          
        }
    }
    
    parent
    
  }
  
  private def children(graph: GraphWithAdjMatrix, vertex: Int) = {
    val result = Set[Int]()
    
    for (v <- 0 to graph.vertices - 1) {
        if (graph.adjMatrix(vertex)(v) == 1) {
          result.add(v)
        }
    }
    
    result
  }
  
  private def level(graph: GraphWithAdjMatrix, vertex: Int): (Int, Set[Int]) = {
    level(graph, vertex, Array(0), Set[Int]())
  }
  
  private def level(graph: GraphWithAdjMatrix, vertex: Int, l: Array[Int], ancestors: Set[Int]): (Int, Set[Int]) = {
    for (v <- 0 to graph.vertices - 1) {
        if (graph.adjMatrix(v)(vertex) == 1) {
          ancestors += v
          l(0) += 1
          level(graph, v, l, ancestors)
        }
    }
    (l(0), ancestors)
  }
  
  private def root(graph: GraphWithAdjMatrix) = {
    val range = 0 to graph.vertices - 1
    
    var vertices = range.toSet[Int]
    
    for (u <- 0 to graph.vertices - 1) {
      for (v <- 0 to graph.vertices - 1) {
        if (graph.adjMatrix(u)(v) == 1) {
          vertices = vertices - v
        }
      }      
    }
    
    (vertices.toList)(0)
  }
  
}