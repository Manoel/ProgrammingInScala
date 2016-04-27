package domain.solution.knight

import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import scala.math._

object Solution {

  val dim = 15

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
    // use abs because it is symmetric
    var a = max(abs(A), abs(B))
    var b = min(abs(A), abs(B))

    val specialCase = getSpecialCase

    val node = Node(a, b)

    specialCase.getOrElse(node, generalCase(node))

  }

  private def generalCase(node: Node): Int = {
    // diagonal 4: starts at (4,4) x = y
    // diagonal 1: starts at (3,2) x = y + 1 3
    // diagonal 3: starts at (5,3) x = y + 2 4
    // diagonal 1: starts at (7,4) x = y + 3 5
    // diagonal 1: starts at (9,5) x = y + 4 6
    
    val x = node.x
    val y = node.y
    val r = 2
    var n = 0
    var a_1 = 0
    var a_n = 0
    var result = 0

    if (isDiagonal(x, y)) {
      if (x == y) {
        n = (x - 4) / 3 + 1
        a_1 = 4
      } else {
        val num_terms = x - y
        val jump = 2
        val start = 3 + (num_terms - 1) * jump
        n = (x - start) / 3 + 1
        a_1 = num_terms + 2
      }

      a_n = a_1 + (n - 1) * r

      result = a_n

    } else {
      // (4, 0)  : starts at 2  base case (or special case)
      // (2, 1)  : starts at 1  base case (or special case)
      // (4, 2)  : starts at 2  general case
      // (6, 3)  : starts at 3  general case
      // (8, 4)  : starts at 4  general case
      // (10, 5) : starts at 5  general case
      // (12, 6) : starts at 6  general case
      // ...

      var x_1 = 0

      if (y == 0) {
        a_1 = 2
        x_1 = 4
      } else if (y == 1) {
        a_1 = 1
        x_1 = 2
      } else {
        a_1 = y
        val y_n = y - 1
        x_1 = 4 + (y_n - 1) * 2
      }

      n = (x - x_1) / 4 + 1

      a_n = a_1 + (n - 1) * r

      result = a_n + x - (x_1 + (n - 1) * 4)
    }

    result

  }

  private def isDiagonal(x: Int, y: Int): Boolean = {
    // diagonal 1: starts at (4,4) x = y (special case)
    // diagonal 2: starts at (3,2) x = y + 1 
    // diagonal 3: starts at (5,3) x = y + 2
    // diagonal 4: starts at (7,4) x = y + 3
    // diagonal 5: starts at (9,5) x = y + 4
    // ...
    
    if (x == y && x >= 4)
      true
    else {
      val n = x - y
      var r = 2
      val a_n = 3 + (n - 1) * r
      
      x >= a_n
    }
  }

  private def getSpecialCase: Map[Node, Int] = {
    val orign = Node(0, 0)
    val one_zero = Node(1, 0)
    val two_zero = Node(2, 0)
    val three_zero = Node(3, 0)
    val zero_one = Node(0, 1)
    val one_one = Node(1, 1)
    val zero_two = Node(0, 2)
    val two_two = Node(2, 2)
    val zero_three = Node(0, 3)
    val three_three = Node(3, 3)

    Map(orign -> 0, one_zero -> 3, two_zero -> 2, three_zero -> 3,
      zero_one -> 3, one_one -> 2,
      zero_two -> 2, two_two -> 4,
      zero_three -> 3, three_three -> 2)

  }

  def bfs(A: Int, B: Int): Int = {
    if (A == 0 && B == 0)
      0
    else {

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

          updateBoard(n)

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
  }

  private def updateBoard(n: Node) = {
    if (n.x >= 0 && n.x < first.length && n.y >= 0 && n.y < first.length)
      first(n.y)(n.x) = n.distance

    else if (n.x < 0 && abs(n.x) - 1 < first.length && n.y >= 0 && n.y < first.length)
      second(n.y)(abs(n.x) - 1) = n.distance

    else if (n.x < 0 && abs(n.x) - 1 < first.length && n.y < 0 && abs(n.y) - 1 < first.length)
      third(abs(n.y) - 1)(abs(n.x) - 1) = n.distance

    else if (n.x >= 0 && n.x < first.length && n.y < 0 && abs(n.y) - 1 < first.length)
      fourth(abs(n.y) - 1)(n.x) = n.distance
  }

  private def printBoard() {
    val result = ListBuffer[ListBuffer[String]]()

    for (i <- first.length - 1 to 0 by -1) {
      val l = (ListBuffer() ++ (ListBuffer() ++ second(i)).reverse ++ first(i))
      val lstr = l.map(i => if (i >= 10) i + "" else " " + i)
      result += lstr
    }

    for (i <- 0 until first.length) {
      val l = (ListBuffer() ++ (ListBuffer() ++ third(i)).reverse ++ fourth(i))
      val lstr = l.map(i => if (i >= 10) i + "" else " " + i)
      result += lstr
    }

    result.foreach(a => println(a))
  }

  def main(args: Array[String]) {
    var param = (200, 320)
    var result_bfs = bfs(param._1, param._2)
    var result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (100, 1)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))
    
    param = (4, 5)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))
    
    param = (24, 25)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))
    
    param = (5, 5)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (0, 0)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (1, 0)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (2, 0)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (3, 0)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (0, 1)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (1, 1)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (0, 2)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (2, 2)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (0, 3)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (3, 3)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (4, 0)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (2, 1)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (4, 2)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

    param = (6, 3)
    result_bfs = bfs(param._1, param._2)
    result_formulee = solution(param._1, param._2)
    println(param)
    println("BFS = " + result_bfs + " " + " Result formulee = " + result_formulee + " " + (result_bfs == result_formulee))

  }

}