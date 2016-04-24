package domain.tree

import scala.collection.mutable.PriorityQueue
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

object HuffmanCode {

  case class Node(val value: Char, val frequency: Double, var left: Node, var right: Node) extends Ordered[Node] {

    def this(value: Char, frequency: Double) = this(value, frequency, null, null)

    def compare(that: Node): Int = {
      that.frequency.compare(frequency)
    }
    
    def isLeaf = left == null && right == null
  }

  def code(symbols: Array[(Char, Double)]): Map[Char, String] = {
    require(symbols != null)
    require(!symbols.isEmpty)

    val pq = PriorityQueue[Node]()

    for (s <- symbols)
      pq += new Node(s._1, s._2)

    while (pq.length > 1) {
      val n1 = pq.dequeue()
      val n2 = pq.dequeue()
      pq += new Node(' ', n1.frequency + n2.frequency, n2, n1)
    }

    buildCode(pq.iterator.next())
  }
  
  private def buildCode(root: Node): Map[Char, String] = {
    val table = Map[Char, String]()
    
    if (root.isLeaf) {
      table += (root.value -> "0")  
    } else {
      buildCode(root, table, new StringBuilder())
    }
    
    table
  }
  
  private def buildCode(root: Node, table: Map[Char, String], currentCode: StringBuilder) {
    if (root.isLeaf) {
      table += (root.value -> currentCode.toString())
    } else {
      if (root.left != null) {
        currentCode.append("0")
        buildCode(root.left, table, currentCode)
        currentCode.deleteCharAt(currentCode.length - 1)
      }
      
      if (root.right != null) {
        currentCode.append("1")
        buildCode(root.right, table, currentCode)
        currentCode.deleteCharAt(currentCode.length - 1)
      }
    }
  }

  def main(args: Array[String]) {
    val symbols = Array(('A', 0.08), ('B', 0.10), ('C', 0.12), ('D', 0.15), ('E', 0.20), ('F', 0.35))

    println(code(symbols))
  }

}