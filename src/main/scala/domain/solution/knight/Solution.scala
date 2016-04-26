package domain.solution.knight

import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import scala.math._

object Solution {
  
  val dim = 11
  
  var first: Array[Array[Int]] = null
  var second: Array[Array[Int]] = null
  var third: Array[Array[Int]] = null
  var fourth: Array[Array[Int]] = null
  
  case class Node(x: Int, y: Int) {
    
    var distance = 0
    
    def next(): List[Node] = {
      val n1 = Node(x + 2, y + 1)
      val n2 = Node(x + 2, y - 1)
      val n3 = Node(x - 2, y + 1)
      val n4 = Node(x - 2, y - 1)
      val n5 = Node(x + 1, y + 2)
      val n6 = Node(x + 1, y - 2)
      val n7 = Node(x - 1, y + 2)
      val n8 = Node(x - 1, y - 2)

      List(n1, n2, n3, n4, n5, n6, n7, n8)
    }

  }

  def solution(A: Int, B: Int): Int = {
    first = Array.ofDim[Int](dim, dim)
    second = Array.ofDim[Int](dim, dim)
    third = Array.ofDim[Int](dim, dim)
    fourth = Array.ofDim[Int](dim, dim)
    
    val initialNode = Node(0, 0)
    
    val queue = Queue[Node](initialNode)
    
    val visited = scala.collection.mutable.Set(initialNode)
    
    var result = -1
    
    while (result == -1 && !queue.isEmpty) {
      val node = queue.dequeue()
      
      node.next().filter(n => !visited.contains(n)).foreach(n => {
        n.distance = node.distance + 1
        visited += n
        
        if (n.x >=0 && n.x < first.length && n.y >= 0 && n.y < first.length)
          first(n.x)(n.y) = n.distance
          
        if (n.x < 0 && abs(n.x) < first.length && n.y >= 0 && n.y < first.length)
          second(abs(n.x))(n.y) = n.distance
       
        if (n.x < 0 && abs(n.x) < first.length && n.y < 0 && abs(n.y) < first.length)
          third(abs(n.x))(abs(n.y)) = n.distance
        
        if (n.x >=0 && n.x < first.length && n.y < 0 && abs(n.y) < first.length)
          fourth(n.x)(abs(n.y)) = n.distance  
          
        if (n.distance <= 100000000 && n.x == A && n.y == B)
          result = n.distance
        else if (n.distance < 100000000)
          queue += n
        else if (result == -1)
          result = -2
      })
        
    }
    
    result
  }
  
  def main(args: Array[String]) {
    println("(200,320)")
    println(solution(200, 320))
    
    val result = ListBuffer[ListBuffer[Int]]()
    
//    for (i <- first.length - 1 to 0 by -1) {
//      result += (ListBuffer() ++ third(i) ++ fourth(i))
//    }
    
    for (i <- first.length - 1 to 0 by -1) {
//      result += (ListBuffer() ++ second(i) ++ first(i))
      result += (ListBuffer() ++ second(i))
    
    }
    
    result.foreach(a => println(a))
  }

}