package be.doeraene.webcomponents.ui5.internal

import com.raquo.domtypes.generic.codecs.StringAsIsCodec
import com.raquo.laminar.api.L.*
import com.raquo.laminar.keys.ReactiveHtmlAttr
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

  private val slot: ReactiveHtmlAttr[String] = customHtmlAttr("slot", StringAsIsCodec)

  @targetName("fixedSlot")
  def :=(element: HtmlElement): Inserter[Base] = <--(Val(element.amend(slot := name)))

  @targetName("fixedSlot")
  def :=(elements: Seq[HtmlElement])(using DummyImplicit): Inserter[Base] = <--(Val(elements))

  @targetName("slotObservable")
  def     <--(elementObservable: Observable[HtmlElement]): Inserter[Base] =
    child <-- elementObservable.map(_.amend(slot := name))

  @targetName("slotObservable")
  def        <--(elementsObservable: Observable[Seq[HtmlElement]])(using DummyImplicit): Inserter[Base] =
    children <-- elementsObservable.map(_.map(_.amend(slot := name)))

}
