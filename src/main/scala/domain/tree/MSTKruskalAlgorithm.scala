package domain.tree

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object MSTKruskalAlgorithm {

  def mst(edges: List[WeightedEdge]): List[WeightedEdge] = {
    val edgesSorted = ListBuffer() ++ edges.sorted

    val vertices = mutable.Set[Int]()

    edgesSorted.foreach { e =>
      {
        vertices += e.u
        vertices += e.v
      }
    }

    val uf = new UnionFindByRankPathCompression(vertices.size)
    
    val result = ListBuffer[WeightedEdge]()
    
    while (result.length < vertices.size - 1) {
      val e = edgesSorted.remove(0)
      
      val uroot = uf.find(e.u)
      val vroot = uf.find(e.v)
      
      if (uroot != vroot) {
        result += e
        uf.union(uroot, vroot)
      }
      
    }

    result.toList
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