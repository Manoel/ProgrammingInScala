package domain.tree

object InorderTraversal {

  def traverse(tree: TreeNode) {
    if (tree != null) {
      if (!tree.getChildren.isEmpty)
        traverse(tree.getChildren(0))

      println(tree.key)
      
      for (i <- 1 until tree.getChildren.length)
        traverse(tree.getChildren(i))
    }
  }
  
  def main(args: Array[String]) {
    val edges = List(Edge(3, 1), Edge(3, 5), Edge(1, 2), Edge(5, 4), Edge(5, 6))
    
    val root = VertexCollector.createTree(edges)
    
    traverse(root)
  }

}