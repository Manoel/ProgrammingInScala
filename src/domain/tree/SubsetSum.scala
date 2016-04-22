package domain.tree

import scala.collection.mutable.ListBuffer

object SubsetSum {

  private var originalSet: Set[Int] = null

  private var n: Int = 0

  class SetNode(val set: Set[Int]) {

    def sum = if (set.isEmpty) 0 else set.reduce(_ + _)

    def nextNodes(): List[SetNode] = {
      var result = ListBuffer[SetNode]()

      for (e <- originalSet) {
        if (sum + e <= n && !set.contains(e))
          result += new SetNode(set + e)
      }

      result.toList
    }

    override def toString = set.toString()

  }

  def findSubset(set: Set[Int], n: Int): Set[Int] = {
    require(set != null)
    require(!set.isEmpty)
    require(n > 0)

    this.originalSet = set
    this.n = n

    val subset = new Array[Set[Int]](1)

    dfs(new SetNode(Set()), subset)

    subset(0)
  }

  private def dfs(setNode: SetNode, subset: Array[Set[Int]]) {
    val sum = setNode.sum

    if (sum == n)
      subset(0) = setNode.set
    else {
      for (s <- setNode.nextNodes()) {
        if (subset(0) == null)
          dfs(s, subset)
      }
    }
  }

  def main(args: Array[String]) {
    val set = Set(31, 27, 15, 11, 7, 5)
    val n = 39

    println(findSubset(set, 1545))
  }

}