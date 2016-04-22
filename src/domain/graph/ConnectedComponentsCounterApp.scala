package domain.graph

object ConnectedComponentsCounterApp {
  def main(args: Array[String]) {
    val adjMatrix = Array(
      Array(0, 1, 1, 1, 0, 0),
      Array(1, 0, 1, 1, 0, 0),
      Array(1, 1, 0, 1, 0, 0),
      Array(1, 1, 1, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1),
      Array(0, 0, 0, 0, 1, 0))

    val graph = new GraphWithAdjMatrix(adjMatrix)

    println(ConnectedComponentsCounter.connectedComponets(graph))

    val adjMatrix2 = Array(
      Array(0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0))

    val graph2 = new GraphWithAdjMatrix(adjMatrix2)

    println(ConnectedComponentsCounter.connectedComponets(graph2))
  }
}