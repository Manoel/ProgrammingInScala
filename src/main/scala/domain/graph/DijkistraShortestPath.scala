package domain.graph

import java.util.PriorityQueue
import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

object Ord extends Ordering[(Int, Int)] {

  override def compare(x: (Int, Int), y: (Int, Int)): Int = {
    val r = x._1.compare(y._1)

    if (r == 0)
      x._2.compare(y._2)
    else
      r
  }

}

object DijkistraShortestPath {

  def shortestPath(graph: GraphWithAdjMatrix, from: Int, to: Int) = {

    val L = new Array[Int](graph.vertices)
    
    val P = new Array[ListBuffer[Int]](graph.vertices)
    
    val pq = new PriorityQueue[(Int, Int)](Ord)

    for (v <- 0 to graph.vertices - 1) {
      if (v == from) {
        pq.add((0, v))
        L(v) = 0
      } else {
        pq.add((Int.MaxValue, v))
        L(v) = Int.MaxValue
      }
      
      P(v) = new ListBuffer
    }

    val S = Set[Int]()

    while (!S.contains(to)) {
      val u = pq.poll()

      S += u._2

      for (v <- 0 to graph.vertices - 1) {
        if (graph.adjMatrix(u._2)(v) >= 1 && !S.contains(v) && L(u._2) + graph.adjMatrix(u._2)(v) < L(v)) {
          val newL = L(u._2) + graph.adjMatrix(u._2)(v)

          pq.remove((L(v), v))

          L(v) = newL

          pq.add((newL, v))
          
          P(v).clear()
          
          P(v) ++= P(u._2)
          
          P(v) += u._2

        }
      }
    }
    
    P(to) += to

    (L(to), P(to))

  }

}