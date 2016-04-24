package domain.graph

import scala.collection.mutable.ListBuffer
import scala.collection.mutable

object FunctionsGenerator {

  case class FnGeneratorNode(val list1: List[Int], val list2: List[Int]) {

    def incidentEdges: List[FnGeneratorEdge] = {

      if (list1.isEmpty)
        List.empty
      else {

        val head = list1.head

        val tail = list1.tail

        val result = ListBuffer[FnGeneratorEdge]()

        val list2Copy = ListBuffer[Int]() ++ list2

        list2.foreach { x => result += new FnGeneratorEdge((head, x), this, new FnGeneratorNode(tail, list2.diff(x :: Nil))) }

        result.toList
      }
    }

  }

  case class FnGeneratorEdge(val mapping: (Int, Int), val u: FnGeneratorNode, val v: FnGeneratorNode)
  
  def generateFunctions(node: FnGeneratorNode): List[Map[Int, Int]] = {
    val functions = ListBuffer[Map[Int, Int]]()
    
    generateFunctions(node, functions)
    
    functions.toList
  }
  
  private def generateFunctions(node: FnGeneratorNode, functions: ListBuffer[Map[Int, Int]]) {
    
    for (edge <- node.incidentEdges) 
      doGenerateFunctions(edge, functions, mutable.Map[Int, Int]() + (edge.mapping._1 -> edge.mapping._2))    
  }
  
  private def doGenerateFunctions(edge: FnGeneratorEdge, functions: ListBuffer[Map[Int, Int]], function: mutable.Map[Int, Int]) {
    
    for (e <- edge.v.incidentEdges) 
      doGenerateFunctions(e, functions, function + (e.mapping._1 -> e.mapping._2))
    
    if (edge.v.incidentEdges.isEmpty) 
      functions += function.toMap
    
    function.remove(edge.mapping._1)
    
  }

  def main(args: Array[String]) {

    val node = FnGeneratorNode(List(3), List(6))

    val edges = node.incidentEdges

    println(edges)
  }

}