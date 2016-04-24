package domain.graph

import domain.tree.Edge
import scala.collection.mutable

object VertexDegreeFinder {

  def findUndirected(edges: List[Edge]): Map[Int, Int] = {

    val result = mutable.Map[Int, Int]()

    edges.foreach { e =>
      {
        add(e.u, result)
        add(e.v, result)
      }
    }
    
    result.toMap

  }
  
  def findDirected(edges: List[Edge]): Map[Int, (Int, Int)] = {
    
    val result = mutable.Map[Int, (Int, Int)]()
    
    edges.foreach { e => add(e, result) }
    
    result.toMap
    
  }
  
  private def add(edge: Edge, degrees: mutable.Map[Int, (Int, Int)]) {
    add(edge.u, degrees, (0, 1), (a, b) => (a, b + 1))
    add(edge.v, degrees, (1, 0), (a, b) => (a + 1, b))
  }
  
  private def add(
      vertex: Int, 
      degrees: mutable.Map[Int, (Int, Int)], 
      default: (Int, Int), 
      updateFn: (Int, Int) => (Int, Int)) {
    
    if (!degrees.contains(vertex))
      degrees += (vertex -> default)
    else  {
      val degree = degrees.get(vertex).get
      degrees += (vertex -> updateFn (degree._1, degree._2))
    }
    
  }
  
  private def add(vertex: Int, degrees: mutable.Map[Int, Int]) {
    if (!degrees.contains(vertex))
      degrees += (vertex -> 1)
    else
      degrees += (vertex -> (degrees.get(vertex).get + 1))
  }
  
  def main(args: Array[String]) {
    val edges = List(
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
        
    println(findUndirected(edges))
    println(findDirected(edges))
  }

}