package domain.tree

class WeightedEdge (val weight: Double, u: Int, v: Int) extends Edge(u, v) with Ordered[WeightedEdge] {
    
    override def compare(that: WeightedEdge): Int = {
      weight.compare(that.weight)  
    }
    
    def to(from: Int): Int = {
      if (from == u)
        v
      else if (from == v)
        u
      else  
        throw new IllegalArgumentException        
    }
    
}