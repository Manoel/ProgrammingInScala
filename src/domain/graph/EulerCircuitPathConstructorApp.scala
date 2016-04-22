package domain.graph

object EulerCircuitPathConstructorApp {
  def main(agrs: Array[String]) {
    val adjMatrix1 = Array(
      Array(0, 1, 0, 1, 1, 1),
      Array(1, 0, 1, 0, 0, 0),
      Array(0, 1, 0, 1, 0, 0),
      Array(1, 0, 1, 0, 0, 0),
      Array(1, 0, 0, 0, 0, 1),
      Array(1, 0, 0, 0, 1, 0)
    )
    
    val graph1 = new GraphWithAdjMatrix(adjMatrix1)
    
    val circuit1 = EulerCircuitPathConstructor.circuitOrPath(graph1)
    
    println(circuit1)
    
    val adjMatrix2 = Array(
      Array(0, 1, 0, 1, 1, 1),
      Array(1, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 1, 0, 0),
      Array(1, 0, 1, 0, 0, 0),
      Array(1, 0, 0, 0, 0, 1),
      Array(1, 0, 0, 0, 1, 0)
    )
    
    val graph2 = new GraphWithAdjMatrix(adjMatrix2)
    
    val circuit2 = EulerCircuitPathConstructor.circuitOrPath(graph2)
    
    println(circuit2)
    
  }
  
  def printMatrix(matrix: Array[Array[Int]]) {
    for (i <- 0 to 5) {
      for (j <- 0 to 5) {
        print(matrix(i)(j) + " ")
      }
      println()
    }
  }
}