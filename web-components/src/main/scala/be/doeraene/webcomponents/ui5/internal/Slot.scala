package be.doeraene.webcomponents.ui5.internal

import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveElement.Base

import scala.annotation.targetName

/** A [[Slot]] represents a special child component of web components.
  *
  * Many web components reserve a `slot` attribute for some of their children, with a particular meaning.
  *
  * In order to have compile-time fixed slots for your elements, you can define a variable with their name, and it will
  * allow you to attach child in a simple manner.
  */
final class Slot(name: String) {

  private val slot: HtmlAttr[String] = htmlAttr("slot", StringAsIsCodec)

  def :=(element: HtmlElement): Inserter = <--(Val(element.amend(slot := name)))

  def :=(elements: Seq[HtmlElement])(using DummyImplicit): Inserter = <--(Val(elements))

  def <--(elementObservable: Observable[HtmlElement]): Inserter =
    child <-- elementObservable.map(_.amend(slot := name))

  def <--(elementsObservable: Observable[Seq[HtmlElement]])(using DummyImplicit): Inserter =
    children <-- elementsObservable.map(_.map(_.amend(slot := name)))

}
