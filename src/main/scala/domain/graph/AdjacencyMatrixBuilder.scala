package domain.graph

import domain.tree.Edge
import scala.collection.mutable

object AdjacencyMatrixBuilder {
  
  def buildUndirected(edges: List[Edge]): GraphWithAdjMatrix = {
    build(edges, updateUndirected)   
  }
  
  def buildDirected(edges: List[Edge]): GraphWithAdjMatrix = {
		build(edges, updateDirected)
  }
  
  private def build(edges: List[Edge], updateFn: (Array[Array[Int]], Edge) => Unit): GraphWithAdjMatrix = {
    
    val vertices = mutable.Set[Int]()
    
    edges.foreach { edge => 
      {
        vertices += edge.u
        vertices += edge.v
      }
    }
    
    val result = initializeMatrix(vertices.size)
    
    for (edge <- edges)    
      updateFn(result, edge)
    
    new GraphWithAdjMatrix(result)
  }
  
  private def updateUndirected(matrix: Array[Array[Int]], edge: Edge) {
    matrix(edge.u)(edge.v) += 1
      
    if (edge.u != edge.v)
      matrix(edge.v)(edge.u) += 1
  }
  
  private def updateDirected(matrix: Array[Array[Int]], edge: Edge) {
    matrix(edge.u)(edge.v) += 1
  }      
  
  private def initializeMatrix(size: Int): Array[Array[Int]] = {
    val result = new Array[Array[Int]](size)
    
    for (i <- 0 until size) 
      result(i) = new Array[Int](size)
    
    result
  }
  
  def main(args: Array[String]) {
    val edges = List(
        new Edge(0, 0),
        new Edge(0, 1),
        new Edge(0, 1), 
        new Edge(0, 2), 
        new Edge(0, 3), 
        new Edge(0, 4),
        new Edge(1, 2),
        new Edge(1, 3),
        new Edge(1, 4),
        new Edge(2, 3),
        new Edge(2, 4),
        new Edge(3, 4))
        
    println("Undirected adjacency matrix:")
    println(buildUndirected(edges))
    
    println("Directed adjacency matrix:")
    println(buildDirected(edges))
  }
  
  
}