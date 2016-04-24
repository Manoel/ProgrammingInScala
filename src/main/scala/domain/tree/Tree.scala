package domain.tree

class Tree(val key: Int, val left: Option[Tree], val rigth: Option[Tree]) {
  def preorder: Unit = {
    println(key)
    left.foreach { l => l.preorder }
    rigth.foreach { r => r.preorder }
  }

  def inorder: Unit = {
    left.foreach { l => l.inorder }
    println(key)
    rigth.foreach { r => r.inorder }
  }

  def postorder: Unit = {
    left.foreach { l => l.postorder }
    rigth.foreach { r => r.postorder }
    println(key)
  }
}

object Tree {
  def tree(key: Int, left: Option[Tree] = Option.empty[Tree], rigth: Option[Tree] = Option.empty[Tree]) = Option(new Tree(key, left, rigth))
}