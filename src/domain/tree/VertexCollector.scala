package domain.tree

import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue

object VertexCollector {
  
  case class VertexInfo(val parent: Int, val level: Int, val ancestors: List[Int], val children: List[Int], val descendants: List[Int]) 
  
  def collect(edges: List[Edge], vertex: Int): VertexInfo = {
    val root = parent(edges)
    val nodes = fillTree(root, edges)
    val node = nodes.get(vertex).get
    
    val p = node.getParent.key
    val children = node.getChildren.map { x => x.key }
    val levelAncestors = levelAndAncestors(node)
    val d = descendants(node)
    
    VertexInfo(p, levelAncestors._1, levelAncestors._2, children, d)
  }
  
  private def descendants(node: TreeNode): List[Int] = {
    val result = ListBuffer[Int]()
    val queue = Queue[TreeNode](node)
    
    while (!queue.isEmpty) {
      val n = queue.dequeue()
      
      for (c <- n.getChildren)
        result += c.key
      
      queue ++= n.getChildren
      
    }
    
    result.toList
  }
  
  private def levelAndAncestors(node: TreeNode): (Int, List[Int]) = {
    val level = Array(0)
    val ancestors = ListBuffer[Int]()
    doLevelAndAncestors(node, level, ancestors)
    (level(0), ancestors.toList)
  }
  
  private def doLevelAndAncestors(node: TreeNode, level: Array[Int], ancestors: ListBuffer[Int]) {
    var p = node.getParent
    while (p != null) {
      level(0) += 1
      ancestors += p.key
      p = p.getParent
    }
  }
  
  def createTree(edges: List[Edge]): TreeNode = {
    val root = parent(edges)
    fillTree(root, edges)
    root
  }
  
  private def fillTree(root: TreeNode, edges: List[Edge]): Map[Int, TreeNode] = {
    val nodes = Map(root.key -> root)
    
    for (e <- edges) {
      val from = add(nodes, e.u)
      val to = add(nodes, e.v)
      from.add(to)      
    }
    
    nodes
  }
  
  private def add(nodes: Map[Int, TreeNode], key: Int): TreeNode = {
    if (!nodes.contains(key))
      nodes += (key -> TreeNode(key))
    nodes.get(key).get         
  }
  
  private def parent(edges: List[Edge]): TreeNode = {
	  val all = Set[Int]()
    val to = Set[Int]()
        
    for (e <- edges) {
    	all += e.u
    	all += e.v
      to += e.v
    }
	  
	  val diff = all -- to
	  
	  require(diff.size == 1)
	  
	  new TreeNode(diff.iterator.next())
  }
  
  def main(args: Array[String]) {
    val edges = List(Edge(3, 1), Edge(3, 5), Edge(1, 2), Edge(5, 4), Edge(5, 6))
    
    println(parent(edges))
    
    println(collect(edges, 2))
  }
  
}