package domain.tree

import scala.collection.mutable.Queue
import scala.collection.mutable.Stack

object TreeBuilder {

  case class Node(val key: Int, var left: Node, var right: Node) {
    def this(key: Int) = this(key, null, null)
  }

  def buildBST(items: List[Int]): Node = {
    require(items != null)
    require(!items.isEmpty)

    buildBST(items, 0, items.length - 1)
  }

  private def buildBST(items: List[Int], start: Int, end: Int): Node = {
    if (start == end)
      new Node(items(start))
    else if (start > end)
      null
    else {
      val n = (start + end) / 2
      new Node(items(n), buildBST(items, start, n - 1), buildBST(items, n + 1, end))
    }
  }

  def buildBSTIterative(items: List[Int]): Node = {
    require(items != null)
    require(!items.isEmpty)

    val n = (items.length - 1) / 2

    val root = new Node(items(n))

    val stack = new Stack[(Node, (Int, Int), (Int, Int))]()

    stack.push((root, (0, n - 1), (n + 1, items.length - 1)))

    while (!stack.isEmpty) {
      val e = stack.pop()

      val node = e._1
      val int1 = e._2
      val int2 = e._3

      val npLeft = nodePosition(items, int1)

      node.left = npLeft._1
      
      if (npLeft._2 != -1) {
         stack.push((node.left, (int1._1, npLeft._2 - 1), (npLeft._2 + 1, int1._2)))
      }
      
      val npRight = nodePosition(items, int2)

      node.right = npRight._1
      
      if (npRight._2 != -1) {
         stack.push((node.right, (int2._1, npRight._2 - 1), (npRight._2 + 1, int2._2)))
      }
    }

    root
  }

  private def nodePosition(items: List[Int], int: (Int, Int)): (Node, Int) = {
    var node:Node = null
    var p = -1
    
    if (int._1 == int._2)
      node = new Node(items(int._1))
    else if (int._1 > int._2)
      node = null
    else {
      p = (int._1 + int._2) / 2
      node = new Node(items(p))
    }
    
    (node, p)
  }

  def build(items: List[Int]): Node = {
    require(items != null)
    require(!items.isEmpty)

    val itemsQueue = Queue[Int]()
    itemsQueue ++= items.slice(1, items.length)

    val root = new Node(items(0))

    val nodesQueue = Queue(root)

    while (!nodesQueue.isEmpty) {
      val n = nodesQueue.dequeue()

      if (!itemsQueue.isEmpty) {
        val left = new Node(itemsQueue.dequeue())
        n.left = left
        nodesQueue += left
      }

      if (!itemsQueue.isEmpty) {
        val right = new Node(itemsQueue.dequeue())
        n.right = right
        nodesQueue += right
      }

    }

    root
  }

  def main(args: Array[String]) {
    val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val root1 = buildBST(list)
    
    val root2 = buildBSTIterative(list)

    println(root1 == root2)

  }

}