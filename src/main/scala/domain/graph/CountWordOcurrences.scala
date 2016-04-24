package domain.graph

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set

object CountWordOcurrences {

  case class Vertex(row: Int, column: Int) {
    private var neighbours: List[Vertex] = null
    
    def getNeighbours = {
      if (neighbours == null)
        neighbours = buildNeighbours(this)
      
      neighbours
    }
  }
  
  private var boardLimit: Int = 0
  private var board : Array[Array[Char]] = null
  private var word: String = null

  private def buildNeighbours(vertex: Vertex): List[Vertex] = {
    val neighbours = ListBuffer[Vertex]()
    
    if (vertex.row - 1 >= 0 && vertex.column - 1 >= 0)
      neighbours += new Vertex(vertex.row - 1, vertex.column - 1)
    
    if (vertex.row - 1 >= 0)
      neighbours += new Vertex(vertex.row - 1, vertex.column)
    
    if (vertex.column - 1 >= 0)
      neighbours += new Vertex(vertex.row, vertex.column - 1)
    
    if (vertex.row - 1 >= 0 && vertex.column + 1 <= boardLimit)
      neighbours += new Vertex(vertex.row - 1, vertex.column + 1)
    
    if (vertex.column + 1 <= boardLimit)
      neighbours += new Vertex(vertex.row, vertex.column + 1)
    
    if (vertex.row + 1 <= boardLimit && vertex.column - 1 >= 0)
      neighbours += new Vertex(vertex.row + 1, vertex.column - 1)
    
    if (vertex.row + 1 <= boardLimit)
      neighbours += new Vertex(vertex.row + 1, vertex.column)
    
    if (vertex.row + 1 <= boardLimit && vertex.column + 1 <= boardLimit)
      neighbours += new Vertex(vertex.row + 1, vertex.column + 1)
    
    neighbours.toList
  }
  
  def count(board: Array[Array[Char]], word: String): Int = {
    requireSquareBoard(board)
    require(word != null && !word.isEmpty())
    
    boardLimit = board.length - 1
    this.board = board
    this.word = word
    
    val count = Array(0)
    
    for (i <- 0 until board.length; j <- 0 until board(0).length) {
      if (board(i)(j) == word.charAt(0)) {
        val vertex = new Vertex(i, j)
        val visited = Set(vertex)
        dfs(vertex, visited, count, 1)
      }
    }

    count(0)
  }
  
  private def dfs(vertex: Vertex, visited: Set[Vertex], count: Array[Int], nextIndex: Int) {
    for (v <- vertex.getNeighbours) {
      if (!visited.contains(v) 
          && nextIndex < word.length() 
          && word.charAt(nextIndex) == board(v.row)(v.column)) {
          
        if (nextIndex == word.length() - 1)
          count(0) += 1
        else {
          visited += v
          dfs(v, visited, count, nextIndex + 1)
          visited -= v
        }
      }
    }
  }

  private def requireSquareBoard(board: Array[Array[Char]]) {
    require(
      board != null
        && board.length > 0
        && board.forall { x =>
          (x != null
            && x.length == board.length)
        })
  }
  
  def main(args: Array[String]) {
    val board1 = Array(
      Array('A', 'B', 'O', 'B'),
      Array('L', 'O', 'L', 'A'),
      Array('A', 'L', 'A', 'F'),
      Array('Z', 'A', 'X', 'Y')
    )
    
    println(count(board1, "BOLA"))
  
    val board2 = Array(
      Array('B', 'X', 'Y', 'Z'),
      Array('X', 'O', 'Y', 'Z'),
      Array('X', 'Y', 'L', 'Z'),
      Array('X', 'Y', 'Z', 'A')
    )
    
    println(count(board2, "BOLA"))
    
    val board3 = Array(
      Array('B', 'X', 'Y', 'B'),
      Array('X', 'O', 'O', 'Z'),
      Array('X', 'L', 'L', 'Z'),
      Array('A', 'Y', 'Z', 'A')
    )
    
    println(count(board3, "BOLA"))
  }

}