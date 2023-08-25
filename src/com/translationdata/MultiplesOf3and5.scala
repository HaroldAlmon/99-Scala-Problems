package com.translationdata

//import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.Assert._


object MultiplesOf3and5 {
  def main(args: Array[String]) {
    val sum = MultiplesOf3and5.multiplesOf3And5(1000 - 1)
    println("P001: " + sum)
  }

  def multiplesOf3And5(upperLimit: Int) = {
    (calculateSumBelowLimit(upperLimit, 3 )
      + calculateSumBelowLimit(upperLimit, 5)
      - calculateSumBelowLimit(upperLimit, 15))
  }

  // This is a variation of the sum(1..n) formula.
  //
  // Sum(1k, 2k, 3k...nk) = k * n * (n + 1)
  //                   -----------
  //                        2
  // Why?
  // Sum(1k, 2k, 3k...nk) = k * Sum(1, 2, 3...n) -- Factor out the k
  // e.g. Sum(1*3, 2*3, 3*3...n*3) = 3 * Sum(1, 2, 3...n)
  //
  // Sum(1, 2, 3...n) = n * (n + 1)/2 -- standard sum(1...n) formula
  // so if we have sum(3,6,9), divide the last term by 3 (9/3 = 3), find sum(1,2,3)
  // and multiply the result by 3.

  // The formula includes n, the problem does not.
  private def calculateSumBelowLimit(upperLimit: Int, k_value: Int) = {
    val n_value = upperLimit / k_value
    //val n_value = (upperLimit - 1) / k_value
    k_value * n_value * (n_value + 1) / 2
  }
}