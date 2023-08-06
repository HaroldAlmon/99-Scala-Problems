package com.translationdata

// Tested with junit-4.13.2.jar
// and hamcrest-core-2.2.jar
// In IntelliJ, add Jars to Files > Project Structures > Modules > + > Jars or Directories

import org.junit.Assert._
import org.junit.Test

import scala.annotation.tailrec

class P01 {
  @Test
  def P01_last1():Unit = {
    val result = P01.P01_last1(P01.numList)
    assertEquals(7, result)
    printf("P01_last1: %d%n", result)
  }

  @Test
  def P01_last2():Unit = {
    val result = P01.P01_last2(P01.fruitList)
    assertEquals("pear", result)
    printf("P01_last2: %s%n", result)
  }

  @Test
  def P02_penultimate():Unit = {
    val result = P01.P02_penultimate(P01.fruitList)
    assertEquals("apple", result)
    printf("P02_penultimate: %s%n", result)
  }

  @Test
  def P03_kthElement():Unit = {
    val result = P01.P03_kthElement(4, P01.palindromeList)
    assertEquals("apple", result)
    printf("P03_kthElement: %s%n", result)
  }
}

object P01 {
  private val fruitList = List("first", "peach", "apple", "pear", "last")
  private val numList = List(11, 22, 33, 44, 55, 66, 77)
  private val palindromeList = List("peach", "apple", "pear", "apple", "peach")
  val nestedList = List(List(0, 1), 2, List(3, List(5, 8)))

  def main(args: Array[String]) {
    printf("P05: %s%n", P05_reverse(fruitList))
    printf("P05: %s%n", P05_reverse(fruitList))

    printf("%d%n", sumList(numList))

    printf("P03: %d%n", P03_kthElement(2, numList))
    printf("P03: %d%n", P03_kthElement2(3, numList))
    printf("P04: %d%n", P04_size(numList))

    printf("P05: %s%n", P05_reverse(fruitList))
    printf("P05b: %s%n", P05b_reverse(fruitList))
    printf("P05_reverseTailRecursion: %s%n", P05_reverseTailRecursion(fruitList))
    printf("P01 Functional: %s%n", P05_reverseFunctional(fruitList))

    printf("P05: %s%n", P05_reverse(numList))
    //printf("P05: %s%n", P05_reverseTailRecursion(numList))
    printf("P06: %s%n", P06_palindrome(numList))

    // printf("P06: %s%n", P06_palindrome(palindromeList))
    // printf("Test_08: %s%n", Test_08(fruitList))

    //printf("P07: %s%n", P07_flattenList(nestedList))
  }

  private def sumList(list: List[Int]) = {
    list.foldLeft(0)((total, number) => total + number)
  }

  private def P01_last1[E](list: List[E]) = {
    list.reverse.head
  }

  private def P01_last2[E](list: List[E]) = {
    list.last
  }

  private def P02_penultimate[E](list: List[E]) = {
    list(list.size - 2)
  }

  private def P03_kthElement[E](position: Int, list: List[E]) = {
    list.apply(position - 1)
  }

  private def P03_kthElement2[E](position: Int, list: List[E]) = {
    println("Pos = " + position)
    list(position - 1)
  }

  private def P04_size[E](list: List[E]) = {
    list.size
  }

  private def P05_reverse[E](list: List[E]): List[E] = {
    // @tailrec

    list match {
      case Nil => Nil
      case theHead :: theTail // This splits the list into the frst element and the list minus the first element.
      => P05_reverse(theTail) ::: List(theHead) // Same as next function just a more elegant way.
    }
  }

  // Will cause stack overflow on large lists...
  private def P05b_reverse[E](list: List[E]): List[E] = {
    list match {
      case Nil => Nil
      case _ => P05_reverse(list.tail) ::: List(list.head)
    }
  }

  // Tail recurion avoids stack overflows...
  private def P05_reverseTailRecursion[E](list: List[E]): List[E] = {

    @tailrec
    def reverse[E](listOut: List[E], listIn: List[E]): List[E] = {
      listIn match {
        case List() => listOut // We are done.

        case theHead :: theTail =>
          reverse(List(theHead) ::: listOut, theTail)
      }
    }

    reverse(List(), list)
  }

  private def P05_flatten_test[E](list: List[E]): List[E] = {
    @tailrec
    def flatten[E](theList: List[E], resultOut: List[E]): List[E] = {
      theList match {
        case Nil => resultOut
        case theHead :: theTail =>
          flatten(theHead :: resultOut, theTail)
      }
    }

    flatten(Nil, list)
  }

  private def P05_reverseFunctional[E](list: List[E]): List[E] = {
    list.foldLeft(List[E]())((resultOut, nextElement)
    => nextElement :: resultOut)
  }

  private def P06_palindrome[E](list: List[E]): Boolean = {
    // Simple example of a for-comprehension that is a foreach loop...
    for (i <- 0 to (list.size - 1) / 2)
      if (list(i) != list(list.size - i - 1)) {
        return false
      }
    true
  }
  /*
  private def P07_flattenList[E](list: List[E]): List[E] = {
    //@tailrec

    def flatten[E](theTail: List[E], theResult: List[E]): List[E] = {
      theTail match {
        // case Nil => theResult
        case theHead :: theTail =>
          //println(theHead)
          //println(theTail)
          P07_flattenList(theHead) ::: P07_flattenList(theTail)

        case element => element
      }
    }

    list match {
      case theHead :: theTail =>
        P07_flattenList(theHead) ::: P07_flattenList(theTail)
      case _ => list
    }
  }
  */

  private def P05_reverseTailRecursion2[E](list: List[E]): List[E] = {
    // @tailrec
    //def reverse[E](theTail: List[E], theResult: List[E]): List[E] = {
    list match {
      case theHead :: theTail =>
        theTail ::: P05_reverseTailRecursion2(theTail)

      //case _ => List(list)
    }
  }

  //reverse(Nil, list)

  /*
  private def Test_08[E](list: List[E]): List[E] = {
    list match {
      case theHead :: theTail =>
        List(theHead)
    }
  }
*/


 /*
  private def P08_flattenList[E](list: List[E]): List[E] = {
    list match {
      case theHead :: theTail =>

        // Part 1: Analyse theHead
        theHead match {
          case h :: t =>

            // Part 1: Analyse theTail
            theTail match {
              case h2 :: t2 => // The theTail is a list
                P08_flattenList(theHead) ::: P08_flattenList(theTail) // Case 1. theHead and theTail are lists

              case _ =>
                P08_flattenList(theHead) ::: List(theTail) // Case 2. theHead is a list, theTail is an element
            }

          // Part 2. theHead is an element
          case _ =>
            // Part 2: Analyse theTail
            theTail match {
              case h3 :: t3 => // theTail is a list
                List(theHead) ::: P08_flattenList(theTail) // Case 3. theHead is an element, theTail is a list

              case _ =>
                List(theHead) ::: List(theTail) // Case 2. theHead is an element, theTail is an element
            }

        }
      case _ => List(list) // convert element to a list
    }
  }
*/

  /*
  private def P08_flattenList[E](list: List[E]): List[E] = {
    list match {
      case theHead :: theTail =>

        // Part 1: Analyse theHead
        theHead match {
          case h :: t =>

            // Part 1: Analyse theTail
            theTail match {
              case h2 :: t2 => // The theTail is a list
                P08_flattenList(theHead) ::: P08_flattenList(theTail) // Case 1. theHead and theTail are lists
            }
        }
    }
  }
  */

  //}


  /*
In general:

  :: - adds an element at the beginning of a list and returns a list with the added element
::: - concatenates two lists and returns the concatenated list

For example:

1 :: List(2, 3)             will return     List(1, 2, 3)
List(1, 2) ::: List(3, 4)   will return     List(1, 2, 3, 4)
*/


}