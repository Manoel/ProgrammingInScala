package domain.structures

object MatrixOperations {

  def product(A: Array[Array[Int]], B: Array[Array[Int]]): Array[Array[Int]] = {
    require(canMultiply(A, B))
    
    val C = Array.ofDim[Int](A.length, B(0).length)
    
    for (i <- 0 until C.length; j <- 0 until C(0).length) {
      for (k <- 0 until A(0).length)
        C(i)(j) += A(i)(k) * B(k)(j) 
    }
    
    C
  }
  
  def booleanProduct(A: Array[Array[Int]], B: Array[Array[Int]]): Array[Array[Int]] = {
    require(canMultiply(A, B))
    require(isZeroOne(A))
    require(isZeroOne(B))
    
    val C = Array.ofDim[Int](A.length, B(0).length)
    
    for (i <- 0 until C.length; j <- 0 until C(0).length) {
      for (k <- 0 until A(0).length)
        C(i)(j) |= A(i)(k) & B(k)(j) 
    }
    
    C
  }
  
  def join(A: Array[Array[Int]], B: Array[Array[Int]]): Array[Array[Int]] = {
    zeroOneOp(A, B, _|_)    
  }
  
  def meet(A: Array[Array[Int]], B: Array[Array[Int]]): Array[Array[Int]] = {
    zeroOneOp(A, B, _&_)    
  }
  
  private def zeroOneOp(A: Array[Array[Int]], B: Array[Array[Int]], op: (Int, Int) => Int): Array[Array[Int]] = {
    require(sameDimensions(A, B))
    require(isZeroOne(A))
    require(isZeroOne(B))
    
    val J = Array.ofDim[Int](A.length, A(0).length)
    
    for (i <- 0 until A.length; j <- 0 until A(0).length)
      J(i)(j) = op(A(i)(j), B(i)(j))
      
    J
    
  }
  
  private def isZeroOne(A: Array[Array[Int]]): Boolean = {
    require(valid(A))
    require(A.toIterable.forall { row => row.forall{ value => value == 0 || value == 1} })
    true
  }
  
  def equalsMatrices(matrix1: Array[Array[Int]], matrix2: Array[Array[Int]]): Boolean = {

    for (i <- 0 until matrix1.length) {
      for (j <- 0 until matrix1.length) {
        if (matrix1(i)(j) != matrix2(i)(j))
          return false
      }
    }

    return true
  }
  
  def isSymmetric(A: Array[Array[Int]]): Boolean = {
    require(isSquare(A))
    
    val T = transpose(A)
    
    equalsMatrices(A, T)
  }
  
  def transpose(A: Array[Array[Int]]): Array[Array[Int]] = {
    valid(A)
    
    val T = Array.ofDim[Int](A(0).length, A.length)
    
    for (i <- 0 until A(0).length; j <- 0 until A.length)
      T(i)(j) = A(j)(i)
    
    T
  }
  
  def power(A: Array[Array[Int]], n: Int): Array[Array[Int]] = {
    require(isSquare(A))
    require(n >= 0)
    
    var result = Array.ofDim[Int](A.length, A.length)
    for ( i <- 0 until A.length) 
      result(i)(i) = 1
      
    for (i <- 1 to n)
      result = product(A, result)
    
    result
  }
  
  private def isSquare(A: Array[Array[Int]]): Boolean = {
    require(A != null)
    require(A.length > 0)
    require(A.toIterable.forall { row => row.length > 0 && row.length == A.length })
    
    true
  }
  
  private def valid(A: Array[Array[Int]]): Boolean = {
    require(A != null)
    require(A.length > 0)
    require(A.toIterable.forall { row => row.length > 0 && row.length == A(0).length })
    
    true
  }
  
  private def sameDimensions(A: Array[Array[Int]], B: Array[Array[Int]]): Boolean = {
    require(A != null && B != null)
    require(A.length > 0 && A.length == B.length)
    require(A.toIterable.forall { row => row.length > 0 && row.length == A(0).length })
    require(B.toIterable.forall { row => row.length > 0 && row.length == B(0).length })
    require(A(0).length == B(0).length )
    
    true
  }
  
  private def canMultiply(A: Array[Array[Int]], B: Array[Array[Int]]): Boolean = {
    require(A != null && B != null)
    require(A.length > 0 && B.length > 0)
    require(A.toIterable.forall { row => row.length > 0 && row.length == B.length })
    require(B.toIterable.forall { row => row.length > 0 && row.length == B(0).length })
    
    true
  }
  
  def main(args: Array[String]) {
    
    val A = Array(
      Array(1, 0, 4),
      Array(2, 1, 1),
      Array(3, 1, 0),
      Array(0, 2, 2)
    )
    
    val B = Array(
      Array(2, 4),
      Array(1, 1),
      Array(3, 0)
    )
    
    val C = Array(
      Array(1, 0, 4, 1),
      Array(2, 1, 1, 1),
      Array(3, 1, 0, 1),
      Array(0, 2, 2, 1)
    )
    
    val I = Array(
      Array(1, 0, 0, 0),
      Array(0, 1, 0, 0),
      Array(0, 0, 1, 0),
      Array(0, 0, 0, 1)
    ) 
    
    val sym = Array(
      Array(1, 1, 0),
      Array(1, 0, 1),
      Array(0, 1, 0)
    ) 
    
    val zeroOne = Array(
      Array(1, 1, 1),
      Array(1, 0, 1),
      Array(0, 1, 0)
    )
    
    println("A x B =")
    println(product(A, B).deep.mkString("\n"))
    println("C^0 = ")
    println(power(C, 0).deep.mkString("\n"))
    println("I^10 = ")
    println(power(I, 10).deep.mkString("\n"))
    println("I transose:")
    println(transpose(I).deep.mkString("\n"))
    println("sym is symmetric?")
    println(isSymmetric(sym))
    println("Join of sym and zeroOne")
    println(join(sym, zeroOne).deep.mkString("\n"))
    println("Meet of sym and zeroOne")
    println(meet(sym, zeroOne).deep.mkString("\n"))
  }

}