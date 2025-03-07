/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example

object Lecture1 {
  def main(args: Array[String]): Unit = {
    val square:Int => Int = a => a*a 
    for(i <- 0.to(2)){
        println(square(i))
    }

    val area:Double => Double = R => R*R*3.14
    println(area(12))

    def CtoF(C:Double):Double = {
      C*9/5+35
    }
    println(CtoF(36.6))

    val strLenght: String => Unit = S => println(S.length())

    val AreaBetweenTwoPoints: (Double, Double, Double, Double) => Double = (x1, y1, x2, y2) => Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1,2))
    println(AreaBetweenTwoPoints(0,0,1,0))
  }
}
