package domain.graph

import domain.tree.Edge
import scala.collection.mutable

object IncidentMatrixBuilder {

  def build(edges: List[(Edge, Int)]): GraphWithIncidentMatrix =  {
    var edgesCount = 0

    val vertices = mutable.Set[Int]()

    edges.foreach({ e =>
      {
        edgesCount += e._2
        vertices += e._1.u
        vertices += e._1.v
      }
    })

    val edgesMap = mutable.Map[Int, Edge]()

    var index = 0

    edges.foreach({ e =>
      {
        for (i <- index until index + e._2)
          edgesMap += (i -> e._1)
        index += e._2
      }
    })
    
    val incidentMatrix = initialize(vertices.size, edgesCount)
    
    update(incidentMatrix, edgesMap)

    new GraphWithIncidentMatrix(incidentMatrix, edgesMap.toMap)
  }
  
  private def update(incidentMatrix: Array[Array[Int]], edgesMap: mutable.Map[Int, Edge]) {
    val edges = incidentMatrix(0).length
    
    for (j <- 0 until edges) {
      val edge = edgesMap.get(j).get
      incidentMatrix(edge.u)(j) += 1
      incidentMatrix(edge.v)(j) += 1
    }
  }
  
  private def initialize(vertices: Int, edges: Int): Array[Array[Int]] = {
    val result = new Array[Array[Int]](vertices)
    
    for (i <- 0 until vertices)
      result(i) = new Array[Int](edges)
    
    result
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
        
    val graph = AdjacencyMatrixBuilder.buildUndirected(edges)
    
    val edgesListing = EdgesListing.list(graph)
    
    println(build(edgesListing))
  }

}