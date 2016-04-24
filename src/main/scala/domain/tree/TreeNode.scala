package domain.tree

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue

case class TreeNode(val key: Int) {
  
  private val children = ListBuffer[TreeNode]()
  
  private var label: String = null
  
  private var parent: TreeNode = null
  
  def labelVertices() {
    label = "0"
    
    for (i <- 0 until children.length)
      children(i).label = "" + (i + 1)
      
    val queue = Queue[TreeNode]()
    queue ++= children
    
    while (!queue.isEmpty) {
      val t = queue.dequeue()
      
      for (i <- 0 until t.children.length)
        t.children(i).label = t.label + "." + (i + 1)
      
      queue ++= t.children
    }
  }
  
  def add(child: TreeNode) {
    children += child
    child.parent = this
  }
  
  def getParent = parent
  
  def getChildren = children.toList
  
  override def toString = 
    "TreeNode(" + key + ", " + label + ")[" + children.toList + "]" 
  
}