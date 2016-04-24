package domain.tree

object BinarySearch {

  def search(tree: Node, key: Int): Node = {
    require(tree != null)
    search(tree, key, null)
  }

  private def search(tree: Node, key: Int, parent: Node): Node = {
    if (tree == null) {
      val n = new Node(key)
      if (parent.key > key)
        parent.left = n
      else
        parent.right = n

      n
    } else if (tree.key == key)
      tree
    else {
      if (tree.key > key)
        search(tree.left, key, tree)
      else
        search(tree.right, key, tree)
    }
  }

  def main(args: Array[String]) {
    val tree = Node(3, Node(1, null, Node(2, null, null)), Node(5, Node(4, null, null), Node(6, null, null)))

    println(tree)

    println(search(tree, 3))
    println(search(tree, 1))
    println(search(tree, 2))
    println(search(tree, 5))
    println(search(tree, 4))
    println(search(tree, 6))
    println(search(tree, 7))
    println(tree)
  }

}