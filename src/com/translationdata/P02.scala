package com.translationdata

// Tested with junit-4.13.2.jar
// and hamcrest-core-2.2.jar
// In IntelliJ, add Jars to Files > Project Structures > Modules > + > Jars or Directories

import org.junit.Assert._
import org.junit.Test

import scala.::
import scala.annotation.tailrec

class P02 {

}

object P02 {
  private val num_list = List(1, 2, 3, 4, 5, 6, 6, 8)
  def main(args: Array[String]): Unit = {
    printf("Given: %s%n%n", num_list)
    printf("Last element in list: %s%n", P02_last_element(num_list))
    printf("Second last element in list: %s%n", P02_penultimate_element(num_list))
    printf("List size : %s%n", P02_number_of_elements(num_list))
    printf("Imperative reverse the list : %s%n", P02_reverse_list(num_list))
    printf("Functional reverse list : %s%n", P02_reverse_list_advanced(num_list))
    printf( "Factorial is %s%n", myfactorial(8) )
    printf( "Factorial with tail recursion is %s%n", myfactorialAdvanced(8) )
  }

  private def P02_last_element[E](list: List[E]): E = {
    //list.reverse.head
    list(list.size - 1)
  }

  private def P02_penultimate_element[E](list: List[E]): E = {
    //list.reverse.head
    list(list.size - 2)
  }

  private def P02_number_of_elements[E](list: List[E]) = {
    list.size
  }

  private def P02_reverse_list[E] (list: List[E]): List[E] = {
    list match {
      case Nil => Nil
      case _ => P02_reverse_list(list.tail) ::: List(list.head)
    }
  }

  private def P02_reverse_list_advanced[E](list: List[E]): List[E] = {
    list match {
      case headElement :: tailList =>
        P02_reverse_list_advanced(tailList) ::: List(headElement)

      case List() => list // Reverse of any empty list is an empty list!
      //case Nil => list
      //case _ => list
    }
  }

  private def myfactorial(n: Int): Int = {
    if (n == 1)
      1
    else
      n * myfactorial(n - 1)
  }

  private def myfactorialAdvanced(n_in: Int): Int = {
    @tailrec
    def myfactorial_base(theproduct: Int, n: Int): Int = {
      if (n == 1)
        theproduct // We are done.
      else
        myfactorial_base(n * theproduct, n - 1)
    }

    myfactorial_base(n_in, n_in - 1)
  }

}


