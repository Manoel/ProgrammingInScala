package domain.tree

import domain.graph.GraphWithAdjMatrix
import scala.collection.mutable.ListBuffer
import scala.collection.mutable

object SpanningTreeByDFS {
  
  def spanningTree(graph: GraphWithAdjMatrix): TreeNode = {
    require(graph != null)
    
    val nodes = mutable.Map(0 -> TreeNode(0))
    dfs(graph, 0, ListBuffer(0), nodes)
    nodes.get(0).get
  }
  
  private def dfs(graph: GraphWithAdjMatrix, vertex: Int, visited: ListBuffer[Int], nodes: mutable.Map[Int, TreeNode]) {
    for (v <- 0 until graph.vertices) {
      if (graph.adjMatrix(vertex)(v) == 1 && !visited.contains(v)) {
        visited += v
        
        nodes += (v -> new TreeNode(v))
        
        nodes.get(vertex).get.add(nodes.get(v).get)
        
        dfs(graph, v, visited, nodes)
      }
    }
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