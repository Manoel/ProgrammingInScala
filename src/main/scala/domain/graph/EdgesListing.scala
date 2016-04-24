package domain.graph

import scala.collection.mutable.ListBuffer
import scala.collection.mutable
import domain.tree.Edge

object EdgesListing {
  
  def list(graph: GraphWithAdjMatrix):List[(Edge, Int)] = {
    
    val size = graph.adjMatrix.length
    
    val result = ListBuffer[(Edge, Int)]()
    
    for (i <- 0 until size; j <- i until size) 
      if (graph.adjMatrix(i)(j) > 0)
        result += ((new Edge(i, j), graph.adjMatrix(i)(j)))
    
    
    result.toList
  }
  
  def list(graph: GraphWithIncidentMatrix): Map[Edge, Int] = {
    
    val edges = mutable.Map[Edge, Int]()
    
    for (e <- graph.edges.values)
      if (!edges.contains(e))
        edges += (e -> 1)
      else
        edges += (e -> (edges.get(e).get + 1))
      
    edges.toMap
  }
  
  def main(args: Array[String]) {
    val edges = List(
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
        
    val graphWithAdjMatrix = AdjacencyMatrixBuilder.buildUndirected(edges)
    
    val edgesListing = list(graphWithAdjMatrix)
    
    val graphWithIncidentMatrix = IncidentMatrixBuilder.build(edgesListing)
    
    println(list(graphWithIncidentMatrix))
  }
  
}