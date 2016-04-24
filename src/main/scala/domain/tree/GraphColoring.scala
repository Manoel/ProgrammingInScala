package domain.tree

import domain.graph.GraphWithAdjMatrix
import scala.collection.mutable.ListBuffer

object GraphColoring {

  val numColors = 3

  var coloring: Map[Int, Int] = null

  class ColoringNode(
    val vertex: Int,
    val coloring: Map[Int, Int])

  def coloring(graph: GraphWithAdjMatrix): Map[Int, Int] = {
    for (v <- 0 until graph.vertices) {
      if (coloring == null)
        doColoring(graph, new ColoringNode(v, Map(v -> 0)))
    }
    coloring
  }

  private def doColoring(
    graph: GraphWithAdjMatrix,
    node: ColoringNode) {

    for (v <- 0 until graph.vertices) {
      if (graph.adjMatrix(node.vertex)(v) == 1 && !node.coloring.contains(v)) {

        val colorsUsed = ListBuffer[Int]()
        for (u <- 0 until graph.vertices) {
          if (graph.adjMatrix(v)(u) == 1 && node.coloring.contains(u)) {
            colorsUsed += node.coloring.get(u).get
          }
        }

        for (c <- 0 until numColors) {
          if (coloring == null && !colorsUsed.contains(c)) {
            val nextNode = new ColoringNode(v, node.coloring + (v -> c))
            if (nextNode.coloring.size == graph.vertices)
              coloring = nextNode.coloring
            else {
              doColoring(graph, nextNode)
            }

          }
        }

      }
    }

  }

  def main(args: Array[String]) {
    val graph = new GraphWithAdjMatrix(
      Array(
        Array(0, 1, 0, 0, 1),
        Array(1, 0, 1, 1, 1),
        Array(0, 1, 0, 1, 0),
        Array(0, 1, 1, 0, 1),
        Array(1, 1, 0, 1, 0)))
    
    println(coloring(graph))
  }

}