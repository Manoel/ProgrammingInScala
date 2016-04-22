package domain.graph

import scala.collection.mutable.ListBuffer

object GraphColoring {
  
  case class Vertex(val u: Int, val degree: Int, val adj: Set[Int], var color: Int)
  
  def coloring(graph: GraphWithAdjMatrix): List[Vertex] = {
  
    var vertices = ListBuffer[Vertex]()
    
    for (v <- 0 until graph.vertices)
      vertices += new Vertex(v, graph.degree(v), graph.adj(v), -1)
    
    vertices = vertices.sortWith((u, v) => u.degree > v.degree)
    
    val result = ListBuffer[Vertex]()
    
    val adj = ListBuffer[Int]()
    
    var color = 0
    
    while (!vertices.isEmpty) {
      
      var i = 0
      
      while (i < vertices.length) {
    	  var sameColor = true
        for (u <- adj) {
          if (vertices(i).adj.contains(u)) {
            sameColor = false
          }
        }
    	  if (sameColor) {
    	    adj += vertices(i).u
    	    vertices(i).color = color
    	    result += vertices(i)
    	    vertices.remove(i)
    	  } else {
    	    i += 1
    	  }
      }
      adj.clear()
      color += 1
    }
    
    result.toList
  }
  
  def main(args: Array[String]) {
    
    val adjMatrix = Array(
      Array(0, 0, 1, 0, 0, 0),
      Array(0, 0, 1, 0, 0, 0),
      Array(1, 1, 0, 0, 1, 0),
      Array(0, 0, 0, 0, 1, 1),
      Array(0, 0, 1, 1, 0, 0),
      Array(0, 0, 0, 1, 0, 0))

    val graph = new GraphWithAdjMatrix(adjMatrix)
    
    println(coloring(graph))
    
  }
  
}