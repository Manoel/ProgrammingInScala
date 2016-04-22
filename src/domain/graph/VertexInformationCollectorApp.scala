package domain.graph

object VertexInformationCollectorApp {

  def main(args: Array[String]) = {

    val adjMatrix = Array(
      Array(0, 0, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 0),
      Array(1, 1, 0, 0, 0, 0),
      Array(0, 0, 0, 0, 0, 1),
      Array(0, 0, 1, 1, 0, 0),
      Array(0, 0, 0, 0, 0, 0))

    val graph = new GraphWithAdjMatrix(adjMatrix)

    val vertexInformation = VertexInformationCollector.collect(graph, 2)

    assume(vertexInformation.level == 1)
    assume(vertexInformation.parent == 4)
    assume(vertexInformation.children == Set(0, 1))
    assume(vertexInformation.ancestors == Set(4))
    assume(vertexInformation.descendants == Set(0, 1))
  }

}