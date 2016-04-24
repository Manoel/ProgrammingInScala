package domain.element

import Element.elem

import org.scalatest.WordSpec
import org.scalatest.prop.Checkers
import org.scalacheck.Prop._

import domain.element.Element.elem;

class ElementPropertySpec extends WordSpec with Checkers {

  "elem result" must {
    "have passed width" in {
      check((w: Int) => (w > 0 && w < 10) ==> {
        println("w= " + w)
        (elem('x', 1, 3).width == 1)
      })
    }
    "have passed height" in {
      check((h: Int) => h > 0 && h < 10 ==> {
        println("h= " + h)
        (elem('x', 2, 1).height == 1)
      })
    }
  }

}