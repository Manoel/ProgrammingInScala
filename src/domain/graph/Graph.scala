package domain.graph

class Graph(n: Int) {
  
	val adjList = new Array[List[Int]](n)
  
  for (v <- 0 to n - 1)
    adjList(v) = List()

  def addEdge(u: Int, v: Int) = {
    assume(u >= 0 && u < n)
    assume(v >= 0 && v < n)

    adjList(u) = v :: adjList(u)
    adjList(v) = u :: adjList(v)
    
    assert(adjList(u).contains(v))
    assert(adjList(v).contains(u))
  }
	
	def degree(vertex: Int) = {
	  assume(vertex >= 0 && vertex < n)
	  adjList(vertex).size
	}
}