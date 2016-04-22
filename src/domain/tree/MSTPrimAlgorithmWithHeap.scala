package domain.tree

import java.util.PriorityQueue
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object MSTPrimAlgorithmWithHeap {

  def mst(edges: List[WeightedEdge]): List[WeightedEdge] = {
    val edgesMap = mutable.Map[Int, ListBuffer[WeightedEdge]]()

    val vcMap = mutable.Map[Int, VertexContribution]()

    val queue = new PriorityQueue[VertexContribution]()

    fillMaps(edges, edgesMap, vcMap, queue)

    val vertices = mutable.Set[Int]()

    val result = ListBuffer[VertexContribution]()

    while (!queue.isEmpty()) {
      val vc = queue.poll()

      result += vc

      vertices += vc.vertex

      for (e <- edgesMap.get(vc.vertex).get) {
        val to = e.to(vc.vertex)

        if (!vertices.contains(to)) {
          val vcTo = vcMap.get(to).get

          if (e.weight < vcTo.weigth) {
            queue.remove(vcTo)
            vcTo.weigth = e.weight
            vcTo.from = vc.vertex
            queue.add(vcTo)
          }
        }
      }

    }

    val resultingEdges = ListBuffer[WeightedEdge]()

    for (i <- 1 until result.length) {
      resultingEdges += new WeightedEdge(result(i).weigth, result(i).from, result(i).vertex)
    }

    resultingEdges.toList
  }

  private def fillMaps(
    edges: List[WeightedEdge],
    edgesMap: mutable.Map[Int, ListBuffer[WeightedEdge]],
    vcMap: mutable.Map[Int, VertexContribution],
    queue: PriorityQueue[VertexContribution]) {
    for (e <- edges) {
      addEdge(e, edgesMap)
      addVC(e, vcMap)
    }
    vcMap.get(0).get.weigth = 0
    vcMap.values.foreach { vc => queue.add(vc) }
  }

  private def addVC(edge: WeightedEdge, vcMap: mutable.Map[Int, VertexContribution]) {
    addVC(edge.u, vcMap)
    addVC(edge.v, vcMap)
  }

  private def addVC(vertex: Int, vcMap: mutable.Map[Int, VertexContribution]) {
    if (!vcMap.contains(vertex)) {
      vcMap += (vertex -> new VertexContribution(vertex, Double.PositiveInfinity, -1))
    }
  }

  private def addEdge(edge: WeightedEdge, edgesMap: mutable.Map[Int, ListBuffer[WeightedEdge]]) {
    addEdge(edge.u, edge, edgesMap)
    addEdge(edge.v, edge, edgesMap)
  }

  private def addEdge(vertex: Int, edge: WeightedEdge, edgesMap: mutable.Map[Int, ListBuffer[WeightedEdge]]) {
    if (!edgesMap.contains(vertex)) {
      edgesMap += (vertex -> ListBuffer())
    }
    edgesMap.get(vertex).get += edge
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