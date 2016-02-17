import scala.annotation.tailrec
import org.junit.Assert._
import org.junit.Test

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
  val fruitList  = List("peach", "apple", "pear")
  val numList  = List(1, 2, 3, 4, 5, 6, 7)
  val palindromeList  = List("peach", "apple", "pear", "apple", "peach")
  val nestedList = List(List(1,1),2,List(3,List(5,8) ))
  val nestedList2 = List(List(1,1),List(5,8))

  def main(args: Array[String]) {

    printf("%d%n", sumList(numList))

    printf("P03: %d%n", P03_kthElement(2, numList))
    printf("P04: %d%n", P04_size(numList))

    printf("P05: %s%n", P05_reverse(fruitList))
    printf("P05b: %s%n", P05b_reverse(fruitList))
    printf("P05: %s%n", P05_reverseTailRecursion(fruitList))
    printf("P01: %s%n", P05_reverseFunctional(fruitList))

    printf("P05: %s%n", P05_reverse(numList))
    printf("P05: %s%n", P05_reverseTailRecursion(numList))
    printf("P06: %s%n", P06_palindrome(numList))
    printf("P06: %s%n", P06_palindrome(palindromeList))
    //printf("P07: %s%n", P07_flatten(nestedList))
  }

  def P01_last1[E](list: List[E]) = {
    list.reverse.head
  }

  def P01_last2[E](list: List[E]) = {
    list.last
  }

  def P02_penultimate[E](list: List[E]) = {
    list(list.size - 2)
  }

  def P03_kthElement[E](position:Int, list: List[E]) = {
    list.apply(position - 1)
  }

  def P04_size[E](list: List[E]) = {
    list.size
  }

  def P05_reverse[E](list: List[E]):List[E] = {
    list match {
      case Nil => Nil
      case theHead :: theTail => P05_reverse(theTail) ::: List(theHead)
    }
  }

  def P05b_reverse[E](list: List[E]):List[E] = {
    list match {
      case Nil => Nil
      case _ => P05_reverse(list.tail) ::: List(list.head)
    }
  }


  def P05_reverseTailRecursion[E](list: List[E]):List[E] = {
    @tailrec
    def reverse [E](theResult: List[E], theTail: List[E]):List[E] = {
      theTail match {
        case Nil => theResult
        case theHead :: theTail => reverse( theHead :: theResult, theTail )
      }
    }

    reverse(Nil, list)
  }

  def P05_reverseFunctional[E](list: List[E]):List[E] = {
    list.foldLeft(List[E]()) { (theResult, element) => element :: theResult }
  }

  def P06_palindrome[E](list: List[E]):Boolean = {
    for (i <- 0 to (list.size - 1) / 2)
      if (list(i) != list(list.size - i - 1)) {
        return false
      }
    true
  }

  def sumList(list:List[Int]) = {
    list.foldLeft(0) { (total, number) => total + number }
  }
}
