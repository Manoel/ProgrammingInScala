package domain.tree

import Tree.tree

object TreeApp {
  def main(args: Array[String]) = {
    val t = tree(10, tree(5, tree(3), tree(7)), tree(15, tree(12), tree(20)))
    println("Inorder:")
    t.get.inorder
    println("Preorder:")
    t.get.preorder
    println("Postorder:")
    t.get.postorder
  }
}