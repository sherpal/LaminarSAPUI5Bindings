package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ButtonDesign, ColourScheme, IconName}
import be.doeraene.webcomponents.ui5.eventtypes.{HasColor, HasDetail}
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.scaladsl.colour.Colour
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** Represents a predefined range of colors for easier selection. Overview The ColorPalettePopover provides the users
  * with a slot to predefine colors. You can customize them with the use of the colors property. You can specify a
  * defaultColor and display a "Default color" button for the user to choose directly. You can display a "More
  * colors..." button that opens an additional color picker for the user to choose specific colors that are not present
  * in the predefined range.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/ColorPalettePopover/">the doc</a> for more
  *   information.
  */
object ColourPalettePopover extends WebComponent {

  //noinspection ScalaUnusedSymbol
  @js.native
  trait RawElement extends js.Object {
    var open: Boolean  = js.native
    var opener: String = js.native
  }

  object RawElement {
    extension (elem: RawElement) {

      @scala.annotation.compileTimeOnly(
        "showAt method has been removed, and replaced by using the open and openerId mecanic"
      )
      def showAt(opener: dom.HTMLElement): Unit = ???
    }
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ColorPalettePopover.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-color-palette-popover")

  lazy val defaultColour: HtmlAttr[Colour] = htmlAttr("default-color", Colour.AsStringCodec)

  lazy val showDefaultColour: HtmlAttr[Boolean] =
    htmlAttr("show-default-color", BooleanAsAttrPresenceCodec)

  lazy val open: HtmlAttr[Boolean] = htmlAttr("open", BooleanAsAttrPresenceCodec)

  lazy val openerId: HtmlAttr[String] = htmlAttr("opener", StringAsIsCodec)

  /** This import is required for the `showMoreColours` property to work. */
  @js.native
  @JSImport("@ui5/webcomponents/dist/features/ColorPaletteMoreColors.js", JSImport.Default)
  object ColourPaletteMoreColours extends js.Object

  lazy val showMoreColours: HtmlAttr[Boolean] = {
    ColourPaletteMoreColours
    htmlAttr("show-more-colors", BooleanAsAttrPresenceCodec)
  }

  lazy val showRecentColours: HtmlAttr[Boolean] =
    htmlAttr("show-recent-colors", BooleanAsAttrPresenceCodec)

  object slots {}

  object events {
    val onItemClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[HasColor]] = new EventProp("item-click")

    val onClose: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("close")
  }

  def item: ColourPaletteItem.type = ColourPaletteItem

  def getColourPalettePopoverById(id: String): Option[Ref] =
    Option(dom.document.getElementById(id)).map(_.asInstanceOf[Ref])

  /** [[Observer]] you can feed a popover ref and a [[dom.HTMLElement]] to open the popover at the element. */
  @scala.annotation.compileTimeOnly(
    "showAtObserver was replaced in 2.0.0 by showAtObserverId, which requires you to give the element id"
  )
  def showAtObserver: Observer[(Ref, dom.HTMLElement)] = ???

  /** [[Observer]] you can feed a popover ref and the id of a [[dom.HTMLElement]] to open the popover at the element. */
  val showAtObserverId: Observer[(Ref, String)] =
    Observer { (ref, openerId) =>
      ref.opener = openerId
      scala.scalajs.js.timers.setTimeout(0) {
        ref.open = true
      }
    }

  /** [[Mod]] for [[ColourPalettePopover]]s opening them each time the stream emits an opener [[dom.HTMLElement]] */
  @scala.annotation.compileTimeOnly(
    "showAtFromEvents was replaced in 2.0.0 by showAtOpenerIdFromEvents, which requires you to give the element id"
  )
  def showAtFromEvents(openerEvents: EventStream[dom.HTMLElement]): Mod[ReactiveHtmlElement[Ref]] = ???

  /** [[Mod]] for [[ColourPalettePopover]]s opening them each time the stream emits an opener [[dom.HTMLElement]] with
    * the id specified by the stream
    */
  def showAtOpenerIdFromEvents(openerEvents: EventStream[String]): Mod[ReactiveHtmlElement[Ref]] =
    inContext[ReactiveHtmlElement[Ref]](el => openerEvents.map(el.ref -> _) --> showAtObserverId)

}
