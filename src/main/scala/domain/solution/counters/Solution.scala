package domain.solution.counters

object Solution {
    def solution(N: Int, A: Array[Int]): Array[Int] = {
        val counters = Array.ofDim[Int](N)
        
        var maxUpdateAll = 0
        
        var max = 0
        
        for (op <- A) {
            if (op < N) {
                if (counters(op-1) >= maxUpdateAll)
                    counters(op-1) += 1
                else
                    counters(op-1) = maxUpdateAll + 1
                
                if (counters(op-1) > max)
                    max = counters(op-1)
                
            } else {
                maxUpdateAll = max
            }
        }
        
        for (i <- 0 until counters.length) {
            if (counters(i) < maxUpdateAll)
                counters(i) = maxUpdateAll
        }
        
        counters
    }
    
    def main (args: Array[String]) {
      println(solution(5, Array(3, 4, 4, 6, 1, 4, 4)).deep.mkString(" "))
    }
}