package domain.tree

object PostorderTraversal {

  def traverse(tree: TreeNode) {
    if (tree != null) {
      for (t <- tree.getChildren)
        traverse(t)

      println(tree.key)
    }
  }
  
  def main(args: Array[String]) {
    val edges = List(Edge(3, 1), Edge(3, 5), Edge(1, 2), Edge(5, 4), Edge(5, 6))
    
    val root = VertexCollector.createTree(edges)
    
    traverse(root)
  }

}