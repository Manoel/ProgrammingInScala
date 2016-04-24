package domain.structures

case class FuzzySet[E](val set: Map[E, Double]) {

  require(set != null)

  require(set.forall(e => e._2 >= 0 && e._2 <= 1))

  def add(element: E, degree: Double): FuzzySet[E] = {
    require(element != null)
    require(degree >= 0 && degree <= 1)

    val newSet = set + (element -> degree)

    FuzzySet(newSet)
  }
  
  def complement() = FuzzySet(set.map(e => (e._1, 1.0 - e._2)))
  
  def union(that: FuzzySet[E]) = {
    val onlyInMe = set.filter(e => !that.set.contains(e._1))
    val onlyInThat = that.set.filter(e => !set.contains(e._1))
    val inBoth = set.filter(e => that.set.contains(e._1)).map(both => (both._1, scala.math.max(both._2, that.set(both._1))))
    
    FuzzySet(inBoth)
  }
  
  def intersection(that: FuzzySet[E]) = {
    val inBoth = set.filter(e => that.set.contains(e._1)).map(both => (both._1, scala.math.min(both._2, that.set(both._1))))
    
    FuzzySet(inBoth)
  }
  
}

object FuzzySet {
  
  def main(args: Array[String]) {
    val famousPeople = FuzzySet(Map("Alice" -> 0.6, "Brian" -> 0.9, "Fred" -> 0.4, "Oscar" -> 0.1, "Rita" -> 0.5))
    val richPeople = FuzzySet(Map("Alice" -> 0.4, "Brian" -> 0.8, "Fred" -> 0.2, "Oscar" -> 0.9, "Rita" -> 0.7))
 
    println("Famous people...........: " + famousPeople)
    println("Rich people.............: " + richPeople)
    println("Not famous people.......: " + famousPeople.complement())
    println("Famous or rich people...: " + famousPeople.union(richPeople))
    println("Famous and rich people..: " + famousPeople.intersection(richPeople))
    
  }
  
}

