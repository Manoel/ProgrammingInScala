package domain.element

import Element.elem

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import domain.element.Element.elem;

class ElementSpec extends FlatSpec with Matchers {

  "An ArrayElement" should 
    "have the width and height equals to the array dimensions passed" in {
    
    val e = elem(Array("-", "-"))
    e.width should be (1)
    e.height should be (1)
  }

}