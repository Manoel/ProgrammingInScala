package domain.tree

import domain.graph.GraphWithAdjMatrix
import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

object SpanningTreeByBFS {

  def spanningTree(graph: GraphWithAdjMatrix): TreeNode = {
    require(graph != null)

    val queue = new Queue[Int]()
    queue += 0

    val visited = new ListBuffer[Int]()
    visited += 0

    val nodes = Map(0 -> new TreeNode(0))

    while (!queue.isEmpty) {
      val n = queue.dequeue()

      for (v <- 0 until graph.vertices) {
        if (graph.adjMatrix(n)(v) == 1 && !visited.contains(v)) {
          visited += v
          queue += v

          nodes += (v -> new TreeNode(v))

          nodes.get(n).get.add(nodes.get(v).get)
        }
      }

    }

    nodes.get(0).get
  }
  
  def main(args: Array[String]) {
    val graph = new GraphWithAdjMatrix(
      Array(
          Array(0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
          Array(1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0),
          Array(0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1),
          Array(0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1),
          Array(0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0)
          )
    )
    
    val root = spanningTree(graph)
    
    println(root)
  }

}