package domain.graph

class VertexInformation(
    val parent: Int, 
    val level: Int, 
    val children: Set[Int], 
    val ancestors: Set[Int], 
    val descendants: Set[Int]) {
}