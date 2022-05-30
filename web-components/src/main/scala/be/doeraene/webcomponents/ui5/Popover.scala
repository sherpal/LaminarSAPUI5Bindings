package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.PopoverPlacementType
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.domtypes.generic.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.keys.{ReactiveHtmlAttr, ReactiveProp, ReactiveStyle}
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-popover component displays additional information for an object in a compact way and without leaving the
  * page.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/Popover/">the doc</a> for more information.
  */
object Popover extends HasIcon with HasOnClick {

  @js.native
  trait RawElement extends js.Object {
    def showAt(opener: dom.HTMLElement): Unit = js.native

    def applyFocus(): Unit = js.native

    def close(): Unit = js.native

    def isOpen(): Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/Popover.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref         = dom.html.Element with RawElement
  type ModFunction = Popover.type => Mod[ReactiveHtmlElement[Ref]]

  private val tag: HtmlTag[Ref] = customHtmlTag("ui5-popover")

  val id: ReactiveProp[String, String] = idAttr

  val disabled: ReactiveHtmlAttr[Boolean] = customHtmlAttr("disabled", BooleanAsAttrPresenceCodec)

  val headerText: ReactiveHtmlAttr[String] = customHtmlAttr("header-text", StringAsIsCodec)

  val placementType: ReactiveHtmlAttr[PopoverPlacementType] =
    customHtmlAttr("placement-type", PopoverPlacementType.AsStringCodec)

  /** id of the element that opens the popover */
  val opener: ReactiveHtmlAttr[String] = customHtmlAttr("opener", StringAsIsCodec)
  val open: ReactiveHtmlAttr[Boolean]  = customHtmlAttr("open", BooleanAsAttrPresenceCodec)

  object slots {
    def header: Slot = new Slot("header")
    def footer: Slot = new Slot("footer")
  }

  object events {
    val onAfterClose: EventProp[dom.Event]  = new EventProp("after-close")
    val onAfterOpen: EventProp[dom.Event]   = new EventProp("after-open")
    val onBeforeClose: EventProp[dom.Event] = new EventProp("before-close")
    val onBeforeOpen: EventProp[dom.Event]  = new EventProp("before-open")
  }

  def apply(mods: ModFunction*): HtmlElement = tag(mods.map(_(Popover)): _*)

  def getPopoverById(id: String): Option[Ref] =
    Option(dom.document.getElementById(id)).map(_.asInstanceOf[dom.HTMLElement & RawElement])

}
