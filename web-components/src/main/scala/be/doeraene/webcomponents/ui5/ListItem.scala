package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.ui5.configkeys.{ListItemType, ValueState}
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.api.L.*
import com.raquo.laminar.tags.CustomHtmlTag
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.internal.Slot
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget

/** The ui5-li represents the simplest type of item for a ui5-list. This is a list item, providing the most common use
  * cases such as text, image and icon.
  *
  * @see
  *   <a href="https://sap.github.io/ui5-webcomponents/playground/components/List/">the doc</a> for more information.
  */
object ListItem extends WebComponent with HasIcon with HasDescription with HasAdditionalText {

  @js.native
  trait RawElement extends js.Object {}

  @js.native
  @JSImport("@ui5/webcomponents/dist/ListItemStandard.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-li")

  lazy val additionalTextState: HtmlAttr[ValueState] =
    htmlAttr("additional-text-state", ValueState.AsStringCodec)

  lazy val iconEnd: HtmlAttr[Boolean]      = htmlAttr("icon-end", BooleanAsAttrPresenceCodec)
  lazy val navigated: HtmlAttr[Boolean]    = htmlAttr("navigated", BooleanAsAttrPresenceCodec)
  lazy val tpe: HtmlAttr[ListItemType]     = ListItemType.asHtmlAttr("type")
  lazy val selected: HtmlAttr[Boolean]     = htmlAttr("selected", BooleanAsAttrPresenceCodec)
  lazy val highlight: HtmlAttr[ValueState] = ValueState.asHtmlAttr("highlight")
  lazy val movable: HtmlAttr[Boolean]      = htmlAttr("movable", BooleanAsAttrPresenceCodec)

  @scala.annotation.compileTimeOnly("The image property has been replaced by the image slot.")
  def image: HtmlAttr[String] = ???

  object slots {
    val deleteButton: Slot = Slot("deleteButton")
    val image: Slot        = Slot("image")

    def imageContent: Slot = image
  }

  object events {
    val onDetailClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("detail-click")
  }

}
