package domain.tree

case class Node(var key: Int, var left: Node, var right: Node) {
  def this(key: Int) = this(key, null, null)
}
