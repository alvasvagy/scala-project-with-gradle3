package org.example

object Lecture2 {
  def main(args: Array[String]): Unit = {
    val parity:Int => Boolean = A => (A % 2 == 0)
    val num:Int = 22
    println(s"number ${num} is ${if(parity(num)) "even" else "odd"}") 
    val PosNegZero:Int => String = nu => if(nu > 0) "pos" else if(nu !=0 ) "neg" else "zero"
    println(PosNegZero(3))
    println(PosNegZero(-1))
    println(PosNegZero(0))

    for(i <- 1.to(10)) println(i)
    for(i <- 1.to(9)) println(i)

    for(i <- 1.to(10)) println(i*5)

  }
}
