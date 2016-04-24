package domain.tree

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object MSTPrimAlgorithm {
  
  def mst(edges: List[WeightedEdge]): Set[WeightedEdge] = {
    require(edges != null)
    require(!edges.isEmpty)
    
    val edgesSorted = ListBuffer() ++ edges.sorted
    
    val cheapestEdge = edgesSorted.remove(0)
    
    val tree = mutable.Set(cheapestEdge)
    val vertices = mutable.Set(cheapestEdge.u, cheapestEdge.v)
    var canAddEdge = true
    
    while (canAddEdge) {
      
      val edgeOption = edgesSorted.find { e => 
        ((vertices.contains(e.u) && !vertices.contains(e.v))
        || (!vertices.contains(e.u) && vertices.contains(e.v)))}
      
      if (edgeOption.isDefined) {
    	  val edge = edgeOption.get
        tree += edge
        vertices += edge.u
        vertices += edge.v
        edgesSorted -= edge
      } else 
        canAddEdge = false
    }
    
    tree.toSet
  }
  
  def main(args: Array[String]) {
    val edges = List(
        new WeightedEdge(1200, 0, 1), 
        new WeightedEdge(2000, 0, 2), 
        new WeightedEdge(900, 0, 3), 
        new WeightedEdge(2200, 0, 4),
        new WeightedEdge(1000, 1, 2),
        new WeightedEdge(1300, 1, 3),
        new WeightedEdge(700, 1, 4),
        new WeightedEdge(1600, 2, 3),
        new WeightedEdge(800, 2, 4),
        new WeightedEdge(1400, 3, 4))
        
    println(mst(edges))
  }
  
}