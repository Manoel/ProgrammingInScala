package domain.logic

object TruthValues {

	def and(p: Boolean, q: Boolean): Boolean = p && q
	
	def or(p: Boolean, q: Boolean): Boolean = p || q
	
	def exOr(p: Boolean, q: Boolean): Boolean = p ^ q
	
	def conditional(p: Boolean, q: Boolean): Boolean = if (q) true else if (p) false else true
	
	def biconditional(p: Boolean, q: Boolean): Boolean = p == q
	
	def not(p: Boolean): Boolean = !p
	
	def printTruthTable(op: (Boolean, Boolean) => Boolean, name: String) {
		println("Truth Table of " + name)
		println("------------------------------")
	  println("P	|Q		|Result")
		println("------------------------------")
	  println("true	|true		|" + op(true, true))
		println("true	|false		|" + op(true, false))
		println("false	|true		|" + op(false, true))
		println("false	|false		|" + op(false, false))
		println("------------------------------")
	}
	
	def main(args: Array[String]) {
		printTruthTable(and, "AND")
		printTruthTable(or, "OR")
		printTruthTable(exOr, "EXCLUSIVE OR")
		printTruthTable(conditional, "CONDITIONAL")
		printTruthTable(biconditional, "BICONTIONAL")
	}

}