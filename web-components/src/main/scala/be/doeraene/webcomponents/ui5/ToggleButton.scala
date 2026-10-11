package be.doeraene.webcomponents.ui5

import be.doeraene.webcomponents.WebComponent
import be.doeraene.webcomponents.ui5.configkeys.ButtonAccessibleRole
import be.doeraene.webcomponents.ui5.configkeys.ButtonDesign
import be.doeraene.webcomponents.ui5.configkeys.ButtonType
import be.doeraene.webcomponents.ui5.configkeys.IconName
import be.doeraene.webcomponents.ui5.datatypes.AccessibilityAttributes
import be.doeraene.webcomponents.ui5.eventtypes.EventWithPreciseTarget
import be.doeraene.webcomponents.ui5.eventtypes.HasDetail
import be.doeraene.webcomponents.ui5.internal.Slot
import com.raquo.laminar.api.L.*
import com.raquo.laminar.codecs.BooleanAsAttrPresenceCodec
import com.raquo.laminar.codecs.StringAsIsCodec
import com.raquo.laminar.keys.HtmlAttr
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** The ui5-toggle-button component is an enhanced ui5-button that can be toggled between pressed and normal states.
  * Users can use the ui5-toggle-button as a switch to turn a setting on or off. It can also be used to represent an
  * independent choice similar to a check box.
  *
  * @see
  *   <a href="https://ui5.github.io/webcomponents/components/ToggleButton/">the doc</a> for more information.
  */
object ToggleButton extends WebComponent with HasAccessibleName with HasIcon {

  @js.native
  trait RawElement extends js.Object {
    var accessibilityAttributes: js.Object = js.native

    def pressed: Boolean = js.native
  }

  @js.native
  @JSImport("@ui5/webcomponents/dist/ToggleButton.js", JSImport.Default)
  object RawImport extends js.Object

  // object-s are lazy so you need to actually use them in your code to prevent dead code elimination
  used(RawImport)

  type Ref = dom.html.Element & RawElement

  protected val tag: CustomHtmlTag[Ref] = CustomHtmlTag("ui5-toggle-button")

  lazy val pressed: HtmlAttr[Boolean] = htmlAttr("pressed", BooleanAsAttrPresenceCodec)

  lazy val accessibleRole: HtmlAttr[ButtonAccessibleRole] =
    htmlAttr("accessible-role", ButtonAccessibleRole.AsStringCodec)

  lazy val accessibleDescription: HtmlAttr[String] = htmlAttr("accessible-description", StringAsIsCodec)
  lazy val design: HtmlAttr[ButtonDesign]          = htmlAttr("design", ButtonDesign.AsStringCodec)
  lazy val disabled: HtmlAttr[Boolean]             = htmlAttr("disabled", BooleanAsAttrPresenceCodec)
  lazy val endIcon: HtmlAttr[IconName]             = htmlAttr("end-icon", IconName.AsStringCodec)
  lazy val tooltip: HtmlAttr[String]               = htmlAttr("tooltip", StringAsIsCodec)
  lazy val tpe: HtmlAttr[ButtonType]               = htmlAttr("type", ButtonType.AsStringCodec)

  /** Id of the form element with which the button is associated (only relevant when `tpe` is `Submit` or `Reset`).
    *
    * @since UI5 2.21.0
    */
  lazy val form: HtmlAttr[String] = htmlAttr("form", StringAsIsCodec)

  /** When `true`, a busy indicator is displayed on the button (and it can't be clicked).
    *
    * @since UI5 2.13.0
    */
  lazy val loading: HtmlAttr[Boolean] = htmlAttr("loading", BooleanAsAttrPresenceCodec)

  /** Delay before the busy indicator is shown when `loading` is `true` (default: 1000 ms).
    *
    * @since UI5 2.13.0
    */
  lazy val loadingDelay: HtmlAttr[FiniteDuration] = htmlAttr("loading-delay", FiniteDurationCodec)

  /** Additional accessibility attributes. Supported fields: `expanded`, `hasPopup`, `controls`, `ariaKeyShortcuts` and
    * `ariaLabel`.
    */
  lazy val accessibilityAttributes: HtmlProp[AccessibilityAttributes, AccessibilityAttributes] =
    AccessibilityAttributes.asHtmlProp

  @deprecated(
    "Set the \"type\" property to \"Submit\" to achieve the same result. The \"submits\" property is ignored if \"type\" is set to any value other than \"Button\".",
    since = "1.15.0"
  )
  lazy val submits: HtmlAttr[Boolean] = htmlAttr("submits", BooleanAsAttrPresenceCodec)

  @scala.annotation.compileTimeOnly(
    """iconEnd has been removed and replaced by the new endIcon mecanic. If you previously had `ToggleButton(_.icon := IconName.upload, _.iconEnd := true, "Upload")`, you now have to replace by `ToggleButton(_.endIcon := IconName.upload, "Upload")`"""
  )
  def iconEnd: HtmlAttr[Boolean] = ???

  object slots {

    /** Badge to display on the button (use a [[ButtonBadge]]). */
    val badge: Slot = Slot("badge")
  }

  object events {

    /** Fired when the component is activated (the `pressed` state has already been toggled when the event fires).
      * Calling `preventDefault()` on the event restores the previous `pressed` state.
      */
    val onClick: EventProp[EventWithPreciseTarget[Ref] & HasDetail[Button.events.ClickDetail]] =
      new EventProp("click")
  }

}
