package domain.structures

case class SetRepresentation(val s: String) {

  require(s != null && !s.isEmpty())
  require(s.forall { c => c == '0' || c == '1' })

  def complement(): SetRepresentation = SetRepresentation(s.map { c => if (c == '1') '0' else '1' })

  def union(that: SetRepresentation): SetRepresentation = {
    val op = (s1: String, s2: String, index: Int) => if (s1.charAt(index) == '1' || s2.charAt(index) == '1') '1' else '0'
    executeOp(that, op)
  }

  def intersection(that: SetRepresentation): SetRepresentation = {
    val op = (s1: String, s2: String, index: Int) => if (s1.charAt(index) == '1' && s2.charAt(index) == '1') '1' else '0'
    executeOp(that, op)
  }

  def difference(that: SetRepresentation): SetRepresentation = {
    val op = (s1: String, s2: String, index: Int) => if (s2.charAt(index) == '0') s1.charAt(index) else '0'
    executeOp(that, op)
  }
  
  def symmetricDifference(that: SetRepresentation): SetRepresentation = {
    val op = (s1: String, s2: String, index: Int) => 
      if (s2.charAt(index) == '0') s1.charAt(index) 
      else if (s1.charAt(index) == '0') s2.charAt(index)
      else '0'
        
    executeOp(that, op)
  }

  private def executeOp(that: SetRepresentation, op: (String, String, Int) => Char): SetRepresentation = {
    require(that != null)
    require(s.length() == that.s.length())

    val indexes = (0 until s.length())

    val u = indexes.toIterable.map { index => op(s, that.s, index) }

    SetRepresentation(new String(u.toArray))
  }

}