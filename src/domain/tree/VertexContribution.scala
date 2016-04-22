package domain.tree

class VertexContribution(val vertex: Int, var weigth: Double, var from: Int) extends Ordered[VertexContribution] {
  
  override def compare(that: VertexContribution): Int = {
    weigth.compare(that.weigth)
  }
  
}