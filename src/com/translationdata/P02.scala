package com.translationdata

// Tested with junit-4.13.2.jar
// and hamcrest-core-2.2.jar
// In IntelliJ, add Jars to Files > Project Structures > Modules > + > Jars or Directories

import org.junit.Assert._
import org.junit.Test

import scala.annotation.tailrec
class P02 {

}

object P02 {
  private val num_list = List(1, 2, 3)
  def main(args: Array[String]): Unit = {
    printf("Last element in list: %s", P02_last_element(num_list))
  }

  private def P02_last_element[E](list: List[E]): E = {
    //list.reverse.head
    list(list.size - 1)
  }

  private def P02_penultimate_element[E](list: List[E]): E = {
    //list.reverse.head
    list(list.size - 2)
  }
}


