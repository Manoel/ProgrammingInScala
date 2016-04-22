package domain.logic

object BitwiseOperations {

	def and(a: String, b: String): String = {
		val intValues = toInt(a, b)		
		Integer.toString(intValues._1 & intValues._2, 2)
	}
	
	def or(a: String, b: String): String = {
		val intValues = toInt(a, b)		
		Integer.toString(intValues._1 | intValues._2, 2)
	}
	
	def xor(a: String, b: String): String = {
		val intValues = toInt(a, b)		
		Integer.toString(intValues._1 ^ intValues._2, 2)
	}
	
	private def toInt(a: String, b: String): (Int, Int) = (Integer.parseInt(a, 2), Integer.parseInt(b, 2))
	
	def main(args: Array[String]) {
		println(and("11", "10"))
		println(or("11", "10"))
		println(xor("11", "10"))
	}

}