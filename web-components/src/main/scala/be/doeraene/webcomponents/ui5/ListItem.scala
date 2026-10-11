package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.{ListItemAccessibleRole, ListItemType, ValueState, WrappingType}
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.{EventWithPreciseTarget, HasDetail, ListItemClickDetail}
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.{BooleanAsAttrPresenceCodec, StringAsIsCodec}
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-li represents the simplest type of item for a ui5-list. This is a list item, providing the most common use
  * cases such as text, image and icon.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/ListItemStandard/">the doc</a> for more information.
  */
object ListItem extends WebComponent with HasIcon with HasText with HasDescription with HasAdditionalText {

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

  lazy val accessibleName: HtmlAttr[String] = htmlAttr("accessible-name", StringAsIsCodec)
  lazy val iconEnd: HtmlAttr[Boolean]       = htmlAttr("icon-end", BooleanAsAttrPresenceCodec)
  lazy val navigated: HtmlAttr[Boolean]     = htmlAttr("navigated", BooleanAsAttrPresenceCodec)
  lazy val tpe: HtmlAttr[ListItemType]      = ListItemType.asHtmlAttr("type")
  lazy val selected: HtmlAttr[Boolean]      = htmlAttr("selected", BooleanAsAttrPresenceCodec)
  lazy val highlight: HtmlAttr[ValueState]  = ValueState.asHtmlAttr("highlight")
  lazy val movable: HtmlAttr[Boolean]       = htmlAttr("movable", BooleanAsAttrPresenceCodec)
  lazy val tooltip: HtmlAttr[String]        = htmlAttr("tooltip", StringAsIsCodec)

  /** Whether the text (and description) should wrap (`Normal`) or be truncated (`None`, default).
    *
    * @since UI5 2.10.0
    */
  lazy val wrappingType: HtmlAttr[WrappingType] = WrappingType.asHtmlAttr("wrapping-type")

  /** Role of the item. If not set, it is inherited from the parent list.
    *
    * @since UI5 2.23.0
    */
  lazy val accessibleRole: HtmlAttr[ListItemAccessibleRole] = ListItemAccessibleRole.asHtmlAttr("accessible-role")

  /** Additional accessibility attributes. Supported fields: `hasPopup`, `ariaSetsize` and `ariaPosinset`. */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  @scala.annotation.compileTimeOnly("The image property has been replaced by the image slot.")
  def image: HtmlAttr[String] = ???

  object slots {
    val deleteButton: Slot = Slot("deleteButton")
    val image: Slot        = Slot("image")

    def imageContent: Slot = image
  }

  object events {
    val onDetailClick: EventProp[EventWithPreciseTarget[Ref]] = new EventProp("detail-click")

    /** Fired when the item is activated (by mouse or keyboard), since UI5 2.23.0. */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[ListItemClickDetail]] = new EventProp("click")
  }

}
