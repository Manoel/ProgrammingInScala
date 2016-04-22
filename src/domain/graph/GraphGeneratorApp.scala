package domain.graph

object GraphGeneratorApp {
  def main(args: Array[String]) {
    
    println(">>>> 1000 Undirected graphs with 4 vertices:")
    for (i <- 1 to 1000) {
      val graph = GraphGenerator.generate(4, false)
      println("==============================================================")
      println(graph)
      println("==============================================================")
    }
    
    println(">>>> 1000 Directed graphs with 4 vertices:")
    for (i <- 1 to 1000) {
      val graph = GraphGenerator.generate(4, true)
      println("==============================================================")
      println(graph)
      println("==============================================================")
    }
  }
}