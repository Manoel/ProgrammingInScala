package domain.element

import Element.elem

import org.scalatest.FunSuite

import domain.element.Element.elem;

class ElementSuite extends FunSuite {

  test("contents of element should have width and height equals to Array dimensions") {
    val e = elem(Array("-", "-"))

    assertResult(1)(e.width)
    assertResult(2)(e.height)
  }

}