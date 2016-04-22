package domain.tree

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer

class NimNode(val nimPosition: NimPosition) {

  private var payoff: Int = 0

  private def this(nimPosition: NimPosition, parent: NimNode, level: Int) = {
    this(nimPosition)
    this.parent = parent
    this.level = level
  }

  private var parent: NimNode = null

  private var level: Int = 0

  private var children: Set[NimNode] = Set()

  def buildGameTree() {
    nimPosition.nextPositions().foreach { x => children += new NimNode(x, this, level + 1) }

    children.foreach { x => x.buildGameTree() }

    if (children.isEmpty) {
      if (level % 2 == 0)
        payoff = -1
      else
        payoff = 1
    }
  }

  def updatePayoff(): Int = {
    if (children.isEmpty) {
      if (level % 2 == 0)
        payoff = -1
      else
        payoff = 1
      
    } else {
      
      val childrenPayoffs = children.map { x => x.updatePayoff() }
      
      if (level % 2 == 0)
        payoff = childrenPayoffs.reduce(scala.math.max)
      else 
        payoff = childrenPayoffs.reduce(scala.math.min)
    }
    payoff
  }
  
  def optimalStrategyFirstPlayer(): List[NimPosition] = {
    val result = ListBuffer(nimPosition)
    doOptimalStrategyFirstPlayer(result)
    result.toList
  }
  
  private def doOptimalStrategyFirstPlayer(result: ListBuffer[NimPosition]) {
    if (!children.isEmpty) {
      val c = children.find { x => x.payoff == 1 }.getOrElse(children.iterator.next())
      result += c.nimPosition
      c.doOptimalStrategyFirstPlayer(result)
    }
  }

  override def toString = nimPosition + " [ " + children + " ] "

}

object NimNode {
  def main(args: Array[String]) {
    val nimNode = new NimNode(new NimPosition(List(2, 2, 1)))
    nimNode.buildGameTree()
    println(nimNode.updatePayoff())
    println(nimNode.optimalStrategyFirstPlayer())
  }
}