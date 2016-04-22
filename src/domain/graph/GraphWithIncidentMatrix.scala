package domain.graph

import domain.tree.Edge

class GraphWithIncidentMatrix(val matrix: Array[Array[Int]], val edges: Map[Int, Edge]) {
  
  override def toString = edges.toString() + "\n" + matrix.deep.mkString("\n") 
  
}