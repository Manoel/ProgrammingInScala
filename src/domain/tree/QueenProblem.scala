package domain.tree

import scala.collection.mutable.Leaf

object QueenProblem {

  var result: Array[Array[Int]] = null

  class QueenNode(val column: Int, val board: Array[Array[Int]])

  def positioning(n: Int): Array[Array[Int]] = {
    for (r <- 0 until n) {
      if (result == null) {
        val board = new Array[Array[Int]](n)

        for (u <- 0 until n)
          board(u) = new Array[Int](n)

        board(r)(0) = 1
        
        doPositioning(new QueenNode(0, board))
      }
    }

    result
  }

  private def doPositioning(node: QueenNode) {

    for (r <- 0 until node.board.length) {
      if (result == null) {
        val nextNode = createNextNode(node)
        
        var canUseRow = true

        for (s <- 0 until node.board.length)
          if (nextNode.board(r)(s) == 1)
            canUseRow = false

        if (canUseRow) {
          var canUseDiagonal = true
          
          var i = r - 1
          var j = nextNode.column + 1
          while (i >= 0 && j < node.board.length) {
            if (nextNode.board(i)(j) == 1)
              canUseDiagonal = false
            i -= 1
            j += 1
          }
          
          i = r + 1
          j = nextNode.column + 1
          while (i < node.board.length && j < node.board.length) {
            if (nextNode.board(i)(j) == 1)
              canUseDiagonal = false
            i += 1
            j += 1
          }

          i = r - 1
          j = nextNode.column - 1
          while (i >= 0 && j >= 0) {
            if (nextNode.board(i)(j) == 1)
              canUseDiagonal = false
            i -= 1
            j -= 1
          }

          i = r + 1
          j = nextNode.column - 1
          while (i < node.board.length && j >= 0) {
            if (nextNode.board(i)(j) == 1)
              canUseDiagonal = false
            i += 1
            j -= 1
          }

          if (canUseDiagonal) {
            nextNode.board(r)(nextNode.column) = 1

            if (nextNode.column == node.board.length - 1)
              result = nextNode.board
            else
              doPositioning(nextNode)

          }
        }
      }
    }
  }

  private def createNextNode(node: QueenNode): QueenNode = {
    val board = new Array[Array[Int]](node.board.length)

    for (u <- 0 until board.length) {
      board(u) = new Array[Int](board.length)
      Array.copy(node.board(u), 0, board(u), 0, board.length)
    }

    new QueenNode(node.column + 1, board)
  }
  
  private def printBoard(board: Array[Array[Int]]) {
    for (i <- 0 until board.length) {
      for (j <- 0 until board.length) {
        print(board(i)(j) + " ")
      }
      println()
    }
    println("------------")
  }
  
  def main(args: Array[String]) {
    val board = positioning(8)
    printBoard(board)
    
  }

}